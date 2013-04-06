package org.adamk33n3r.karthas;

// Java imports
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;

// LWJGL
import org.lwjgl.opengl.Display;
import org.lwjgl.LWJGLException;

// My own classes
import org.adamk33n3r.karthas.entities.Actor;
import org.adamk33n3r.karthas.entities.Entity;
import org.adamk33n3r.karthas.gui.GUI;

public class Karthas extends Applet {
	
	public static final boolean DEBUG = true;

	private static final long serialVersionUID = 1108146708237546867L;

	static final boolean XML = false;
	boolean closeRequested = false;

	static boolean run = false;
	static String playerName = "";
	static Actor player;

	static String codeBase;
	public static String home, sep;
	
	private static Karthas self;
	private GUI gui;
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public static void startAsApp() {
		self = new Karthas();
		self.setUp();
		self.gui = GUI.create("Karthas", 800, 600);

		self.loop();
	}

	private void setUp() {
		sep = System.getProperty("file.separator");
		home = System.getProperty("user.home") + sep + ".karthas";
	}

	public static String getCWD() {
		return codeBase != null ? codeBase : "file:///" + System.getProperty("user.dir") + "/";
	}

	/**
	 * The main game loop
	 */
	public void loop() {
		System.out.println("Starting up...");

		while (GUI.isRunning()) {
			gui.update();
			gui.render();
			
			if (Display.isCloseRequested() || closeRequested)
				gui.shutdown();
		}
		gui.destroy();
		org.lwjgl.openal.AL.destroy();
		System.exit(0);
		//save(player, XML);
	}
	
	public void shutdown() {
		this.gui.shutdown();
	}

	/**
	 * Initializes program
	 * 
	 * @throws IOException
	 */

	public static Actor init(String name) {
		playerName = name;
		player = new Actor(0, 0, playerName, "Squire", 2, 1, 1, 0);
		return player;
	}

	/**
	 * Loads saved data
	 * 
	 * @param playerName - The player name to be loaded
	 * @return True if loaded successfully
	 */
//TODO rewrite load and save functions
	public static boolean load(String playerName, boolean Xstream) {
		/*if (Xstream) {
			XStream xstream = new XStream();
			xstream.alias("player", Actor.class);
			try {
				printSystemMessage("before");

				//player = (Actor) xstream.fromXML(new File(playerName));
				player = (Actor) xstream.fromXML(new File(playerName + ".xml"));
			} catch (Exception e) {
				System.err.println(String.format("Save %s.xml does not exist", playerName));
			}
			printSystemMessage("after");

			run = true;
		} else {
			try {
				ObjectInput in = new ObjectInputStream((new FileInputStream(playerName + ".bin")));
				player = (Actor) in.readObject();
				in.close();
				run = true;
			} catch (Exception e) {
				System.err.println(e.getLocalizedMessage());
			}
		}
		return run;*/
		return true;
	}

	/**
	 * Saves the current player
	 * 
	 * @param object The player
	 * @return True if saved successfully
	 */

	public static boolean save(Entity object, boolean Xstream) {
		/*try {
			if (Xstream) {
				XStream xstream = new XStream();
				xstream.alias("player", Actor.class);
				BufferedWriter bw = new BufferedWriter(new FileWriter(object.getName() + ".xml"));
				printSystemMessage("Saving...");
				printSystemMessage(xstream.toXML(object));
				xstream.toXML(object, bw);
				bw.close();
				printSystemMessage("Done!");
			} else {
				ObjectOutputStream out = new ObjectOutputStream((new FileOutputStream(object.getName() + ".bin")));
				out.writeObject(object);
				out.close();
			}
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}*/
		return true;
	}

	public static Object getPlayer() {
		return player;
	}
	
	public static Karthas getKarthas() {
		return self;
	}
	
	/**
	 * Helper function to print an error message
	 * @param error - Error to be printed
	 */

	public static void printSystemError(String error) {
		System.err.println("Error: " + error);
	}
	public static void printDebug(String string) {
		if (Karthas.DEBUG)
			System.out.println(string);
	}

}
