package components.Arrays;

import components.Fields.FieldType;

public class floatArray extends DSArray{

	public floatArray(String name, float[] data) {
		setName(name);
		super.dataType = FieldType.FLOAT;
		this.dataFloat = data;
		Nelements = data.length;
		updateSize();
	}
}
