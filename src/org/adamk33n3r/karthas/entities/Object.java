package org.adamk33n3r.karthas.entities;

public class Object extends Entity{
	
	public Object(int posx, int posy, String name){
		super(posx,posy,name);
	}

	@Override
	public String toString() {
		return name;
	}

}
