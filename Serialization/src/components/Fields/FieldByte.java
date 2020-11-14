package components.Fields;

import static serialization.Writer.insertBytes;

public class FieldByte extends DSField {

	public FieldByte(String name, byte value) {
		setName(name);
		super.dataType = FieldType.BYTE;
		data = new byte[FieldType.sizeOf(FieldType.BYTE)];
		insertBytes(data, 0, value);
	}
}
