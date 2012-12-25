package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.Renderable;

public abstract class Component implements Renderable{
	
	//public static final String MENU_TYPE = "Menu";
	//public static final String TEXT_TYPE = "Text";
	
	public enum Type {
		MENU,
		TEXT
	}
	
	Type type;
	public Type getType() {
		return type;
	}
}
