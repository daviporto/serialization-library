package components.Arrays;

import components.Fields.FieldType;

public class shortArray extends DSArray{

	public shortArray(String name, short[] data) {
		setName(name);
		super.dataType = FieldType.SHORT;
		this.dataShort = data;
		Nelements = data.length;
		updateSize();
	}
}
