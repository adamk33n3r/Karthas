package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.Renderable;

public abstract class Component extends Renderable{
	
	public enum Type {
		MENU,
		TEXT
	}
	
	Type type;
	public Type getType() {
		return type;
	}
}
