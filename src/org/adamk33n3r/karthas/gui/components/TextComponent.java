package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.gui.Graphics;
import org.newdawn.slick.Image;

public class TextComponent extends Component{
	
	String text, name;
	int x, y, width;
	boolean centered;
	
	public TextComponent(String text, int x, int y, boolean centered) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.centered = centered;
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
		Graphics.drawImage((Image) Resources.get(Resources.IMAGES.valueOf(name)), x - width / 2 - 15, y - 6);
		if (centered)
			Graphics.drawStringCentered(x, y, text, Graphics.DEFAULT_FONT_COLOR, Graphics.font);
		else
			Graphics.drawString(x, y, text, Graphics.DEFAULT_FONT_COLOR, Graphics.font);
	}
	
}
