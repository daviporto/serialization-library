package serialization;

import java.util.logging.Logger;

import components.Fields.FieldType;

public class Writer {
	
	public static int insertBytes(byte source[], int offset, byte[] data) {
		assert(source != null && source.length >= data.length + offset);
		if(source == null || data == null ||
				source.length < data.length + offset) {
			Logger.getGlobal().warning("array com espaco insuficiente");
			return offset;
		}

		for(int i = 0; i < data.length; i++) source[offset++] = data[i];
		return offset;
	}
	public static int insertBytes(byte source[], int offset, byte data) {
		assert(source != null && source.length >= FieldType.sizeOf(FieldType.BYTE) + offset);
		
		if(source == null || source.length < FieldType.sizeOf(FieldType.BYTE) + offset) {
			Logger.getGlobal().warning("array com espaco insuficiente");
			return offset;
		}
		source[offset++] = data;
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, short data) {
		assert(source != null && source.length >= FieldType.sizeOf(FieldType.SHORT) + offset);
		if(source == null || source.length < FieldType.sizeOf(FieldType.SHORT) + offset) {
			Logger.getGlobal().warning("array com espaco insuficiente");
			return offset;
		}
		source[offset++] = (byte) ((data >> 8) & 0xff);
		source[offset++] = (byte) ((data ) & 0xff);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, char data) {
		assert(source != null && source.length >= FieldType.sizeOf(FieldType.CHAR) + offset);
		if(source == null || source.length < FieldType.sizeOf(FieldType.CHAR) + offset) {
			Logger.getGlobal().warning("array com espaco insuficiente");
			return offset;
		}
		source[offset++] = (byte) ((data >> 8) & 0x00ff);
		source[offset++] = (byte) ((data ) & 0xff);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, int data) {
		assert(source != null && source.length >= FieldType.sizeOf(FieldType.INT) + offset);
		if(source == null || source.length < FieldType.sizeOf(FieldType.INT) + offset) {
			Logger.getGlobal().warning("array com espaco insuficiente");
			return offset;
		}
		source[offset++] = (byte) ((data >> 24) & 0xff);
		source[offset++] = (byte) ((data >> 16) & 0xff);
		source[offset++] = (byte) ((data >> 8)  & 0xff);
		source[offset++] = (byte) ((data)  & 0xff);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, long data) {
		assert(source != null && source.length >=  FieldType.sizeOf(FieldType.LONG) + offset);
		if(source == null || source.length < FieldType.sizeOf(FieldType.LONG) + offset) {
			Logger.getGlobal().warning("array com espaco insuficiente");
			return offset;
		}
		source[offset++] = (byte) ((data >> 56)  & 0xff);
		source[offset++] = (byte) ((data >> 48) & 0xff);
		source[offset++] = (byte) ((data >> 40) & 0xff);
		source[offset++] = (byte) ((data >> 32)  & 0xff);
		source[offset++] = (byte) ((data >> 24) & 0xff);
		source[offset++] = (byte) ((data >> 16) & 0xff);
		source[offset++] = (byte) ((data >> 8)  & 0xff);
		source[offset++] = (byte) ((data >> 1)  & 0xff);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, float data) {
		assert(source != null && source.length >= FieldType.sizeOf(FieldType.FLOAT) + offset);
		if(source == null || source.length < FieldType.sizeOf(FieldType.FLOAT) + offset) {
			Logger.getGlobal().warning("array com espaco insuficiente");
			return offset;
		}
		return insertBytes(source, offset, Float.floatToIntBits(data));
	}
	
	public static int insertBytes(byte source[], int offset, double data) {
		assert(source != null && source.length >= FieldType.sizeOf(FieldType.FLOAT) + offset);
		if(source == null || source.length < FieldType.sizeOf(FieldType.DOUBLE) + offset) {
			Logger.getGlobal().warning("array com espaco insuficiente");
			return offset;
		}
		return insertBytes(source, offset, Double.doubleToLongBits(data));
	}
	
	public static int insertBytes(byte source[], int offset, boolean data) {
		assert(source != null && source.length >= FieldType.sizeOf(FieldType.BOOLEAN) + offset);
		if(source == null || source.length < FieldType.sizeOf(FieldType.BOOLEAN) + offset) {
			Logger.getGlobal().warning("array com espaco insuficiente");
			return offset;
		}
		source[offset++] = (byte)( data ? 1 : 0);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, String data) {
		assert(source != null && source.length >= data.length() + offset);
		if(source == null || source.length < data.length() + offset) {
			Logger.getGlobal().warning("array com espaco insuficiente");
			return offset;
		}
		offset = insertBytes(source, offset, (short)data.length());
		return insertBytes(source, offset, data.getBytes());
	}
	
	public static int insertBytes(byte source[], int offset, short[] data) {
		for(int i = 0; i < data.length; i++) offset = insertBytes(source, offset, data[i]);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, char[] data) {
		for(int i = 0; i < data.length; i++) offset = insertBytes(source, offset, data[i]);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, int[] data) {
		for(int i = 0; i < data.length; i++) offset = insertBytes(source, offset, data[i]);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, long[] data) {
		for(int i = 0; i < data.length; i++) offset = insertBytes(source, offset, data[i]);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, float[] data) {
		for(int i = 0; i < data.length; i++) offset = insertBytes(source, offset, data[i]);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, double[] data) {
		for(int i = 0; i < data.length; i++) offset = insertBytes(source, offset, data[i]);
		return offset;
	}
	
	public static int insertBytes(byte source[], int offset, boolean[] data) {
		for(int i = 0; i < data.length; i++) offset = insertBytes(source, offset, data[i]);
		return offset;
	}
	
	
}