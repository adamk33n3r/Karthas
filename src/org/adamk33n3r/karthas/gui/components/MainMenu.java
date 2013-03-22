package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.entities.Human;
import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.gui.MenuItem;
import org.adamk33n3r.karthas.gui.MenuItemAction;

public class MainMenu extends OverworldMenuComponent {
	
	public MainMenu(Human player) {
		super(player);
		menu = new Menu(new MenuItem("Attack"), new MenuItem("Save"), new MenuItem("Quit", MenuItemAction.EXIT));
	}

}
