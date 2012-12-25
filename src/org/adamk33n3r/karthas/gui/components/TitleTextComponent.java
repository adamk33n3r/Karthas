package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.gui.GUI;
import org.newdawn.slick.Color;

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
		GUI.drawStringCentered(GUI.width / 2, 100, text, Color.yellow, GUI.font);
	}

}
