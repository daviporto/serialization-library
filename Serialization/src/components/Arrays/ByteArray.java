package components.Arrays;

import components.Fields.FieldType;

public class ByteArray extends DSArray{

	public ByteArray(String name, byte[] data) {
		setName(name);
		super.dataType = FieldType.BYTE;
		this.dataByte = data;
		Nelements = data.length;
		updateSize();
	}
}
