package org.adamk33n3r.karthas;

/**
 * Interface to implement a render function
 * @author adamk33n3r
 *
 */
public abstract class Renderable {
	
	protected int width;
	protected int height;
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Karthas getSystem() {
		return Karthas.getKarthas();
	}
	
	/**
	 * Function to update
	 */
	public abstract void update();
	
	/**
	 * Function to render
	 */
	public abstract void render();
	
}
