package components.Arrays;

import components.Fields.FieldType;

public class longArray extends DSArray{

	public longArray(String name, long[] data) {
		setName(name);
		super.dataType = FieldType.LONG;
		this.dataLong = data;
		Nelements = data.length;
		updateSize();
	}
}
