package org.adamk33n3r.karthas.gui;

import org.lwjgl.input.Keyboard;

// Slick imports
import org.newdawn.slick.Color;
import org.newdawn.slick.AngelCodeFont;

import org.adamk33n3r.karthas.Resources;
// My imports
import org.adamk33n3r.karthas.gui.components.ConfirmMenu;

public class Console {

	private static boolean running = true, returnText = true;

	private static String prompt, text = "";
		
	static AngelCodeFont font;

	private Console() {
			
	}

	public static void start(String prompt) {
		running = true;
		Console.prompt = prompt;
		text = "";
	}

	public static boolean isRunning() {
		return running;
	}

	public static String getInput() {
		if (returnText)
			return text;
		return "";
	}
	
	public static boolean confirm() { //TODO idk why this is in console
		ConfirmMenu comp = new ConfirmMenu();
		while(comp.getStatus() == 0) {
			GUI.renderState();
			GUI.drawRect(0, 0, GUI.width, GUI.height, new Color(0, 0, 0, 150)); // Makes background darker
			comp.update();
			comp.render();
			GUI.render(true);
		}
		if (comp.getStatus() == 1)
			return true;
		return false;
	}

	public static void update() {
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
					running = false;
					break;
				case Keyboard.KEY_ESCAPE:
					running = false;
					returnText = false;
					break;
			}
			if (text.length() > 25)
				text = text.substring(0, text.length() - 1);
		}
	}

	public static void render() {
		GUI.renderState();
		GUI.drawRect(0, 0, GUI.width, GUI.height, new Color(0, 0, 0, 150));
		int centerx = GUI.width / 2;
		int centery = GUI.height / 2;
		GUI.drawRect(centerx / 4, centery - 30, centerx * 7 / 4, centery, Color.blue, 5, Color.gray);
		GUI.drawString(centerx / 4 + 10, centery - 27, prompt + ": " + text + "_", Color.green, GUI.font);
		GUI.drawString(centerx / 4 + 5, centery + 10, "<ENTER> to Confirm", Color.green, GUI.font);
		GUI.drawString(centerx * 7 / 4 - 185, centery + 10, "<ESC> to Cancel", Color.green, GUI.font);
		GUI.render(true);
	}

}