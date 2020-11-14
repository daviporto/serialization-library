package testSerialization;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import components.Arrays.DSArray;
import components.Arrays.booleanArray;
import components.Arrays.intArray;
import components.Fields.DSField;
import components.Fields.FieldDouble;
import components.Fields.FieldFloat;
import components.Fields.FieldInt;
import components.Objects.DSObject;
import components.dataBase.DSDataBase;
import components.string.DSString;

//uncomment the code inside main for test


public class Main {
	static void printeB(byte[] b) {
		for (int i = 0; i < b.length; i++) {
			System.out.printf("0x%x ", b[i]);
		}
	}

	public static void writeToAfile(String path, byte[] data) {
		try {
			BufferedOutputStream s = new BufferedOutputStream(new FileOutputStream(path));
			s.write(data);
			s.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static void serialize() {

		DSDataBase DB = new DSDataBase("dataBase");
		DSArray a = new intArray("aasdasdasdsadsaa", new int[] { Integer.MAX_VALUE, 0x1111, 0x5555, 0xaaaa });
		DSField f = new FieldDouble("nnn", 158.558);
		DSField f1 = new FieldInt("xposition", 1);
		DSField f3 = new FieldInt("xposition2", 2);
		DSField f22 = new FieldInt("xposition3", 3);
		DSField f2 = new FieldFloat("yposition", 0xff);
		DSString s = new DSString("String", "heelo cruel wrold!, why on eath i'm alive yet? that make any sense?");
		DSObject o = new DSObject("um ojeto ai");
		o.pushArray(a);
		o.pushField(f);
		o.pushField(f1);
		o.pushField(f2);
		o.pushField(f22);
		o.pushField(f3);
		o.pushString(s);
		o.pushField(f);
		DB.pushObject(o);
		DSObject o2 = new DSObject("second one ");
		o2.pushString(new DSString("usless String ", "push push push push"));
		o2.pushField(f2);
		o2.pushArray(new booleanArray("aray of booleans", new boolean[] { true, true, false, true }));

		DSObject wiredone = new DSObject("who knows");
		DB.pushObject(wiredone);
		DB.pushObject(o2);
		DB.serializeToFile("serialized.bin");
		DB.enableDebbug();// enable debbug tree
	}

	public static void deserialize() {
		DSDataBase.deserializeFromFile("serialized.bin");
	}

	public static void main(String[] args) {
		serialize();
		deserialize();
	}

}
