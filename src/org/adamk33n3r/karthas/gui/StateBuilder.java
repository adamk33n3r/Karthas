package org.adamk33n3r.karthas.gui;

// Java import
import java.util.HashMap;

// My imports
import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.entities.Human;
import org.adamk33n3r.karthas.gui.components.*;

public class StateBuilder {

	private static HashMap<String, State> stateMap;

	private StateBuilder() {
	}
	
	public static HashMap<String, State> downloading() {
		stateMap = new HashMap<String, State>();
		stateMap.put("Downloading", new State().addLayer(new Layer(new CenteredTextComponent("Downloading resources...", 200, true))));
		return stateMap;
	}

	public static HashMap<String, State> build() {
		stateMap = new HashMap<String, State>();
		stateMap.put("Title", new State().addLayer(new Layer(new TitleMenu(), new LogoComponent(((ResizableImage) Resources.get(Resources.IMAGES.RESIZE.componentBack)).getImage()),
				new TitleText("Welcome to Karthas!")/*, new BGSoundComponent(Resources.SOUNDS.menuBG, true, 1.0f, 1.0f)*/)));
		Human me = new Human(5, 5, "Adam", "Sir", 3, 1, 1, 0);
		
		stateMap.put("Main", new State().addLayer(new Layer(new Overworld(Resources.area, me))).addLayer(new Layer(new MainMenu(me))));
		
		return stateMap;
	}

}
