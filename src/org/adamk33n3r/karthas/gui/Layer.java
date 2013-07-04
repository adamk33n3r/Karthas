package org.adamk33n3r.karthas.gui;

import java.util.ArrayList;

import org.adamk33n3r.karthas.Renderable;
import org.adamk33n3r.karthas.gui.components.Component;

public class Layer extends Renderable {
	
	private ArrayList<Component> components;
	
	public Layer(Component... components) {
		this.components = new ArrayList<Component>();
		for (int i = 0; i < components.length; i++) {
			components[i].setLayer(this);
			this.components.add(components[i]);
		}
	}
	
	/**
	 * Don't call this
	 */
	@Override
	public void update() {
		this.update(true);
	}
	
	public void update(boolean canHandleInput) {
		for (Component comp : this.components) {
			comp.update(canHandleInput);
		}
	}
	
	@Override
	public void render() {
		for (Component comp : this.components) {
			comp.render();
		}
	}

}
