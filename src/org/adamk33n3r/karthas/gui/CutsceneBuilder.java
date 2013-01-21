package org.adamk33n3r.karthas.gui;

import java.util.Arrays;
import java.util.LinkedList;

import org.newdawn.slick.Image;

import org.adamk33n3r.karthas.Resources;

public class CutsceneBuilder {
	public static LinkedList<Cutscene> build() {
		LinkedList<Cutscene> list = new LinkedList<Cutscene>();
		
		list.add(new Cutscene(new Scene(Arrays.asList("I know what you're thinking... ~It's just another one of those RPG games. ~You go around, kill people, get experience, complete quests.",
				"Now I'm not saying this one doesn't have these things but believe me...", "This", "One's", "Different"), (Image) Resources.get("resources/Karthas.png"))));
		
		return list;
	}
}
