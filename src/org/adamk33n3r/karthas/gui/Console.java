package org.adamk33n3r.karthas.gui;

import org.adamk33n3r.karthas.Input;
import org.adamk33n3r.karthas.gui.components.ConsoleComponent;

public class Console extends Popup {
	
	/**
	 * Layer to get the user's input
	 * @param prompt - The {@code String} to display at prompt
	 * @param text - The {@code String} to edit
	 * @param parent - The {@code Layer} to display underneath
	 */
	public Console(String prompt, StringBuilder text, Layer parent) {
		super(parent, new ConsoleComponent(prompt, text, parent));
	}
	
	/*public static boolean confirm() { //TODO idk why this is in console
		ConfirmMenu comp = new ConfirmMenu();
		while(comp.getStatus() == 0) {
			GUI.renderState();
			GUI.drawRect(0, 0, GUI.width, GUI.height, new Color(0, 0, 0, 150)); // Makes background darker
			comp.update();
			comp.render();
			//GUI.render(true);
		}
		if (comp.getStatus() == 1)
			return true;
		return false;
	}*/
	
	@Override
	public void update() {
		super.update();
		if (!((ConsoleComponent) children[0]).isRunning()) {
			Input.unlock(children[0]);
			GUI.setInputCheck();
			GUI.goBack();
		}
	}

}