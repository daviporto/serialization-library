package components.Fields;

import static serialization.Writer.insertBytes;

public class FieldLong extends DSField {
	
	public FieldLong(String name, long value) {
		setName(name);
		super.dataType = FieldType.LONG;
		data = new byte[FieldType.sizeOf(FieldType.LONG)];
		
		insertBytes(data, 0, value);
	}
}
