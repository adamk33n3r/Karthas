package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.InputAction;
import org.adamk33n3r.karthas.Karthas;
import org.adamk33n3r.karthas.entities.Actor;
import org.adamk33n3r.karthas.gui.GUI;
import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.gui.MenuItem;
import org.adamk33n3r.karthas.gui.MenuItemAction;
import org.adamk33n3r.karthas.gui.Popup;

public class TitleMenu extends MenuComponent {
	
	public TitleMenu() {
		/*if(!input.equals("")) {				// New
		Actor player = Karthas.init(input);
		Karthas.save(player, true);
		System.out.println("dsdfa");
		GUI.addLayer("Main");
	}
	if(!in.equals("")) {					// Load
		Karthas.load(in, true);
		if (Karthas.getPlayer() != null)
			GUI.addLayer("Main");
	}*/
		menu = new Menu(new MenuItem("New Game", new MenuItemAction() {

			@Override
			public void execute() {
				StringBuilder input = new StringBuilder();
				System.out.println("Execute New Game");
				GUI.newConsole("Enter new character name", input, new InputAction() {

					@Override
					public void execute() {
					}

					@Override
					public void execute(StringBuilder input) {
						if(!input.toString().equals("")) {				// New
							Actor player = Karthas.init(input.toString());
							Karthas.save(player, true);
							System.out.println("dsdfa");
							GUI.addLayer("Main");
						}
					}
					
				});
			}
		}), new MenuItem("Load Game", new MenuItemAction() {

			@Override
			public void execute() {
				StringBuilder input = new StringBuilder();
				GUI.newConsole("Enter character to load", input, new InputAction() {

					@Override
					public void execute() {
					}

					@Override
					public void execute(StringBuilder input) {
						if(!input.toString().equals("")) {					// Load
							Karthas.load(input.toString(), true);
							if (Karthas.getPlayer() != null)
								GUI.addLayer("Main");
							else
								GUI.addLayer(new Popup(GUI.getCurrentLayer(), new TextComponent("Player does not exist", GUI.width / 2, GUI.height / 2, true)));
						}
					}
					
				});
			}
			
		}), new MenuItem("Quit", MenuItemAction.EXIT));
	}

}
