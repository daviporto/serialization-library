package components.Fields;
import static serialization.Writer.*;

public class FieldInt extends DSField{
	
	public FieldInt(String name, int value) {
		setName(name);
		super.dataType = FieldType.INT;
		data = new byte[FieldType.sizeOf(FieldType.INT)];
		insertBytes(data, 0, value);
	}

}
