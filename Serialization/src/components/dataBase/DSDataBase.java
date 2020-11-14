package components.dataBase;

import static serialization.Reader.readByte;
import static serialization.Reader.readInt;
import static serialization.Reader.readShort;
import static serialization.Reader.readString;
import static serialization.Writer.insertBytes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import components.Container;
import components.ContainerType;
import components.Objects.DSObject;
import debug.Debbug;

public class DSDataBase extends Container {
	private static final byte[] HEADER = "DSDB".getBytes();// Davi Serialization DateBase
	public static final short VERSION = 0x0001;
	private short Nobjects;
	private Debbug debbug;
	public List<DSObject> objects = new ArrayList<DSObject>();

	private DSDataBase() {

	}

	public byte getContainerType() {
		return ContainerType.DATABASE;
	}

	public DSDataBase(String name) {
		size = HEADER.length + 2 + 4;
		setName(name);
	}

	public void pushObject(DSObject object) {
		objects.add(object);
		size += object.sizeOf();
		Nobjects = (short) objects.size();
	}

	public int sizeOf() {
		return super.getSize() + size + 2;
	}
	
	public void enableDebbug() {
		debbug = new Debbug(this);
	}
	
	public void updateDebbug() {
		debbug = new Debbug(this);
	}
	
	public void disableDebbug() {
		if(debbug != null) {
			debbug.dispose();
			debbug = null;
		}
	}
	
	public static DSDataBase deserialize(byte[] data) {
		int offset = 0;
		String header = readString(data, offset, HEADER.length);
		if (!header.equals(new String(HEADER))) {
			Logger.getGlobal().severe("invalideFile");
			return null;
		}
		offset += HEADER.length;

		short version = readShort(data, offset);
		offset += 2;

		if (version != VERSION) {
			Logger.getGlobal().severe("Invalid version" + version);
			return null;
		}

		byte containerType = readByte(data, offset);
		if (containerType != ContainerType.DATABASE) {
			Logger.getGlobal().severe("Not a dataBase" + containerType);
			return null;
		}
		offset += 1; // container type length;

		DSDataBase result = new DSDataBase();
		result.nameLength = readShort(data, offset);
		offset += 2;
		result.name = readString(data, offset, result.nameLength).getBytes();
		offset += result.nameLength;

		result.size = readInt(data, offset);
		offset += 4;

		result.Nobjects = readShort(data, offset);
		offset += 2;

		int[] offsetRef = new int[] { offset };// java sucks
		for (int i = 0; i < result.Nobjects; i++)
			result.objects.add(DSObject.Deserialize(data, offsetRef));

		return result;
	}

	public void serializeToFile(String path) {
		byte[] data = new byte[sizeOf()];
		getBytes(data, 0);

		BufferedOutputStream out;
		try {
			out = new BufferedOutputStream(new FileOutputStream(path));
			out.write(data);
			out.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	public static DSDataBase deserializeFromFile(String path) {
		byte[] buffer = null;
		try {
			BufferedInputStream s = new BufferedInputStream(new FileInputStream(path));
			buffer = new byte[s.available()];
			s.read(buffer);
			s.close();
		} catch (IOException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}

		return deserialize(buffer);
	}

	public int getBytes(byte[] bytes, int offset) {
		offset = insertBytes(bytes, offset, HEADER);
		offset = insertBytes(bytes, offset, VERSION);
		offset = super.getBytes(bytes, offset);
		offset = insertBytes(bytes, offset, sizeOf());

		offset = insertBytes(bytes, offset, Nobjects);
		for (DSObject o : objects)
			offset = o.getBytes(bytes, offset);

		return offset;
	}

}
