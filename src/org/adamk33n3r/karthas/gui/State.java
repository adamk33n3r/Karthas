package org.adamk33n3r.karthas.gui;

import org.adamk33n3r.karthas.Renderable;

import java.util.ArrayList;

public class State extends Renderable {
	
	private ArrayList<Layer> layers;
	private Layer curLayer;
	
	public State() {
		this.layers = new ArrayList<Layer>();
	}
	
	public State addLayer(Layer layer) {
		System.out.println("Adding a layer. Size: " + this.layers.size() + 1);
		this.layers.add(layer);
		this.curLayer = layer;
		return this;
	}
	
	public void setCurrentLayer(int index) {
		this.curLayer = layers.get(index);
	}
	
	@Override
	public void update() {
		if (this.curLayer instanceof Popup) {
			this.layers.get(this.layers.size() - 2).update(false);
		}
		this.curLayer.update(true);
	}
	
	@Override
	public void render() {
		if (this.curLayer instanceof Popup) {
			this.layers.get(this.layers.size() - 2).render();
		}
		this.curLayer.render();
	}
}
