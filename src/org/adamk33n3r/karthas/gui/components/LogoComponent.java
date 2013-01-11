package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.gui.GUI;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LogoComponent extends Component {
	
	private Image logo;
	private int alpha = 255;
	private double direction = -600;
	private double speed = 600;
	
	public LogoComponent(String pathToImage) {
		try {
			logo = new Image(pathToImage);
		} catch (SlickException e) {
			System.err.println("Could not find image");
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		double delta = GUI.getDelta();
		double change = direction * delta / 1000;
		alpha+=change;
		if (alpha < 100)
			direction = speed;
		else if (alpha > 255)
			direction = -speed;
	}

	@Override
	public void render() {
		logo.setAlpha(alpha / 255f);
		GUI.drawImage(logo, GUI.width / 2 - logo.getWidth() / 2, GUI.height / 2 - logo.getHeight() / 2);
	}

}
