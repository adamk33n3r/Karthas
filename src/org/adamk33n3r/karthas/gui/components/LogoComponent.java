package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.gui.GUI;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LogoComponent extends Component {
	
	private Image logo;
	private int alpha = 255;
	private int direction = -1;
	
	public LogoComponent(String pathToImage) {
		/*try {
			logo = new Image(pathToImage);
		} catch (SlickException e) {
			System.err.println("Could not find image");
			e.printStackTrace();
		}*/
	}
	
	@Override
	public void update() {
		alpha+=direction;
		if(alpha == 0)
			direction = 1;
		else if(alpha == 255)
			direction = -1;
	}

	@Override
	public void render() {
		//logo.setAlpha(alpha);
		//logo.drawCentered(GUI.width / 2, GUI.height / 2);
		//GUI.drawRect((GUI.width - logo.getWidth()) / 2, (GUI.height - logo.getHeight()) / 2, (GUI.width + logo.getWidth()) / 2, (GUI.height + logo.getHeight()) / 2, new Color(0, 0, 0, 150));
	}

}
