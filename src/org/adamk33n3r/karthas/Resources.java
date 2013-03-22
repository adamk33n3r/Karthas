package org.adamk33n3r.karthas;

// Java imports
import java.io.File;
import java.net.URL;
import java.util.HashMap;

import org.adamk33n3r.karthas.entities.Actor;
import org.adamk33n3r.karthas.entities.Object;
import org.adamk33n3r.utils.ZipUtil;

// Apache import
import org.apache.commons.io.FileUtils;

// Slick imports
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Resources {

	private static HashMap<IMAGES, Image> imageMap = new HashMap<IMAGES, Image>();

	private static HashMap<IMAGES.RESIZE, ResizableImage> resizeImageMap = new HashMap<IMAGES.RESIZE, ResizableImage>();

	private static HashMap<SOUNDS, Sound> soundMap = new HashMap<SOUNDS, Sound>();
	
	public static Area area = new Area();

	public static enum IMAGES {
		karthas, menuItemBack, horizontalMenuItemBack, downloadingresources, welcometokarthas;
		public static enum RESIZE {
			componentBack
		}
	}

	public static enum SOUNDS {
		menuSelect, menuSelect2, menuExecute, menuBG, menuSelect3
	}

	public static Sound getSound(SOUNDS sound) {
		return soundMap.get(sound);
	}

	private static String home, sep;

	public static void load() {
		loadSounds();
		loadImages();
		area.setNPCs(new Actor(1,1,"1","Monster",1,1,0,0),new Actor(200,200,"2","Monster",1,1,0,0),new Actor(30,30,"3","Monster",1,1,0,0));
		area.setObjects(new Object(300,300,"Object 1"));
	}

	private static void loadImages() {
		try {
			resizeImageMap.put(IMAGES.RESIZE.componentBack, new ResizableImage(new Image(Karthas.home + "/resources/MenuItemBack.png"), 10, 1, 10, 10, 1, 10));
			imageMap.put(IMAGES.karthas, new Image(Karthas.home + String.format("%sresources%sKarthas.png", Karthas.sep, Karthas.sep)));
			imageMap.put(IMAGES.menuItemBack, ((ResizableImage) resizeImageMap.get(IMAGES.RESIZE.componentBack)).build(300, 30));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private static void loadSounds() {
		try {
			soundMap.put(SOUNDS.menuExecute,
					new Sound(Karthas.home + String.format("%sresources%saudio%sMenuExecute.wav", Karthas.sep, Karthas.sep, Karthas.sep)));
			soundMap.put(SOUNDS.menuSelect,
					new Sound(Karthas.home + String.format("%sresources%saudio%sMenuSelect.wav", Karthas.sep, Karthas.sep, Karthas.sep)));
			soundMap.put(SOUNDS.menuBG, new Sound(Karthas.home + String.format("%sresources%saudio%sbg.wav", Karthas.sep, Karthas.sep, Karthas.sep)));
			soundMap.put(SOUNDS.menuSelect2,
					new Sound(Karthas.home + String.format("%sresources%saudio%sMenuSelect2.wav", Karthas.sep, Karthas.sep, Karthas.sep)));
			soundMap.put(SOUNDS.menuSelect3,
					new Sound(Karthas.home + String.format("%sresources%saudio%sMenuSelect3.wav", Karthas.sep, Karthas.sep, Karthas.sep)));
		} catch (SlickException e) {
			Karthas.printSystemError("Could not load Sound");
			e.printStackTrace();
		}
	}

	public static void downloadInit() {
		home = Karthas.home;
		sep = Karthas.sep;
	}

	public static boolean needDownload() {
		return !new File(home + sep + "resources" + sep + "audio" + sep + "MenuSelect2.wav").exists();
	}
	
	/**
	 * Gets three files used to display the downloading state
	 */
	public static void downloadFirst() {
		// Check if 'resources' exists
		File resDir = new File(home + sep + "resources");
		if (!resDir.exists())
			resDir.mkdirs();
		try {
			FileUtils.copyURLToFile(new URL("http://cse.taylor.edu/~akeenan/Karthas/resources/MenuItemBack.png"), new File(home + sep + "resources" + sep
					+ "MenuItemBack.png"), 10000, 10000);
			FileUtils.copyURLToFile(new URL("http://cse.taylor.edu/~akeenan/Karthas/resources/Chalkduster24.png"), new File(home + sep + "resources" + sep
					+ "Chalkduster24.png"), 10000, 10000);
			FileUtils.copyURLToFile(new URL("http://cse.taylor.edu/~akeenan/Karthas/resources/Chalkduster24.fnt"), new File(home + sep + "resources" + sep
					+ "Chalkduster24.fnt"), 10000, 10000);

			resizeImageMap.put(IMAGES.RESIZE.componentBack, new ResizableImage(new Image(Karthas.home + "/resources/MenuItemBack.png"), 10, 1, 10, 10, 1, 10));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void downloadResources() {
		try {
			File resZip = new File(home + sep + "resources.zip");
			FileUtils.copyURLToFile(new URL("http://cse.taylor.edu/~akeenan/Karthas/resources.zip"), resZip, 10000, 10000);
			ZipUtil.unzip(resZip);
			resZip.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Image get(IMAGES image) {
		return imageMap.get(image);
	}

	public static ResizableImage get(IMAGES.RESIZE image) {
		return resizeImageMap.get(image);
	}

	public static Sound get(SOUNDS sound) {
		return soundMap.get(sound);
	}

	public static void load(IMAGES image, Image img) {
		imageMap.put(image, img);
	}

	public static void load(IMAGES.RESIZE image, ResizableImage img) {
		resizeImageMap.put(image, img);
	}

	public static void load(SOUNDS sound, Sound soundobj) {
		soundMap.put(sound, soundobj);
	}
}
