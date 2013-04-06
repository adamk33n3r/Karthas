package org.adamk33n3r.karthas.gui.components;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

//My imports
import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.Karthas;
import org.adamk33n3r.karthas.gui.GUI;

public abstract class MenuComponent extends Component {
	Menu menu;
	
	public Menu getMenu() {
		return menu;
	}
	
	@Override
	public void update() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				int key = Keyboard.getEventKey();
				if (key >= Keyboard.KEY_1 && key < Keyboard.KEY_0) {
					menu.toItem(key - 1);
					menu.getSelected().execute();
				} else {
					switch (key) {
						case Keyboard.KEY_DOWN:
							menu.nextItem();
							break;
						case Keyboard.KEY_UP:
							menu.prevItem();
							break;
						case Keyboard.KEY_RETURN:
							if (!Keyboard.isRepeatEvent()) {
								menu.getSelected().execute();
							}
							break;
						case Keyboard.KEY_F:
							boolean fullscreen = !GUI.fullscreen;
							if (fullscreen) {
								GUI.width = Display.getDesktopDisplayMode().getWidth();
								GUI.height = Display.getDesktopDisplayMode().getHeight();
							} else {
								GUI.width = 800;
								GUI.height = 600;
							}
							GUI.setDisplayMode(GUI.width, GUI.height, fullscreen);
							Karthas.printDebug("Switched fullscreen to: " + fullscreen + " With: " + Display.getDisplayMode());
							break;
					}
				}
			}
		}
	}
	
	@Override
	public void render() {
		menu.render();
	}
	
}
