package org.adamk33n3r.karthas.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.adamk33n3r.karthas.Renderable;
import org.adamk33n3r.karthas.gui.components.Component;
import org.adamk33n3r.karthas.gui.components.Component.Type;
import org.adamk33n3r.karthas.gui.components.MenuComponent;

public class Instance extends Renderable{
		
	HashMap<Component.Type, Component> components;
	
	public Instance(Component... components) {
		this.components = new HashMap<Component.Type, Component>();
		for(int i = 0; i< components.length; i++) {
			this.components.put(components[i].getType(), components[i]);
		}
	}

	public Menu getMenu() {
		return ((MenuComponent) components.get(Component.Type.MENU)).getMenu();
	}

	@Override
	public void update() {
		for(Iterator<Entry<Type, Component>> it = components.entrySet().iterator(); it.hasNext();)
			it.next().getValue().update();
	}

	@Override
	public void render() {
		for(Iterator<Entry<Type, Component>> it = components.entrySet().iterator(); it.hasNext();)
			it.next().getValue().render();
	}
}
