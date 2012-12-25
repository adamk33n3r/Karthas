package org.adamk33n3r.karthas.gui.components;

import org.lwjgl.input.Keyboard;

public abstract class HorizontalMenuComponent extends MenuComponent {
	
	@Override
	public void update() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				int key = Keyboard.getEventKey();
				if (key >= Keyboard.KEY_1 && key < Keyboard.KEY_0) {
					menu.toItem(key - 1);
					menu.getSelected().execute();
				}else if (key == Keyboard.KEY_RIGHT)
					menu.nextItem();
				else if (key == Keyboard.KEY_LEFT)
					menu.prevItem();
				else if (key == Keyboard.KEY_RETURN) {
					menu.getSelected().execute();
				}
			}
		}
	}
	
}
