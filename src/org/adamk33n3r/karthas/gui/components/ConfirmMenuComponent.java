package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.gui.HorizontalMenuItem;
import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.gui.MenuItemAction;

public class ConfirmMenuComponent extends HorizontalMenuComponent {
	
	int status = 0;
	
	public ConfirmMenuComponent() {
		menu = new Menu(new HorizontalMenuItem("No", new MenuItemAction() {

			@Override
			public void execute() {
				status = -1;
			}
			
		}), new HorizontalMenuItem("Maybe"), new HorizontalMenuItem("Yes", new MenuItemAction() {

			@Override
			public void execute() {
				status = 1;
			}
			
		}));
	}
	
	public int getStatus() {
		return status;
	}
	
}
