package components.Arrays;

import static serialization.Reader.*;
import static serialization.Writer.insertBytes;

import components.Container;
import components.ContainerType;
import components.Fields.FieldType;

public class DSArray extends Container {
	public byte dataType;
	public int Nelements;
	public byte[] dataByte;
	public short[] dataShort;
	public char[] dataChar;
	public int[] dataInt;
	public long[] dataLong;
	public float[] dataFloat;
	public double[] dataDouble;
	public boolean[] dataBoolean;

	protected DSArray() {

	}

	public byte getContainerType() {
		return ContainerType.ARRAY;
	}

	public int sizeOf() {
		return super.getSize() + 4 + 1 + 4 + size;
	}

	private int getDataSize() {
		return Nelements * FieldType.sizeOf(dataType);
	}

	protected void updateSize() {
		size += getDataSize();
	}

	public int getBytes(byte[] bytes, int offset) {
		offset = super.getBytes(bytes, offset);
		offset = insertBytes(bytes, offset, sizeOf());
		offset = insertBytes(bytes, offset, dataType);
		offset = insertBytes(bytes, offset, Nelements);

		switch (dataType) {
		case FieldType.BYTE:
			offset = insertBytes(bytes, offset, dataByte);
			break;
		case FieldType.CHAR:
			offset = insertBytes(bytes, offset, dataChar);
			break;
		case FieldType.SHORT:
			offset = insertBytes(bytes, offset, dataShort);
			break;
		case FieldType.INT:
			offset = insertBytes(bytes, offset, dataInt);
			break;
		case FieldType.LONG:
			offset = insertBytes(bytes, offset, dataLong);
			break;
		case FieldType.FLOAT:
			offset = insertBytes(bytes, offset, dataFloat);
			break;
		case FieldType.DOUBLE:
			offset = insertBytes(bytes, offset, dataDouble);
			break;
		case FieldType.BOOLEAN:
			offset = insertBytes(bytes, offset, dataBoolean);
			break;
		}
		return offset;
	}

	public static DSArray deserialize(byte data[], int offset) {
		byte containerType = readByte(data, offset++);
		assert (containerType == ContainerType.ARRAY);

		DSArray result = new DSArray();
		result.nameLength = readShort(data, offset);
		offset += 2;
		result.name = readString(data, offset, result.nameLength).getBytes();
		offset += result.nameLength;

		result.size = readInt(data, offset);
		offset += 4;

		result.dataType = readByte(data, offset++);

		result.Nelements = readInt(data, offset);
		offset += 4;

		switch (result.dataType) {
		case FieldType.BYTE:
			result.dataByte = new byte[result.Nelements];
			readBytes(data, offset, result.dataByte);
			offset += result.Nelements * FieldType.sizeOf(FieldType.BYTE);
			;
			break;
		case FieldType.CHAR:
			result.dataChar = new char[result.Nelements];
			readChars(data, offset, result.dataChar);
			offset += result.Nelements * FieldType.sizeOf(FieldType.CHAR);
			break;
		case FieldType.SHORT:
			result.dataShort = new short[result.Nelements];
			readShorts(data, offset, result.dataShort);
			offset += result.Nelements * FieldType.sizeOf(FieldType.SHORT);
			break;
		case FieldType.INT:
			result.dataInt = new int[result.Nelements];
			readInts(data, offset, result.dataInt);
			offset += result.Nelements * FieldType.sizeOf(FieldType.INT);
			break;
		case FieldType.LONG:
			result.dataLong = new long[result.Nelements];
			readLongs(data, offset, result.dataLong);
			offset += result.Nelements * FieldType.sizeOf(FieldType.LONG);
			break;
		case FieldType.FLOAT:
			result.dataFloat = new float[result.Nelements];
			readFloats(data, offset, result.dataFloat);
			offset += result.Nelements * FieldType.sizeOf(FieldType.FLOAT);
			break;
		case FieldType.DOUBLE:
			result.dataDouble = new double[result.Nelements];
			readDoubles(data, offset, result.dataDouble);
			offset += result.Nelements * FieldType.sizeOf(FieldType.DOUBLE);
			break;
		case FieldType.BOOLEAN:
			result.dataBoolean = new boolean[result.Nelements];
			readBooleans(data, offset, result.dataBoolean);
			offset += result.Nelements *  FieldType.sizeOf(FieldType.BOOLEAN);
			break;
		}
		
		return result;

	}

}
