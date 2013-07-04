package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.Area;
import org.adamk33n3r.karthas.entities.*;
import org.adamk33n3r.karthas.gui.GUI;

public class Overworld extends Component {
	
	Area area;
	Human player;
	
	public static int width, height;
	
	/**
	 * New Overworld
	 * @param area - The map
	 * @param player - You
	 */
	public Overworld(Area area, Human player) {
		this.player = player;
		this.area = area;
		this.area.setPlayer(this.player);
	}
	
	@Override
	public void update(boolean canHandleInput) {
		Overworld.width = GUI.width;
		Overworld.height = GUI.height - 200;
		area.update();
	}
	
	@Override
	public void render() {
		area.render();
	}
	
}
