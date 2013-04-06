package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.gui.GUI;
import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.gui.MenuItem;
import org.adamk33n3r.karthas.gui.MenuItemAction;

public class TitleMenu extends MenuComponent {
	
	public TitleMenu() {
		menu = new Menu(new MenuItem("New Game", new MenuItemAction() {

			@Override
			public void execute() {
				String input = "";
				GUI.newConsole("Enter new character name", input);
			}
		}), new MenuItem("Load Game", new MenuItemAction() {

			@Override
			public void execute() {
				String input = "";
				GUI.newConsole("Enter character to load", input);
			}
			
		}), new MenuItem("Quit", MenuItemAction.EXIT));
	}

}
