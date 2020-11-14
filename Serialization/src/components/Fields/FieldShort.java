package components.Fields;

import static serialization.Writer.insertBytes;

public class FieldShort extends DSField{
	
	public FieldShort(String name, short value) {
		setName(name);
		super.dataType = FieldType.SHORT;
		data = new byte[FieldType.sizeOf(FieldType.SHORT)];
		insertBytes(data, 0, value);
	}

}
