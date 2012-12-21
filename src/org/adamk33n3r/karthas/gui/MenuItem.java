package org.adamk33n3r.karthas.gui;

import org.adamk33n3r.karthas.Executable;
import org.adamk33n3r.karthas.Renderable;
import org.newdawn.slick.Color;

/**
 * A class that defines a menu item that has text and a function associated with it.
 * It is a child of a {@link Menu}
 * @author adamk33n3r
 * 
 */
public class MenuItem implements Renderable, Executable {

	String text = "default";
	int position;

	private MenuItemAction function = null;

	private Menu parentMenu = null;
	
	/**
	 * If {@code text} is "Go Back", creates a {@code MenuItem} with a function to go back to the previous menu. Otherwise, makes a {@code MenuItem} with no action
	 * @param text - The text to show in the {@code MenuItem}
	 */
	public MenuItem(String text) {
		this.text = text;
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

	/**
	 * Creates a new {@code MenuItem}
	 * @param text - The text to show in the {@code MenuItem}
	 * @param function - The function that by pressing the return key on the {@code MenuItem} should be executed
	 */
	public MenuItem(String text, MenuItemAction function) {
		this.text = text;
		this.function = function;
	}

	/**
	 * Sets the function for this {@code MenuItem}
	 * @param function - The function to be set
	 */
	public void setFunction(MenuItemAction function) {
		this.function = function;
	}
	
	/**
	 * Sets the owner of this {@code MenuItem}
	 * @param parent - The parent
	 */
	public void setParent(Menu parent) {
		this.parentMenu = parent;
	}
	
	/**
	 * Renders the {@code MenuItem}
	 */
	@Override
	public void render() {
		int y = GUI.height - parentMenu.height - 40 + (position * 25);
		GUI.drawString(50 + 5, y - 2, text, Color.yellow, GUI.font);
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
