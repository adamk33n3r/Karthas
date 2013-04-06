package org.adamk33n3r.karthas;

import org.adamk33n3r.karthas.gui.components.Component;
import org.lwjgl.input.Keyboard;

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
	
	public static boolean process(String text, Component comp) throws InputLockedException {
		if (locked && lockedBy != comp)
				throw new InputLockedException("Input is locked by " + lockedBy); // TODO: Register input or something

		if (Keyboard.next() && Keyboard.getEventKeyState()) {
			int key = Keyboard.getEventKey();
			switch (key) {
				case Keyboard.KEY_1:
					if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
						text += '!';
					break;
				case Keyboard.KEY_SLASH:
					if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
						text += '?';
					break;
				case Keyboard.KEY_SPACE:
					text += ' ';
					break;
				case Keyboard.KEY_BACK:
					if (text.length() > 0)
						text = text.substring(0, text.length() - 1);
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
					text += Keyboard.getKeyName(key);
					break;
				case Keyboard.KEY_RETURN:
					return true;
				case Keyboard.KEY_ESCAPE:
					text = "";
					return true;
					
			}
			return false;
		}
		return false;
	}
	
}
