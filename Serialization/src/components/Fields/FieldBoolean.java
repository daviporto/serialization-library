package components.Fields;

import static serialization.Writer.insertBytes;

public class FieldBoolean extends DSField{

	public FieldBoolean(String name, boolean value) {
		setName(name);
		super.dataType = FieldType.BOOLEAN;
		data = new byte[FieldType.sizeOf(FieldType.BOOLEAN)];
		insertBytes(data, 0, value);
	}

}
