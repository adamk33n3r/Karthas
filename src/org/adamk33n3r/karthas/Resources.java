package org.adamk33n3r.karthas;

// Java imports
import java.io.File;
import java.net.URL;
import java.util.HashMap;

import org.adamk33n3r.utils.ZipUtil;

// Apache import
import org.apache.commons.io.FileUtils;

// Slick imports
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Resources {

	private static HashMap<String, Object> resourceMap = new HashMap<String, Object>();
	
	private static HashMap<SOUNDS, Sound> soundMap = new HashMap<SOUNDS, Sound>();
	
	public static enum SOUNDS{
		menuSelect,
		menuExecute, menuBG
	}
	
	public static Sound getSound(SOUNDS sound) {
		return soundMap.get(sound);
	}

	private static String home, sep;

	public static void load() {
		loadSounds();
		try {
			resourceMap.put("resources/Karthas.png", new Image(Karthas.home + String.format("%sresources%sKarthas.png", Karthas.sep, Karthas.sep)));
			resourceMap.put("MenuItemBack", ((ResizableImage) resourceMap.get("ComponentBack")).build(300, 30));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	private static void loadSounds() {
		try {
			soundMap.put(SOUNDS.menuExecute, new Sound(Karthas.home + String.format("%sresources%saudio%sMenuExecute.wav", Karthas.sep, Karthas.sep, Karthas.sep)));
			soundMap.put(SOUNDS.menuSelect, new Sound(Karthas.home + String.format("%sresources%saudio%sMenuSelect.wav", Karthas.sep, Karthas.sep, Karthas.sep)));
			soundMap.put(SOUNDS.menuBG, new Sound(Karthas.home + String.format("%sresources%saudio%sbg.wav", Karthas.sep, Karthas.sep, Karthas.sep)));
		} catch (SlickException e) {
			Karthas.printSystemError("Could not load Sound");
			e.printStackTrace();
		}
	}

	public static void downloadInit() {
		home = Karthas.home;
		sep = Karthas.sep;
		// Check if 'resources' exists
		File resDir = new File(home + sep + "resources");
		if (!resDir.exists())
			resDir.mkdirs();
		try {
			FileUtils.copyURLToFile(new URL("http://cse.taylor.edu/~akeenan/karthas/resources/MenuItemBack.png"), new File(home + sep + "resources" + sep
					+ "MenuItemBack.png"), 10000, 10000);
			FileUtils.copyURLToFile(new URL("http://cse.taylor.edu/~akeenan/karthas/resources/Chalkduster24.png"), new File(home + sep + "resources" + sep
					+ "Chalkduster24.png"), 10000, 10000);
			FileUtils.copyURLToFile(new URL("http://cse.taylor.edu/~akeenan/karthas/resources/Chalkduster24.fnt"), new File(home + sep + "resources" + sep
					+ "Chalkduster24.fnt"), 10000, 10000);

			resourceMap.put("ComponentBack", new ResizableImage(new Image(Karthas.home + "/resources/MenuItemBack.png"), 10, 1, 10, 10, 1, 10));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean needDownload() {
		return !new File(home + sep + "resources" + sep + "Karthas.png").exists();
	}

	public static void downloadResources() {
		try {
			File resZip = new File(home + sep + "resources.zip");
			FileUtils.copyURLToFile(new URL("http://cse.taylor.edu/~akeenan/karthas/resources.zip"), resZip, 10000, 10000);
			ZipUtil.unzip(resZip);
			resZip.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Object get(String string) {
		return resourceMap.get(string);
	}

	public static void set(String string, Object obj) {
		resourceMap.put(string, obj);
	}
}
