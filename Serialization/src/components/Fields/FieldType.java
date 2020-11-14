package components.Fields;

import java.util.logging.Logger;

public class FieldType {
	public final static byte UNKNOWN = 0;
	public final static byte BYTE    = 1;
	public final static byte SHORT    = 2;
	public final static byte CHAR    = 3;
	public final static byte INT     = 4;
	public final static byte LONG    = 5;
	public final static byte FLOAT   = 6;
	public final static byte DOUBLE  = 7;
	public final static byte BOOLEAN = 8;
	
	public static int sizeOf(byte type) {
		switch (type) {
		case BYTE:    return 1;
		case SHORT:   return 2;
		case CHAR:    return 2;
		case INT:     return 4;
		case LONG:	  return 8;
		case FLOAT:   return 4;
		case DOUBLE:  return 8;
		case BOOLEAN: return 1;

		}
		Logger.getGlobal().info("tipo invalido");
		return 0;
	}
	

}
