package debug;

import components.dataBase.DSDataBase;

public class Debbug {
	private DebbugFrame debbugFrame;
	public Debbug(DSDataBase DB) {
		debbugFrame = new DebbugFrame(DB);
	}
	public void dispose() {
		debbugFrame.dispose();
	}
}
