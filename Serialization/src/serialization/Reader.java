package serialization;

import java.nio.ByteBuffer;

import components.Fields.FieldType;

public class Reader {


	public static void readBytes(byte[] source, int offset, byte[] destination) {
		for (int i = 0; i < destination.length; i++)
			destination[i] = source[offset + i];

	}
	
	public static void readChars(byte[] source, int offset, char[] destination) {
		for (int i = 0; i < destination.length; i++) {
			destination[i] = readChar(source, offset);
			offset += FieldType.sizeOf(FieldType.CHAR);
		}
	}
	
	public static void readInts(byte[] source, int offset, int[] destination) {
		for (int i = 0; i < destination.length; i++) {
			destination[i] = readInt(source, offset);
			offset += FieldType.sizeOf(FieldType.INT);			
		}
	}
	
	public static void readLongs(byte[] source, int offset, long[] destination) {
		for (int i = 0; i < destination.length; i++) {
			destination[i] = readLong(source, offset);
			offset += FieldType.sizeOf(FieldType.LONG);			
		}
	}
	
	public static void readFloats(byte[] source, int offset, float[] destination) {
		for (int i = 0; i < destination.length; i++) {
			destination[i] = readFloat(source, offset);
			offset += FieldType.sizeOf(FieldType.FLOAT);
		}
	}
	
	public static void readDoubles(byte[] source, int offset, double[] destination) {
		for (int i = 0; i < destination.length; i++) {
			destination[i] = readDouble(source, offset);
			offset += FieldType.sizeOf(FieldType.DOUBLE);			
		}
	}
	
	public static void readBooleans(byte[] source, int offset, boolean[] destination) {
		for (int i = 0; i < destination.length; i++) {
			destination[i] = readBoolean(source, offset);
			offset += FieldType.sizeOf(FieldType.BOOLEAN);			
		}
	}
	
	public static void readShorts(byte[] source, int offset, short[] destination) {
		for (int i = 0; i < destination.length; i++) {
			destination[i] = readShort(source, offset);
			offset += FieldType.sizeOf(FieldType.SHORT);			
		}
	}
	
	
	public static byte readByte(byte[] source, int offset) {
		return source[offset];
	}

	public static short readShort(byte[] source, int offset) {
		return ByteBuffer.wrap(source, offset, 2).getShort();
	}

	public static char readChar(byte[] source, int offset) {
		return ByteBuffer.wrap(source, offset, 2).getChar();
	}

	public static int readInt(byte[] source, int offset) {
		return ByteBuffer.wrap(source, offset, 4).getInt();
	}

	public static long readLong(byte[] source, int offset) {
		return ByteBuffer.wrap(source, offset, 8).getLong();
	}

	public static float readFloat(byte[] source, int offset) {
		return ByteBuffer.wrap(source, offset, 4).getFloat();
	}

	public static double readDouble(byte[] source, int offset) {
		return ByteBuffer.wrap(source, offset, 8).getDouble();
	}

	public static boolean readBoolean(byte[] source, int offset) {
		return source[offset] != 0;// if 0 return false otherwise return true
	}

	public static String readString(byte[] source, int offset, int lenght) {
		return new String(source, offset, lenght);
	}

}
