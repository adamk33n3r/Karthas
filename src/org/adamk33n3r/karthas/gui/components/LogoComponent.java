package org.adamk33n3r.karthas.gui.components;

// Slick Image import
import org.newdawn.slick.Image;

// My import
import org.adamk33n3r.karthas.gui.GUI;
import org.adamk33n3r.karthas.gui.Graphics;

public class LogoComponent extends Component {
	
	private Image logo;
	private int alpha = 255;
	private double direction = -600;
	private double speed = 600;
	
	public LogoComponent(Image image) {
		logo = image;
	}
	
	@Override
	public void update(boolean canHandleInput) {
		double delta = GUI.getDelta();
		double change = direction * delta / 1000;
		//System.out.println(String.format("Delta:\t%s\tChange:\t%s\tAlpha:\t%s", delta, change, alpha));

		alpha+=change;
		if (alpha < 100)
			direction = speed;
		else if (alpha > 255)
			direction = -speed;

	}

	@Override
	public void render() {
		logo.setAlpha(alpha / 255f);
		Graphics.drawImage(logo, GUI.width / 2 - logo.getWidth() / 2, GUI.height / 2 - logo.getHeight() / 2);
	}

}
