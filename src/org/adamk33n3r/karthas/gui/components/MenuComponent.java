package org.adamk33n3r.karthas.gui.components;

// LWJGL imports
import org.lwjgl.input.Keyboard;

// My imports
import org.adamk33n3r.karthas.gui.Menu;

public abstract class MenuComponent extends Component {
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
					menu.getSelected().execute();
				}
			}
		}
	}
	
	@Override
	public void render() {
		menu.render();
	}
	
}
