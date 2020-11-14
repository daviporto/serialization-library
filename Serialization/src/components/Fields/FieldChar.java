package components.Fields;

import static serialization.Writer.insertBytes;

public class FieldChar extends DSField {

	public FieldChar(String name, char value) {
		setName(name);
		super.dataType = FieldType.CHAR;
		data = new byte[FieldType.sizeOf(FieldType.CHAR)];
		insertBytes(data, 0, value);
	}

}
