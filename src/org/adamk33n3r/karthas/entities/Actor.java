package org.adamk33n3r.karthas.entities;

import java.util.HashMap;

public class Actor extends Entity {
	
	private String prefix;
	private int att,def,str,arm;
	//TODO private HashMap<String,Integer> attributes = new HashMap<String, Integer>();
	
	/**
	 * An Actor is a subclass of the Entity abstract class.
	 * @param posx Position along x-axis
	 * @param posy Position along y-axis
	 * @param name <code>Actor</code>'s name.
	 * @param prefix <code>Actor</code>'s name prefix.
	 * @param att <code>Actor</code>'s attack attribute.
	 * @param str <code>Actor</code>'s strength attribute.
	 * @param def <code>Actor</code>'s defense attribute.
	 * @param arm <code>Actor</code>'s armor attribute.
	 */
	public Actor(int posx, int posy, String name, String prefix, int att, int str, int def, int arm) {
		super(posx, posy, name);
		this.prefix = prefix;
		this.att = att;
		this.str = str;
		this.def = def;
		this.arm = arm;
	}
	
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the att
	 */
	public int getAtt() {
		return att;
	}

	/**
	 * @param att the att to set
	 */
	public void setAtt(int att) {
		this.att = att;
	}

	/**
	 * @return the str
	 */
	public int getStr() {
		return str;
	}

	/**
	 * @param str the str to set
	 */
	public void setStr(int str) {
		this.str = str;
	}

	/**
	 * @return the def
	 */
	public int getDef() {
		return def;
	}

	/**
	 * @param def the def to set
	 */
	public void setDef(int def) {
		this.def = def;
	}

	/**
	 * @return the arm
	 */
	public int getArm() {
		return arm;
	}

	/**
	 * @param arm the arm to set
	 */
	public void setArm(int arm) {
		this.arm = arm;
	}

	@Override
	public String toString() {
		return String.format("%s %s: Att-%d, Str-%d, Def-%d, Arm-%d", prefix, name, att, str, def, arm);
	}

}
