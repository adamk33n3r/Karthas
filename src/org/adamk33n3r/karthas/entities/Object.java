package org.adamk33n3r.karthas.entities;

import org.adamk33n3r.karthas.gui.Graphics;
import org.newdawn.slick.Color;

/**
 * An Object is a subclass of the Entity abstract class; It is the representation of an inanimate object.
 * @author adamk33n3r
 *
 */
public class Object extends Entity{
	
	/**
	 * Version
	 */
	private static final long serialVersionUID = 0L;
	
	private char tile = '^';

	public Object(int posx, int posy, String name){
		super(posx,posy,name);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		Graphics.drawChar(posx, posy, tile, Color.white, Graphics.font);
	}

}
