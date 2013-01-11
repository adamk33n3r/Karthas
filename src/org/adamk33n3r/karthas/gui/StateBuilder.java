package org.adamk33n3r.karthas.gui;

import java.util.HashMap;

import org.adamk33n3r.karthas.gui.components.*;

public class StateBuilder {
	
	private static HashMap<String, State> stateMap;
	
	private StateBuilder(){}
	
	public static HashMap<String, State> create() {
		
		stateMap = new HashMap<String, State>();
		
		stateMap.put("Title", new State(new LogoComponent("resources/MenuItemBack.png"), new TitleMenuComponent(), new TitleTextComponent()));
		
		stateMap.put("Main", new State(new MainMenuComponent()));
		
		return stateMap;
	}
	
}
