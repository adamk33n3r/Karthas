package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.gui.GUI;
import org.newdawn.slick.Image;

public class CenteredTextComponent extends Component {
	
	String text, name;
	int y, distance, width;
	boolean yFromBottom;
	
	public CenteredTextComponent(String text, int y, boolean yFromBottom) {
		this.text = text;
		this.yFromBottom = yFromBottom;
		if (yFromBottom)
			this.y = GUI.height - y;
		else
			this.y = y;
		this.width = GUI.font.getWidth(text);
		this.name = this.text.replaceAll("\\s","");
		this.name = this.name.replaceAll("\\W","");
		this.name = this.name.toLowerCase();
		Resources.load(Resources.IMAGES.valueOf(name), ((ResizableImage) Resources.get(Resources.IMAGES.RESIZE.componentBack)).build(width + 25, GUI.font.getLineHeight()));

	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		int x1 = (GUI.width - width) / 2;
		GUI.drawImage((Image) Resources.get(Resources.IMAGES.valueOf(this.name)), x1 - 15, y - 6);
		GUI.drawString(x1, y, text, GUI.DEFAULT_FONT_COLOR, GUI.font);
	}

}
