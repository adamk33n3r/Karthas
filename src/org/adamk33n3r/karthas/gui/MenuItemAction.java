package org.adamk33n3r.karthas.gui;

import org.adamk33n3r.karthas.Executable;

/**
 * An interface for making actions for the menu items on {@code MenuItem} creation
 * @author adamk33n3r
 *
 */

public interface MenuItemAction extends Executable {
	
	// Some pre-defined MenuItem actions
	static final MenuItemAction GO_BACK = new MenuItemAction() {

		@Override
		public void execute() {
			System.out.println("Going back a menu layer");
			GUI.goBack();
		}
		
	};
	
	static final MenuItemAction EXIT = new MenuItemAction() {

		@Override
		public void execute() {
			if(confirm()) {
				System.out.println("Exiting...");
				GUI.shutdown();
			}
		}
		
		private boolean confirm() {
			return Console.confirm();
		}

	};
}
