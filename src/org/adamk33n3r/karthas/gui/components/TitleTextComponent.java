package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.gui.GUI;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class TitleTextComponent extends TextComponent{
	
	String text;
	
	public TitleTextComponent() {
		text = "Welcome to Karthas!";
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		int size = GUI.font.getWidth(text);
		int x1 = GUI.width / 2 - size / 2;
		Resources.set("TitleTextBack", ((ResizableImage) Resources.get("ComponentBack")).build(size + 25, GUI.font.getLineHeight()));
		GUI.drawImage((Image) Resources.get("TitleTextBack"), x1 - 15, 94);
		GUI.drawStringCentered(x1 + size / 2, 100, text, Color.yellow, GUI.font);
	}

}
