package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.gui.GUI;
import org.adamk33n3r.karthas.gui.Graphics;
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
		this.width = Graphics.font.getWidth(text);
		this.name = this.text.replaceAll("\\s","");
		this.name = this.name.replaceAll("\\W","");
		this.name = this.name.toLowerCase();
		Resources.load(Resources.IMAGES.valueOf(name), ((ResizableImage) Resources.get(Resources.IMAGES.RESIZE.componentBack)).build(width + 25, Graphics.font.getLineHeight()));

	}

	@Override
	public void update(boolean canHandleInput) {
		
	}
	
	@Override
	public void render() {
		int x1 = (GUI.width - width) / 2;
		Graphics.drawImage((Image) Resources.get(Resources.IMAGES.valueOf(this.name)), x1 - 15, y - 6);
		Graphics.drawString(x1, y, text, Graphics.DEFAULT_FONT_COLOR, Graphics.font);
	}

}
