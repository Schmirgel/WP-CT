import java.awt.EventQueue;

import gfx.Gui;
import logic.Processing;
import logic.Storage;

public class Main {
	public static void main(String[] args) {
		final Storage store = new Storage();
		final Processing pUnit = new Processing(store);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui(pUnit);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
