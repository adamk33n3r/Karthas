package org.adamk33n3r.karthas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.adamk33n3r.karthas.entities.Actor;
import org.adamk33n3r.karthas.entities.Human;
import org.adamk33n3r.karthas.entities.Object;
import org.adamk33n3r.karthas.gui.components.Overworld;

public class Area extends Renderable {
	
	Human player;
	ArrayList<Actor> npcs;
	ArrayList<Object> objects;
	
	public void setPlayer(Human player) {
		this.player = player;
	}
	
	public void setNPCs(Actor...npcs) {
		this.npcs = new ArrayList<Actor>(Arrays.asList(npcs));
	}
	
	public void setObjects(Object...objects) {
		this.objects = new ArrayList<Object>(Arrays.asList(objects));
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void render() {
		Iterator<Actor> aIt = npcs.iterator();
		Iterator<Object> oIt = objects.iterator();
		while(aIt.hasNext()) {
			Actor npc = aIt.next();
			if (npc.isInBounds(Overworld.width, Overworld.height))
				npc.render();
		}
		while(oIt.hasNext()) {
			Object obj = oIt.next();
			if (obj.isInBounds(Overworld.width, Overworld.height))
				obj.render();
		}
		if (player.isInBounds(Overworld.width, Overworld.height))
			player.render();
	}
	
}
