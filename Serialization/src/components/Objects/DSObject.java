package components.Objects;

import static serialization.Reader.readInt;
import static serialization.Reader.readShort;
import static serialization.Reader.readString;
import static serialization.Writer.insertBytes;

import java.util.ArrayList;
import java.util.List;

import components.Container;
import components.ContainerType;
import components.Arrays.DSArray;
import components.Fields.DSField;
import components.string.DSString;

public class DSObject extends Container {
	private short NFields;
	private short Nstrings;
	private short NArrays;
	public List<DSField> fields = new ArrayList<DSField>();
	public List<DSArray> arrays = new ArrayList<DSArray>();
	public List<DSString> strings = new ArrayList<DSString>();

//	private static final int untilNameOffset = 1 + 2 + 4;

	private DSObject() {

	}

	public byte getContainerType() {
		return ContainerType.OBJECT;
	}

	public DSObject(String name) {
		setName(name);
	}

	public void pushField(DSField field) {
		fields.add(field);
		size += field.sizeOf();
		NFields = (short) fields.size();
	}

	public void pushArray(DSArray array) {
		arrays.add(array);
		size += array.sizeOf();
		NArrays = (short) arrays.size();
	}

	public void pushString(DSString string) {
		strings.add(string);
		size += string.sizeOf();
		Nstrings = (short) strings.size();
	}

	public int sizeOf() {
		return super.getSize() + 4 + 2 + 2 + 2 + size;
	}

	public int getBytes(byte[] bytes, int offset) {
		offset = super.getBytes(bytes, offset);
		offset = insertBytes(bytes, offset, sizeOf());

		offset = insertBytes(bytes, offset, NFields);
		for (DSField f : fields)
			offset = f.getBytes(bytes, offset);

		offset = insertBytes(bytes, offset, NArrays);
		for (DSArray a : arrays)
			offset = a.getBytes(bytes, offset);

		offset = insertBytes(bytes, offset, Nstrings);
		for (DSString s : strings)
			offset = s.getBytes(bytes, offset);

		return offset;
	}

	public static DSObject Deserialize(byte[] data, int[] offsetRef) {
		int offset = offsetRef[0];
		assert (data[offset++] == ContainerType.OBJECT);
		DSObject result = new DSObject();

		result.nameLength = readShort(data, offset);
		offset += 2;
		result.name = readString(data, offset, result.nameLength).getBytes();
		offset += result.nameLength;

		result.size = readInt(data, offset);
		offset += 4;

//	early-out	offset += result.size - untilNameOffset - result.nameLength;

		result.NFields = readShort(data, offset);
		offset += 2;

		for (int i = 0; i < result.NFields; i++) {
			DSField field = DSField.deserialize(data, offset);
			offset += field.sizeOf();
			result.fields.add(field);
		}

		result.NArrays = readShort(data, offset);
		offset += 2;
		for (int i = 0; i < result.NArrays; i++) {
			DSArray array = DSArray.deserialize(data, offset);
			offset += array.size;
			result.arrays.add(array);
		}

		result.Nstrings = readShort(data, offset);
		offset += 2;
		for (int i = 0; i < result.Nstrings; i++) {
			DSString string = DSString.deserialize(data, offset);
			offset += string.sizeOf();
			result.strings.add(string);
		}


		offsetRef[0] = offset;
		return result;

	}

}
