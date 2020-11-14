package components.Fields;

import static serialization.Writer.insertBytes;

public class FieldFloat extends DSField{

	public FieldFloat(String name, float value) {
		setName(name);
		super.dataType = FieldType.FLOAT;
		data = new byte[FieldType.sizeOf(FieldType.FLOAT)];
		insertBytes(data, 0, value);
	}

}
