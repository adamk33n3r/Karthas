package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.gui.MenuItem;
import org.adamk33n3r.karthas.gui.MenuItemAction;

public class MainMenu extends MenuComponent {
	
	public MainMenu() {
		menu = new Menu(new MenuItem("Attack"), new MenuItem("Save"), new MenuItem("Quit", MenuItemAction.EXIT));
	}

}
