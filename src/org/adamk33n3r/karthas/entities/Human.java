package org.adamk33n3r.karthas.entities;

import java.util.ArrayList;

import org.adamk33n3r.karthas.gui.Graphics;
import org.newdawn.slick.Color;

public class Human extends Actor {

	private Human spouse;
	private ArrayList<Human> friends;
	
	/**
	 * Makes a new {@code Actor} with a spouse
	 * @param posx
	 * @param posy
	 * @param name
	 * @param prefix
	 * @param att
	 * @param str
	 * @param def
	 * @param arm
	 * @param spouse
	 */
	public Human(int posx, int posy, String name, String prefix, int att, int str, int def, int arm) {
		super(posx,posy,name,prefix,att,str,def,arm);
	}
	
	/**
	 * Makes a new {@code Actor} with a spouse
	 * @param posx
	 * @param posy
	 * @param name
	 * @param prefix
	 * @param att
	 * @param str
	 * @param def
	 * @param arm
	 * @param spouse
	 */
	public Human(int posx, int posy, String name, String prefix, int att, int str, int def, int arm, Human spouse) {
		super(posx,posy,name,prefix,att,str,def,arm);
		this.spouse = spouse;
		this.spouse.spouse = this;
	}
	
	/**
	 * Returns the person's spouse
	 * @return {@code Actor} - The spouse
	 */

	public Actor getSpouse() {
		return this.spouse;
	}

	/**
	 * Sets the spouse of current {@code Actor} as well as setting the spouse's spouse to the current {@code Actor}
	 * @param spouse - Spouse to set
	 */

	public void setSpouse(Human spouse) {
		spouse.spouse = this;
		this.spouse = spouse;
	}
	
	/**
	 * Returns the {@code ArrayList} of friends
	 * @return {@code ArrayList<Actor>} - List of friends
	 */
	
	public ArrayList<Human> getFriends() {
		return this.friends;
	}
	
	/**
	 * Adds an {@code Actor} to the list of friends.
	 * @param friend - Friend to add
	 */
	
	public void addFriend(Human friend) {
		if(friends == null)
			friends = new ArrayList<Human>();
		friends.add(friend);
	}
	
	@Override
	public void render() {
		Graphics.drawString(posx, posy, "#", Color.magenta, Graphics.font);
	}
	
}
