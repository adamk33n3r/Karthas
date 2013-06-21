package org.adamk33n3r.karthas.gui;

// Java import
import java.util.HashMap;

import org.adamk33n3r.karthas.Area;
// My imports
import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.entities.Human;
import org.adamk33n3r.karthas.gui.components.*;

public class LayerBuilder {

	private static HashMap<String, Layer> stateMap;

	private LayerBuilder() {
	}
	
	public static HashMap<String, Layer> downloading() {
		stateMap = new HashMap<String, Layer>();
		stateMap.put("Downloading", new Layer(new CenteredTextComponent("Downloading resources...", 200, true)));
		return stateMap;
	}

	public static HashMap<String, Layer> build() {
		stateMap = new HashMap<String, Layer>();
		stateMap.put("Title", new Layer(new LogoComponent(((ResizableImage) Resources.get(Resources.IMAGES.RESIZE.componentBack)).getImage()), new TitleMenu(),
				new TitleText("Welcome to Karthas!")/*, new BGSoundComponent(Resources.SOUNDS.menuBG, true, 1.0f, 1.0f)*/));
		Human me = new Human(5, 5, "Adam", "Sir", 3, 1, 1, 0);
		stateMap.put("Main", new Layer(new Overworld(Resources.area, me)));
		
		return stateMap;
	}

}
