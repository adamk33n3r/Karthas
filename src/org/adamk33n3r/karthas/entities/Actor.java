package org.adamk33n3r.karthas.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import org.adamk33n3r.karthas.gui.GUI;
import org.newdawn.slick.Color;

/**
 * An Actor is a subclass of the Entity abstract class; It is the representation of a person or monster.
 * 
 * @author adamk33n3r
 */

public class Actor extends Entity {
	
	private static final long serialVersionUID = -3138869789221274698L;
	private static final int NUM_OF_FIELDS = 4;
	private int version = 4;
	private String prefix;
	private HashMap<String, Integer> attributes;
	
	/**
	 * Makes a new {@code Actor}
	 * @param posx Position along x-axis
	 * @param posy Position along y-axis
	 * @param i {@code Actor}'s name.
	 * @param j {@code Actor}'s name prefix.
	 * @param att {@code Actor}'s attack attribute.
	 * @param str {@code Actor}'s strength attribute.
	 * @param def {@code Actor}'s defense attribute.
	 * @param arm {@code Actor}'s armor attribute.
	 */
	public Actor(int posx, int posy, String name, String prefix, int att, int str, int def, int arm) {
		super(posx, posy, name);
		this.prefix = prefix;
		attributes = new HashMap<String, Integer>();
		attributes.put("att", att);
		attributes.put("str", str);
		attributes.put("def", def);
		attributes.put("arm", arm);
	}
	
	/**
	 * Moves the Actor up
	 */
	public void moveUp() {
		this.posy-=10;
	}
	
	/**
	 * Moves the Actor down
	 */
	public void moveDown() {
		this.posy+=10;
	}
	
	/**
	 * Moves the Actor left
	 */
	public void moveLeft() {
		this.posx-=10;
	}
	
	/**
	 * Moves the Actor right
	 */
	public void moveRight() {
		this.posx+=10;
	}
	
	/**
	 * @return String - The prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	
	/**
	 * @param prefix - Sets prefix to this
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	/**
	 * @return int - The attack
	 */
	public int getAtt() {
		return attributes.get("att");
	}
	
	/**
	 * @param att - Sets attack value to this
	 */
	public void setAtt(int att) {
		attributes.put("att", att);
	}
	
	/**
	 * @return int - The strength
	 */
	public int getStr() {
		return attributes.get("str");
	}
	
	/**
	 * @param str - Sets strength value to this
	 */
	public void setStr(int str) {
		attributes.put("str", str);
	}
	
	/**
	 * @return int - The defense
	 */
	public int getDef() {
		return attributes.get("def");
	}
	
	/**
	 * @param def - Sets defense value to this
	 */
	public void setDef(int def) {
		attributes.put("def", def);
	}
	
	/**
	 * @return int - The armor
	 */
	public int getArm() {
		return attributes.get("arm");
	}
	
	/**
	 * @param arm - Sets armor value to this
	 */
	public void setArm(int arm) {
		attributes.put("arm", arm);
	}
	
	/**
	 * My own implementation of {@code readObject} in order to resolve conflicting versions of serialized objects
	 * @param ois - {@code ObjectInputStream}
	 */
	
	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			System.out.println(this.version + " compared to " + Actor.NUM_OF_FIELDS + " fields");
			if (this.version < Actor.NUM_OF_FIELDS) {
				System.err.println("Old version");
				GUI.shutdown();
			} else if (this.version > Actor.NUM_OF_FIELDS) {
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ois.defaultReadObject();
	}
	
	@Override
	public String toString() {
		return String.format("%s %s: Att-%d, Str-%d, Def-%d, Arm-%d", prefix, name, attributes.get("att"), attributes.get("str"), attributes.get("def"),
				attributes.get("arm"));
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void render() {
		GUI.drawString(posx, posy, "#", Color.cyan, GUI.font);
	}
	
}
