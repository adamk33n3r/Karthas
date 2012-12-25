package org.adamk33n3r.karthas.gui;

// LWJGL imports
import org.lwjgl.input.Keyboard;

// Slick imports
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AngelCodeFont;

// My imports
import org.adamk33n3r.karthas.gui.components.ConfirmMenuComponent;

public class Console {

	private static boolean running = true, returnText = true;

	private static String prompt, text = "";
	
	private static Console con;
	
	static AngelCodeFont font;

	private Console() {
		 try {
			font = new AngelCodeFont("resources/Chalkduster20.fnt", new Image("resources/Chalkduster20.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public static void start(String prompt) {
		if(con == null)
			con = new Console();
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
	
	public static boolean confirm() {
		Console.start("");
		ConfirmMenuComponent comp = new ConfirmMenuComponent();
		while(comp.getStatus() == 0) {
			GUI.renderState();
			GUI.drawRect(0, 0, GUI.width, GUI.height, new Color(0, 0, 0, 150));
			comp.update();
			comp.render();
			GUI.render(true);
		}
		if (comp.getStatus() == 1)
			return true;
		return false;
	}

	public static void update() {
		Keyboard.enableRepeatEvents(true);
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
			Keyboard.enableRepeatEvents(false);
		}
	}

	public static void render() {
		GUI.renderState();
		GUI.drawRect(0, 0, GUI.width, GUI.height, new Color(0, 0, 0, 150));
		int centerx = GUI.width / 2;
		int centery = GUI.height / 2;
		GUI.drawRect(centerx / 4, centery - 30, centerx * 7 / 4, centery, Color.blue, 5, Color.gray);
		GUI.drawString(centerx / 4 + 10, centery - 27, prompt + ": " + text + "_", Color.green, font);
		GUI.drawString(centerx / 4 + 5, centery + 10, "<ENTER> to Confirm", Color.green, font);
		GUI.drawString(centerx * 7 / 4 - 185, centery + 10, "<ESC> to Cancel", Color.green, font);
		GUI.render(true);
	}

}