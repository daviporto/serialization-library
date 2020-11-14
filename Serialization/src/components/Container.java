package components;

import static serialization.Writer.insertBytes;

public abstract class Container {
	public static byte containerType;
	public short nameLength;
	public int size = 0;
	public byte[] name;
	
	public void setName(String name) {
		assert(name.length() < Short.MAX_VALUE);
		
		nameLength = (short) name.length();
		this.name = name.getBytes();
	}
	
	public String getName() {
		return new String(name);
	}
	
	public int getSize() {
		return 3 + name.length;
	}
	
	public int getBytes(byte[] bytes, int offset){
//		Logger.getGlobal().info("inserting te type at  " + getContainerType() + "   " + offset);
		offset = insertBytes(bytes, offset, getContainerType());
		offset = insertBytes(bytes, offset, nameLength);
		offset = insertBytes(bytes, offset, name);
		return offset;
	}
	
	public abstract byte getContainerType();

}
