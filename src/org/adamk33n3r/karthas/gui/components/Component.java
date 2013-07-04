package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.Renderable;
import org.adamk33n3r.karthas.gui.InputHandler;
import org.adamk33n3r.karthas.gui.Layer;

public abstract class Component extends Renderable {
	
	Layer parent;
	
	public void setLayer(Layer parent) {
		this.parent = parent;
	}
	InputHandler inputHandler;
	
//	public boolean registerInputHandler() {
//		return this.parent.registerInputHandler(inputHandler);
//	}
	
	public enum Type {
		MENU, TEXT
	}
	
	Type type;
	
	public Type getType() {
		return type;
	}
	
	@Override
	public void update() {
		this.update(true);
	}
	
	public abstract void update(boolean canHandleInput);
}
