package org.adamk33n3r.karthas.gui;

// Java import
import java.util.HashMap;

// My imports
import org.adamk33n3r.karthas.ResizableImage;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.gui.components.*;

public class StateBuilder {

	private static HashMap<String, State> stateMap;

	private StateBuilder() {
	}
	
	public static HashMap<String, State> downloading() {
		stateMap = new HashMap<String, State>();
		stateMap.put("Downloading", new State(new CenteredTextComponent("Downloading resources...", 200, true)));
		return stateMap;
	}

	public static HashMap<String, State> build() {
		stateMap = new HashMap<String, State>();
		stateMap.put("Title", new State(new LogoComponent(((ResizableImage) Resources.get(Resources.IMAGES.RESIZE.componentBack)).getImage()), new TitleMenu(),
				new TitleText("Welcome to Karthas!"), new BGSoundComponent(Resources.SOUNDS.menuBG, true, 1.0f, 1.0f)));

		stateMap.put("Main", new State(new MainMenu()));
		
		return stateMap;
	}

}
