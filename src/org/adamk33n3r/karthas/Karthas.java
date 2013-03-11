package org.adamk33n3r.karthas;

// Java imports
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	
	JFrame frame;

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	Canvas display_parent;
	Thread gameThread;

	public void startGame() {
		gameThread = new Thread() {
			@Override
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
		}frame.dispose();
	}

	@Override
	public void start() {
		setUp();
		add(display_parent);
		display_parent.setFocusable(true);
		display_parent.requestFocus();
		display_parent.setIgnoreRepaint(true);
		setVisible(true);
	}

	public void startAsApp() {
		/*frame = new JFrame("Karthas");
		setUp();
		frame.setLayout(new BorderLayout());
		//frame.setSize(1600, 1200);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeRequested = true;
			}
		});
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
			display_parent.setSize(400, 300);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to create display");
		}frame.add(display_parent, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		display_parent.setFocusable(true);
		//display_parent.requestFocus();
		display_parent.setIgnoreRepaint(true);
		frame.setVisible(true);*/
		
		
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

	@Override
	public void stop() {

	}

	@Override
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

	@Override
	public void init() {
		codeBase = getCodeBase().toString();
		setUp();
		setLayout(new BorderLayout());
		//setSize(1280, 800);
		try {
			display_parent = new Canvas() {
				private static final long serialVersionUID = -8338114538157335277L;

				@Override
				public final void addNotify() {
					super.addNotify();
					startGame();
				}

				@Override
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
	 * The main game loop
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
			
			if (Display.isCloseRequested() || closeRequested)
				GUI.shutdown();
			
		}
		GUI.destroy();
		org.lwjgl.openal.AL.destroy();
		System.exit(0);
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
