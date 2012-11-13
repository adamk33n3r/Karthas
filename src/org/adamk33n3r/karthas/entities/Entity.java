package org.adamk33n3r.karthas.entities;

public abstract class Entity {
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

	public abstract String toString();
}
