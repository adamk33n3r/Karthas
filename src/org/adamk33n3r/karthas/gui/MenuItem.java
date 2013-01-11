package org.adamk33n3r.karthas.gui;

import org.adamk33n3r.karthas.Executable;
import org.adamk33n3r.karthas.Renderable;
import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

/**
 * A class that defines a menu item that has text and a function associated with it.
 * It is a child of a {@link Menu}
 * @author adamk33n3r
 * 
 */
public class MenuItem implements Renderable, Executable {

	String text = "default";
	int position;
	
	protected Color selectedColor;
	protected Color fontColor, selectedFontColor;
	protected Color disabledColor, borderColor;
	
	boolean selected;
	private boolean disabled;

	private MenuItemAction function = null;

	protected Menu parentMenu = null;
	
	/**
	 * If {@code text} is "Go Back", creates a {@code MenuItem} with a function to go back to the previous menu. Otherwise, makes a {@code MenuItem} with no action
	 * @param text - The text to show in the {@code MenuItem}
	 */
	public MenuItem(String text) {
		this.text = text;
		this.selectedColor = GUI.DEFAULT_SELECTED_MENU_COLOR;
		this.disabledColor = GUI.DEFAULT_DISABLED_COLOR;
		this.borderColor = GUI.DEFAULT_BORDER_COLOR;
		this.fontColor = GUI.DEFAULT_FONT_COLOR;
		this.selectedFontColor = GUI.DEFAULT_SELECTED_FONT_COLOR;
		if (!text.equals("Go Back")) {
			this.function = new MenuItemAction() {
				@Override
				public void execute() {
				}
			};
		} else {
			this.function = new MenuItemAction() {
				@Override
				public void execute() {
					if(parentMenu != null)
						GUI.changeTo(parentMenu.getParent());
				}
			};
		}
	}
	
	public MenuItem(String text, MenuItemAction function) {
		this.text = text;
		this.selectedColor = GUI.DEFAULT_SELECTED_MENU_COLOR;
		this.disabledColor = GUI.DEFAULT_DISABLED_COLOR;
		this.borderColor = GUI.DEFAULT_BORDER_COLOR;
		this.fontColor = GUI.DEFAULT_FONT_COLOR;
		this.selectedFontColor = GUI.DEFAULT_SELECTED_FONT_COLOR;
		this.function = function;
	}
	
	public MenuItem(String text, Color selectedColor, Color disabledColor, Color borderColor, MenuItemAction function) {
		this(text, function);
		this.selectedColor = selectedColor;
		this.disabledColor = disabledColor;
		this.borderColor = borderColor;
	}
	
	public MenuItem(String text, Color selectedColor, Color disabledColor, Color borderColor, Color fontColor, Color selectedFontColor, MenuItemAction function) {
		this(text, selectedColor, disabledColor, borderColor, function);
		this.fontColor = fontColor;
		this.selectedFontColor = selectedFontColor;
	}
	
	/**
	 * Sets the owner of this {@code MenuItem}
	 * @param parent - The parent
	 */
	public void setParent(Menu parent) {
		this.parentMenu = parent;
	}
	
	public void enable() {
		disabled = false;
	}
	
	public void disable() {
		disabled = true;
	}
	
	/**
	 * Renders the {@code MenuItem}
	 */
	@Override
	public void render() {
		Color fontColor = this.selectedFontColor;
		int y = GUI.height - parentMenu.getHeight() - 40 + (position * 35);
		if(disabled)
			GUI.drawRect(50, y, 50 + parentMenu.getWidth(), y + 20, this.disabledColor, 5, this.borderColor);
		else
			//GUI.drawResizableImage((ResizableImage) Resources.get("MenuItemBackScale"), 50, y - 5, 300, 30);
			GUI.drawImage((Image) Resources.get("MenuItemBack"), 50, y - 5);
		if(selected)
			GUI.drawRect(55, y, 55 + parentMenu.getWidth() - 5, y + 25, this.selectedColor);
		else
			fontColor = this.fontColor;
		GUI.drawString(50 + 35, y - 5, text, fontColor, GUI.font);
	}

	/**
	 * Executes the {@code MenuItemAction} associated with this {@code MenuItem}
	 */
	@Override
	public void execute() {
		function.execute();
	}

	@Override
	public void update() {
		
	}

}
