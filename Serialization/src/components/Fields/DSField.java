package components.Fields;

import static serialization.Reader.*;
import static serialization.Writer.insertBytes;

import components.Container;
import components.ContainerType;

public class DSField extends Container {
	public byte dataType;
	public byte[] data;

	protected DSField() {

	}

	public byte getContainerType() {
		return ContainerType.FIELD;
	}

	public int sizeOf() {
		return super.getSize() + 1 + data.length;
	}

	public int getBytes(byte[] bytes, int offset) {
		offset = super.getBytes(bytes, offset);
		offset = insertBytes(bytes, offset, dataType);
		offset = insertBytes(bytes, offset, data);
		return offset;
	}

	public byte getByte() {
		return readByte(data, 0);
	}

	public char getChar() {
		return readChar(data, 0);
	}

	public short getShort() {
		return readShort(data, 0);
	}

	public int getInt() {
		return readInt(data, 0);
	}

	public long getLong() {
		return readLong(data, 0);
	}

	public float getFloat() {
		return readInt(data, 0);
	}

	public double getDouble() {
		return readDouble(data, 0);
	}

	public boolean getBoolean() {
		return readBoolean(data, 0);
	}

	public static DSField deserialize(byte data[], int offset) {
		byte containerType = readByte(data, offset++);
		assert (containerType == ContainerType.FIELD);

		DSField result = new DSField();
		result.nameLength = readShort(data, offset);
		offset += 2;
		result.name = readString(data, offset, result.nameLength).getBytes();
		offset += result.nameLength;

		result.dataType = readByte(data, offset++);
		result.data = new byte[FieldType.sizeOf(result.dataType)];
		readBytes(data, offset, result.data);
		offset += FieldType.sizeOf(result.dataType);

		return result;

	}
}
