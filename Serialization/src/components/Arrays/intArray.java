package components.Arrays;

import components.Fields.FieldType;

public class intArray extends DSArray{

	public intArray(String name, int[] data) {
		setName(name);
		super.dataType = FieldType.INT;
		this.dataInt = data;
		Nelements = data.length;
		updateSize();
	}
}
