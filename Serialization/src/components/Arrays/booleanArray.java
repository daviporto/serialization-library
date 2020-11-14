package components.Arrays;

import components.Fields.FieldType;

public class booleanArray extends DSArray{

	public booleanArray(String name, boolean[] data) {
		setName(name);
		super.dataType = FieldType.BOOLEAN;
		this.dataBoolean = data;
		Nelements = data.length;
		updateSize();
	}
}
