package org.adamk33n3r.karthas.gui;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Image;

public class Scene {
	public LinkedList<SceneText> strings;
	LinkedList<Image> scenes;

	public Scene(List<String> strings, Image... images) {
		if (images != null) {
			this.scenes = new LinkedList<Image>();
			this.scenes.addAll(Arrays.asList(images));
		}

		this.strings = new LinkedList<SceneText>();
		for (Iterator<String> it = strings.iterator(); it.hasNext();) {
			SceneText text = new SceneText(it.next());
			this.strings.add(text);
		}
	}

	public class SceneText {
		public LinkedList<String> lines;
		public String string;

		public SceneText(String string) {
			this.string = string;
			lines = new LinkedList<String>();
			int maxWidth = GUI.width - 300;
			String[] words = string.split(" ");
			String line = "";
			int length = 0;
			for (String word : words) {
				length = GUI.font.getWidth(line + word + " ");
				if (length <= maxWidth && word.charAt(0) != '~')
					line += word + " ";
				else {
					if (word.charAt(0) == '~')
						word = word.substring(1);
					lines.add(line.substring(0, line.length() - 1));
					length = 0;
					line = word + " ";
				}
			}
			lines.add(line.substring(0, line.length() - 1));
		}
	}
}
