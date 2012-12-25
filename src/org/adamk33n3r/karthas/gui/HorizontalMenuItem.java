package org.adamk33n3r.karthas.gui;

import java.awt.Point;

import org.newdawn.slick.Color;

public class HorizontalMenuItem extends MenuItem {

	public HorizontalMenuItem(String text) {
		super(text);
	}
	
	public HorizontalMenuItem(String text, MenuItemAction function) {
		super(text, function);
	}
	
	@Override
	public void render() {
		int y = GUI.height / 2;
		int midpoint = GUI.width / parentMenu.items.size();
		int firstpoint = midpoint / 2;
		int size = firstpoint * 6 / 7;
		int x1 = (firstpoint * (1 + position * 2));
		int x2 = x1 + size / 2;
		GUI.drawRect(x1 - size / 2, y, x2, y + 20, Color.blue, 5, Color.gray);
		if(selected)
			GUI.drawPolygon(Color.red, new Point(x1 - size / 2, y), new Point(x1 + size / 2 - 20, y), new Point(x1 + size / 2, y + 20), new Point(x1 - size / 2 + 20, y + 20));
		GUI.drawStringCentered(x1, y - 6, text, Color.yellow, Console.font);
	}

}