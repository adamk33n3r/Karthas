package org.adamk33n3r.karthas;

import org.adamk33n3r.karthas.gui.GUI;
import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.gui.components.Component;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Input {
	
	private static boolean locked;
	
	private static Component lockedBy;
	
	//private static String text = "";
	
	/**
	 * Locks input to this {@code Layer}
	 * @param parent
	 */
	public static void lock(Component parent) {
		locked = true;
		lockedBy = parent;
	}
	
	public static void unlock(Component comp) {
		if (lockedBy == comp) {
			locked = false;
			lockedBy = null;
		}
	}
	
	private static boolean isRestricted(Component owner) {
		return locked && lockedBy != owner;
	}
	
	public static void processMenu(Menu menu, Component owner) throws InputLockedException {
		if (isRestricted(owner))
			throw new InputLockedException("Input is locked by " + lockedBy);
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
							if (!Keyboard.isRepeatEvent())
								menu.getSelected().execute();
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
	
	public static boolean processText(StringBuilder text, Component owner) throws InputLockedException {
		if (isRestricted(owner))
				throw new InputLockedException("Input is locked by " + lockedBy); // TODO: Register input or something

		if (Keyboard.next() && Keyboard.getEventKeyState()) {
			int key = Keyboard.getEventKey();
			switch (key) {
				case Keyboard.KEY_1:
					if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
						text.append('!');
					break;
				case Keyboard.KEY_SLASH:
					if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
						text.append('?');
					break;
				case Keyboard.KEY_SPACE:
					text.append(' ');
					break;
				case Keyboard.KEY_BACK:
					if (text.length() > 0)
						text.delete(text.length() - 1, text.length());
					break;
				case Keyboard.KEY_A:
				case Keyboard.KEY_B:
				case Keyboard.KEY_C:
				case Keyboard.KEY_D:
				case Keyboard.KEY_E:
				case Keyboard.KEY_F:
				case Keyboard.KEY_G:
				case Keyboard.KEY_H:
				case Keyboard.KEY_I:
				case Keyboard.KEY_J:
				case Keyboard.KEY_K:
				case Keyboard.KEY_L:
				case Keyboard.KEY_M:
				case Keyboard.KEY_N:
				case Keyboard.KEY_O:
				case Keyboard.KEY_P:
				case Keyboard.KEY_Q:
				case Keyboard.KEY_R:
				case Keyboard.KEY_S:
				case Keyboard.KEY_T:
				case Keyboard.KEY_U:
				case Keyboard.KEY_V:
				case Keyboard.KEY_W:
				case Keyboard.KEY_X:
				case Keyboard.KEY_Y:
				case Keyboard.KEY_Z:
					text = text.append(Keyboard.getKeyName(key));
					break;
				case Keyboard.KEY_RETURN:
					return true;
				case Keyboard.KEY_ESCAPE:
					text.setLength(0);
					return true;
					
			}

			return false;
		}
		return false;
	}
	
}
