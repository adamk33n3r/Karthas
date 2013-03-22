package org.adamk33n3r.karthas.entities;

import org.adamk33n3r.karthas.Renderable;

/**
 * Entity is the superclass of the entities within Karthas
 * @author adamk33n3r
 *
 */

public abstract class Entity extends Renderable implements java.io.Serializable{
	/**
	 * Version
	 */
	private static final long serialVersionUID = 0L;
	protected int posx, posy;
	protected String name;

	public Entity(int posx, int posy, String name) {
		this.posx = posx;
		this.posy = posy;
		this.name = name;
	}
	
	/**
	 * @return the posx
	 */
	public int getPosx() {
		return posx;
	}

	/**
	 * @param posx the posx to set
	 */
	public void setPosx(int posx) {
		this.posx = posx;
	}

	/**
	 * @return the posy
	 */
	public int getPosy() {
		return posy;
	}

	/**
	 * @param posy the posy to set
	 */
	public void setPosy(int posy) {
		this.posy = posy;
	}
	
	/**
	 * Returns true if Entity is within the bounds from 0 -> width and 0 -> height
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean isInBounds(int width, int height) {
		return this.posx < width && this.posx >= 0 && this.posy < height && this.posy >= 0;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public abstract String toString();
}
