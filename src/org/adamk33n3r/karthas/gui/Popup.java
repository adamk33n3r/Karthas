package org.adamk33n3r.karthas.gui;

import org.adamk33n3r.karthas.gui.components.Component;

public class Popup extends Layer {
	
	Layer parent;
	
	Component[] children;
	
	public Popup(Layer parent, Component...componenets) {
		super(componenets);
		children = componenets;
		this.parent = parent;
	}
	
	@Override
	public void update() {
		this.parent.update();
		super.update();
	}
	
	@Override
	public void render() {
		this.parent.render();
		super.render();
	}
	
}
