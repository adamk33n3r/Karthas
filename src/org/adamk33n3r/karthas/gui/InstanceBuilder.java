package org.adamk33n3r.karthas.gui;

// Java import
import java.util.HashMap;

import org.adamk33n3r.karthas.Area;
// My imports
import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.entities.Human;
import org.adamk33n3r.karthas.gui.components.*;

public class InstanceBuilder {

	private static HashMap<String, Instance> stateMap;

	private InstanceBuilder() {
	}
	
	public static HashMap<String, Instance> downloading() {
		stateMap = new HashMap<String, Instance>();
		stateMap.put("Downloading", new Instance(new CenteredTextComponent("Downloading resources...", 200, true)));
		return stateMap;
	}

	public static HashMap<String, Instance> build() {
		stateMap = new HashMap<String, Instance>();
		stateMap.put("Title", new Instance(new LogoComponent(((ResizableImage) Resources.get(Resources.IMAGES.RESIZE.componentBack)).getImage()), new TitleMenu(),
				new TitleText("Welcome to Karthas!")/*, new BGSoundComponent(Resources.SOUNDS.menuBG, true, 1.0f, 1.0f)*/));
		Human me = new Human(5, 5, "Adam", "Sir", 3, 1, 1, 0);
		stateMap.put("Main", new Instance(new MainMenu(me), new Overworld(Resources.area, me)));
		
		return stateMap;
	}

}
