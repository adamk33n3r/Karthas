package org.adamk33n3r.karthas.gui;

import java.util.HashMap;

public class MenuCreator {
	
	
	private static HashMap<String,Menu> menuMap;
	private MenuCreator(){}
	
	/**
	 * Creates the menus needed for Karthas
	 * @return Returns the {@code HashMap} of all of the menus
	 */
	public static HashMap<String, Menu> create() {
		
		// The HashMap to house the menus
		menuMap = new HashMap<String,Menu>();
		
		
		// Title menu
		menuMap.put("Main", new Menu(null, new MenuItem("New Game", new MenuItemAction() {

			@Override
			public void execute() {
				System.out.println("Create a new character");
				GUI.changeTo("New");
			}
			
		}), new MenuItem("Load Game", new MenuItemAction() {

			@Override
			public void execute() {
				System.out.println("Load your character");
				GUI.changeTo("Load");
			}
			
		}), new MenuItem("Quit", MenuItemAction.EXIT)));
		
		
		// New menu
		menuMap.put("New", new Menu(menuMap.get("Main"), new MenuItem("Confirm"), new MenuItem("Go Back")));
		
		// Load menu
		menuMap.put("Load", new Menu(menuMap.get("Main"), new MenuItem("Load", new MenuItemAction() {

			@Override
			public void execute() {
				GUI.getInput();
			}
			
		}), new MenuItem("Go Back")));
		
		return menuMap;
	}
}
