package org.adamk33n3r.karthas;

import org.adamk33n3r.karthas.entities.*;

public class Karthas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting up....\n");
		Entity player = new Actor(0, 0, "Andrew", "Squire", 2, 1, 1, 0);
		System.out.println(player);
	}

}
