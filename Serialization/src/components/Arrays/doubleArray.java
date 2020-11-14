package components.Arrays;

import components.Fields.FieldType;

public class doubleArray extends DSArray{

	public doubleArray(String name, double[] data) {
		setName(name);
		super.dataType = FieldType.DOUBLE;
		this.dataDouble = data;
		Nelements = data.length;
		updateSize();
	}
}
