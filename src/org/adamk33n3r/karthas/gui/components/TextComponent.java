package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.gui.GUI;
import org.newdawn.slick.Image;

public class TextComponent extends Component{
	
	String text;
	int x, y;
	boolean centered;
	
	public TextComponent(String text, int x, int y, boolean centered) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.centered = centered;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		int size = GUI.font.getWidth(text);
		if (Resources.get(text) == null)
			Resources.set(text, ((ResizableImage) Resources.get("ComponentBack")).build(size + 25, GUI.font.getLineHeight()));
		GUI.drawImage((Image) Resources.get(text), x - size / 2 - 15, y - 6);
		if (centered)
			GUI.drawStringCentered(x, y, text, GUI.DEFAULT_FONT_COLOR, GUI.font);
		else
			GUI.drawString(x, y, text, GUI.DEFAULT_FONT_COLOR, GUI.font);
	}
	
}
