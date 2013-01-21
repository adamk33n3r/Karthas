package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.gui.GUI;
import org.newdawn.slick.Image;

public class CenteredTextComponent extends Component {
	
	String text;
	int y, distance;
	boolean yFromBottom;
	
	public CenteredTextComponent(String text, int y, boolean yFromBottom) {
		this.text = text;
		this.y = y;
		this.yFromBottom = yFromBottom;
		if (yFromBottom)
			this.distance = y;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		int size = GUI.font.getWidth(text);
		if (yFromBottom)
			y = GUI.height - distance;
		int x1 = GUI.width / 2 - size / 2;
		if (Resources.get(text) == null)
			Resources.set(text, ((ResizableImage) Resources.get("ComponentBack")).build(size + 25, GUI.font.getLineHeight()));
		GUI.drawImage((Image) Resources.get(text), x1 - 15, y - 6);
		GUI.drawString(x1, y, text, GUI.DEFAULT_FONT_COLOR, GUI.font);
	}

}
