package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.InputLockedException;
import org.adamk33n3r.karthas.gui.GUI;
import org.adamk33n3r.karthas.gui.Graphics;
import org.adamk33n3r.karthas.gui.InputHandler;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

public class ConsoleComponent extends Component {
	
	private String prompt;
	private StringBuilder text;
	
	//private AngelCodeFont font;
	
	private boolean running = true;
	
	public ConsoleComponent(String prompt, StringBuilder text) {
		this.prompt = prompt;
		this.text = text;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public static boolean processText(StringBuilder text, Component owner) throws InputLockedException {
		while (InputHandler.next()) {
			int key = InputHandler.getKey();
			System.out.println("Got key " + key + ":" + Keyboard.getKeyName(key));
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
	
	@Override
	public void update(boolean canHandleInput) {
		try {
			if (canHandleInput && processText(text, this))
				running = false;
		} catch (InputLockedException e) {
			System.out.println("Input is locked");
			e.printStackTrace();
		}
	}
	
	@Override
	public void render() {
		//GUI.renderState();
		Graphics.drawRect(0, 0, GUI.width, GUI.height, new Color(0, 0, 0, 150));
		int centerx = GUI.width / 2;
		int centery = GUI.height / 2;
		Graphics.drawRect(centerx / 4, centery - 30, centerx * 7 / 4, centery, Color.blue, 5, Color.gray);
		Graphics.drawString(centerx / 4 + 10, centery - 27, prompt + ": " + text + "_", Color.green, Graphics.font);
		Graphics.drawString(centerx / 4 + 5, centery + 10, "<ENTER> to Confirm", Color.green, Graphics.font);
		Graphics.drawString(centerx * 7 / 4 - 185, centery + 10, "<ESC> to Cancel", Color.green, Graphics.font);
	}
	
}
