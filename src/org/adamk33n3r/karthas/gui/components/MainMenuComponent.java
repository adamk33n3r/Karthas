package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.gui.MenuItem;
import org.adamk33n3r.karthas.gui.MenuItemAction;

public class MainMenuComponent extends MenuComponent {
	
	public MainMenuComponent() {
		menu = new Menu(null, new MenuItem("Attack"), new MenuItem("Save"), new MenuItem("Quit", MenuItemAction.EXIT));
	}

}
