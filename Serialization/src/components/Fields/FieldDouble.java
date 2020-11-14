package components.Fields;

import static serialization.Writer.insertBytes;

public class FieldDouble extends DSField {

	public FieldDouble(String name, double value) {
		setName(name);
		super.dataType = FieldType.DOUBLE;
		data = new byte[FieldType.sizeOf(FieldType.DOUBLE)];
		insertBytes(data, 0, value);
	}
}
