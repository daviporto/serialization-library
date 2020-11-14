package components.Arrays;

import components.Fields.FieldType;

public class charArray extends DSArray{

	public charArray(String name, char[] data) {
		setName(name);
		super.dataType = FieldType.CHAR;
		this.dataChar = data;
		Nelements = data.length;
		updateSize();
	}
}
