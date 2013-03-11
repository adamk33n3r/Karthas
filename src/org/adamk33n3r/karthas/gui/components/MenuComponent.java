package org.adamk33n3r.karthas.gui.components;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

//My imports
import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.Karthas;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.SoundPlayer;
import org.adamk33n3r.karthas.gui.GUI;

public class MenuComponent extends Component {
	Menu menu;
	
	public MenuComponent() {
		type = Component.Type.MENU;
	}
	
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
				}else if (key == Keyboard.KEY_DOWN)
					menu.nextItem();
				else if (key == Keyboard.KEY_UP)
					menu.prevItem();
				else if (key == Keyboard.KEY_RETURN) {
					if(!Keyboard.isRepeatEvent()) {
						menu.getSelected().execute();
					}
				}else if (key == Keyboard.KEY_F) {
					boolean fullscreen = !GUI.fullscreen;
					if(fullscreen) {
						GUI.width = Display.getDesktopDisplayMode().getWidth();
						GUI.height = Display.getDesktopDisplayMode().getHeight();
					} else {
						GUI.width = 800;
						GUI.height = 600;
					}GUI.setDisplayMode(GUI.width, GUI.height, fullscreen);
					Karthas.printDebug("Switched fullscreen to: " + fullscreen + " With: " + Display.getDisplayMode());				}
			}
		}
	}
	
	@Override
	public void render() {
		menu.render();
	}
	
}
