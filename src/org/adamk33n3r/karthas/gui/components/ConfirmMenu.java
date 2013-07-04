package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.gui.HorizontalMenuItem;
import org.adamk33n3r.karthas.gui.Layer;
import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.gui.MenuItemAction;

public class ConfirmMenu extends HorizontalMenuComponent {
	
	
	public ConfirmMenu() {
		menu = new Menu(new HorizontalMenuItem("No", new MenuItemAction() {

			@Override
			public void execute() {
				//return -1;
			}
			
		}), new HorizontalMenuItem("Maybe"), new HorizontalMenuItem("Yes", new MenuItemAction() {

			@Override
			public void execute() {
				//return 1;
			}
			
		}));
	}

	@Override
	public void update(boolean canHandleInput) {
		
	}
	
}
