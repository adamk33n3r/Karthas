package org.adamk33n3r.karthas.gui;

// LWJGL imports
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

public class Console {

	private static boolean running = true;

	private static String text = "";

	private Console() {
	}

	public static void start() {
		running = true;
	}

	public static boolean isRunning() {
		return running;
	}

	public static void update() {
		if (Keyboard.next() && Keyboard.getEventKeyState()) {
			int key = Keyboard.getEventKey();
			if (key == Keyboard.KEY_RETURN) {
				running = false;
			} else {
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
						if(text.length() > 0)
							text = text.substring(0, text.length() - 1);
						break;
					case Keyboard.KEY_A:
						text += 'A';
						break;
					case Keyboard.KEY_B:
						text += 'B';
						break;
					case Keyboard.KEY_C:
						text += 'C';
						break;
					case Keyboard.KEY_D:
						text += 'D';
						break;
					case Keyboard.KEY_E:
						text += 'E';
						break;
					case Keyboard.KEY_F:
						text += 'F';
						break;
					case Keyboard.KEY_G:
						text += 'G';
						break;
					case Keyboard.KEY_H:
						text += 'H';
						break;
					case Keyboard.KEY_I:
						text += 'I';
						break;
					case Keyboard.KEY_J:
						text += 'J';
						break;
					case Keyboard.KEY_K:
						text += 'K';
						break;
					case Keyboard.KEY_L:
						text += 'L';
						break;
					case Keyboard.KEY_M:
						text += 'M';
						break;
					case Keyboard.KEY_N:
						text += 'N';
						break;
					case Keyboard.KEY_O:
						text += 'O';
						break;
					case Keyboard.KEY_P:
						text += 'P';
						break;
					case Keyboard.KEY_Q:
						text += 'Q';
						break;
					case Keyboard.KEY_R:
						text += 'R';
						break;
					case Keyboard.KEY_S:
						text += 'S';
						break;
					case Keyboard.KEY_T:
						text += 'T';
						break;
					case Keyboard.KEY_U:
						text += 'U';
						break;
					case Keyboard.KEY_V:
						text += 'V';
						break;
					case Keyboard.KEY_W:
						text += 'W';
						break;
					case Keyboard.KEY_X:
						text += 'X';
						break;
					case Keyboard.KEY_Y:
						text += 'Y';
						break;
					case Keyboard.KEY_Z:
						text += 'Z';
						break;
				}
			}
		}
	}

	public static void render() {
		int centerx = GUI.width / 2;
		int centery = GUI.height / 2;
		GUI.drawRect(centerx / 4, centery - 40, centerx * 7 / 4, centery, Color.blue);
		GUI.drawString(centerx / 4 + 10, centery - 30, "Enter Text Here: " + text + "_", Color.green, GUI.font);
		GUI.render();
	}

}