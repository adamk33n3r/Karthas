package org.adamk33n3r.karthas.gui;

import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class HorizontalMenuItem extends MenuItem {

	public HorizontalMenuItem(String text) {
		super(text);
	}
	
	public HorizontalMenuItem(String text, MenuItemAction function) {
		super(text, function);
	}
	
	@Override
	public void render() {
		Color fontColor = this.fontColor;
		int y = GUI.height / 2;
		int midpoint = GUI.width / parentMenu.items.size();
		int firstpoint = midpoint / 2;
		int size = firstpoint * 6 / 7;
		int x1 = (firstpoint * (1 + position * 2)) - size / 2;
		int x2 = x1 + size;
		if(Resources.get(Resources.IMAGES.horizontalMenuItemBack) == null)
			Resources.load(Resources.IMAGES.horizontalMenuItemBack,((ResizableImage) Resources.get(Resources.IMAGES.RESIZE.componentBack)).build(size, 25));
		GUI.drawImage((Image) Resources.get(Resources.IMAGES.horizontalMenuItemBack), x1, y);
		if(selected) {
			GUI.drawRect(x1 + 5, y + 5, x2, y + 25, this.selectedColor);
			fontColor = this.selectedFontColor;
		}
		GUI.drawStringCentered(x1 + size / 2, y, text, fontColor, GUI.font2);
	}

}