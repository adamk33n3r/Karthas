package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.Input;
import org.adamk33n3r.karthas.InputLockedException;
import org.adamk33n3r.karthas.gui.GUI;
import org.adamk33n3r.karthas.gui.Layer;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;

public class ConsoleComponent extends Component {
	
	private String prompt, text;
	
	//private AngelCodeFont font;
	
	private boolean running = true;
	
	public ConsoleComponent(String prompt, String text, Layer parent) {
		//super(parent);
		this.prompt = prompt;
		this.text = text;
		Input.lock(this);
	}
	
	public boolean isRunning() {
		return running;
	}
	
	@Override
	public void update() {
		try {
			if (Input.process(text, this))
				running = false;
		} catch (InputLockedException e) {
			System.out.println("Input is locked");
			e.printStackTrace();
		}
	}
	
	@Override
	public void render() {
		//GUI.renderState();
		GUI.drawRect(0, 0, GUI.width, GUI.height, new Color(0, 0, 0, 150));
		int centerx = GUI.width / 2;
		int centery = GUI.height / 2;
		GUI.drawRect(centerx / 4, centery - 30, centerx * 7 / 4, centery, Color.blue, 5, Color.gray);
		GUI.drawString(centerx / 4 + 10, centery - 27, prompt + ": " + text + "_", Color.green, GUI.font);
		GUI.drawString(centerx / 4 + 5, centery + 10, "<ENTER> to Confirm", Color.green, GUI.font);
		GUI.drawString(centerx * 7 / 4 - 185, centery + 10, "<ESC> to Cancel", Color.green, GUI.font);
	}
	
}
