package org.adamk33n3r.karthas;

// Java imports
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	static boolean run = false;
	static String playerName = "";
	static Actor player;

	static String codeBase;
	public static String home, sep;
;

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	Canvas display_parent;
	Thread gameThread;

	public void startGame() {
		gameThread = new Thread() {
			public void run() {
				try {
					Display.setParent(display_parent);
				} catch (LWJGLException e) {
					e.printStackTrace();
				}GUI.applet = true;
				GUI.create("Karthas", 800, 600);
				/*LinkedList<Cutscene> s = CutsceneBuilder.build();
				while (s.get(0).isRunning()) {
					s.get(0).update();
					s.get(0).render();
					Display.update();
					glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
					Display.sync(60);
				}*/
				loop();
			}
		};
		gameThread.start();
	}

	public void stopGame() {
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		setUp();
		add(display_parent);
		display_parent.setFocusable(true);
		display_parent.requestFocus();
		display_parent.setIgnoreRepaint(true);
		setVisible(true);
	}

	public void startAsApp() {
		setUp();
		GUI.create("Karthas", 800, 600);

		/*LinkedList<Cutscene> s = CutsceneBuilder.build();
		while (s.get(0).isRunning()) {
			s.get(0).update();
			s.get(0).render();
			Display.update();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		Display.sync(60);
		}*/

		loop();
	}

	public void stop() {

	}

	public void destroy() {
		remove(display_parent);
		super.destroy();
	}

	private void setUp() {
		sep = System.getProperty("file.separator");
		home = System.getProperty("user.home") + sep + ".karthas";
	}

	public static String getCWD() {
		return codeBase != null ? codeBase : "file:///" + System.getProperty("user.dir") + "/";
	}

	public void init() {
		codeBase = getCodeBase().toString();
		setUp();
		setLayout(new BorderLayout());
		//setSize(1280, 800);
		try {
			display_parent = new Canvas() {
				private static final long serialVersionUID = -8338114538157335277L;

				public final void addNotify() {
					super.addNotify();
					startGame();
				}

				public final void removeNotify() {
					stopGame();
					super.removeNotify();
				}
			};
			display_parent.setSize(800, 600);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to create display");
		}
	}

	/**
	 * @param args
	 */
	public void loop() {
		System.out.println("Starting up...");
		
		/*try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		//printSystemMessage(player);

		while (GUI.isRunning()) {
			GUI.update();
			GUI.render(false);

			if (Display.isCloseRequested())
				GUI.shutdown();

			/*printMainMenu();
			switch (Integer.parseInt(getInput())) {
				case 1:
					break;
				case 2:
					printSystemMessage(player);
					break;
				case 3:
					save(player,XML);
					break;
				case 4:
					run = false;
					break;
			}*/
		}
		GUI.destroy();

		//save(player, XML);

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

	/**
	 * Helper function to print the Main Menu
	 */

	@SuppressWarnings("unused")
	private static void printMainMenu() {
		printSystemMessage("1. ~Attack\n2. Print Stats\n3. Save\n4. Quit");
		printPrompt();
	}

	/**
	 * Helper function to print a message to the console
	 * @param msg - Message to print
	 */

	private static void printSystemMessage(Object msg) {
		System.out.println(msg);
	}

	/**
	 * Helper function to print a prompt
	 */

	private static void printPrompt() {
		System.out.print(">>> ");
	}

	/**
	 * Helper function to print an error message
	 * @param error - Error to be printed
	 */

	public static void printSystemError(String error) {
		System.err.println("Error: " + error);
	}

	public static Object getPlayer() {
		return player;
	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void printDebug(String string) {
		if (Karthas.DEBUG)
			System.out.println(string);
	}

}
