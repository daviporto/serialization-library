package components.string;

import static serialization.Reader.readByte;
import static serialization.Reader.readChars;
import static serialization.Reader.readInt;
import static serialization.Reader.readShort;
import static serialization.Reader.readString;
import static serialization.Writer.insertBytes;

import components.Container;
import components.ContainerType;
import components.Fields.FieldType;

public class DSString extends Container {
	public int Nelements;
	public char[] dataChar;
	
	private DSString() {
		
	}
	
	public byte getContainerType() {
		return  ContainerType.STRING;
	}
	
	public String getString() {
		return new String(dataChar);
	}
	
	public DSString (String name, String data) {
		setName(name);
		dataChar = data.toCharArray();
		Nelements = dataChar.length;
		updateSize();
	}
	
	public int sizeOf() {
		return super.getSize() + size + 4 + 4;
	}
	
	public void updateSize() {
		size +=getDataSize();
	}
	
	public int getDataSize() {
		return Nelements * FieldType.sizeOf(FieldType.CHAR);
	}
	
	public int getBytes(byte[] bytes, int offset) {
		offset = super.getBytes(bytes, offset);
		offset = insertBytes(bytes, offset, size);
		offset = insertBytes(bytes, offset, Nelements);
		offset = insertBytes(bytes, offset, dataChar);

		return offset;
	}
	
	public static DSString deserialize(byte data[], int offset) {
		byte containerType = readByte(data, offset++);
		assert(containerType == ContainerType.STRING);
		
		DSString result = new DSString();
		result.nameLength = readShort(data, offset);
		offset += 2;
		result.name = readString(data, offset, result.nameLength).getBytes();
		offset+= result.nameLength;
		
		result.size = readInt(data, offset);
		offset += 4;
		
		result.Nelements = readInt(data, offset);
		offset += 4;
		
		result.dataChar = new char[result.Nelements];
		readChars(data, offset, result.dataChar);
		offset += result.Nelements * FieldType.sizeOf(FieldType.CHAR);
	
		return result;
		
	}
	
	
}
