package org.adamk33n3r.karthas.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.adamk33n3r.karthas.Renderable;
import org.adamk33n3r.karthas.gui.components.Component;
import org.adamk33n3r.karthas.gui.components.MenuComponent;

public class State implements Renderable{
	
	ArrayList<Component> components;
	
	HashMap<Component.Type, Component> compMap;
	
	public State(Component... components) {
		this.components = new ArrayList<Component>();
		this.compMap = new HashMap<Component.Type, Component>();
		for(int i = 0; i< components.length; i++) {
			this.components.add(components[i]);
			this.compMap.put(components[i].getType(), components[i]);
		}
	}

	public Menu getMenu() {
		return ((MenuComponent) compMap.get(Component.Type.MENU)).getMenu();
	}

	@Override
	public void update() {
		for(Iterator<Component> it = components.iterator(); it.hasNext();)
			it.next().update();
	}

	@Override
	public void render() {
		for(Iterator<Component> it = components.iterator(); it.hasNext();)
			it.next().render();
	}
}
