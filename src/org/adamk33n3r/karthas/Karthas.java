package org.adamk33n3r.karthas;

// Java imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// XStream for XML serialization
import com.thoughtworks.xstream.XStream;

// LWJGL
import org.lwjgl.opengl.Display;

// My own classes
import org.adamk33n3r.karthas.entities.Actor;
import org.adamk33n3r.karthas.entities.Entity;
import org.adamk33n3r.karthas.gui.GUI;

public class Karthas {
	
	static final boolean XML = false;

	static boolean run = false;
	static String playerName = "";
	static Actor player;

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting up....\n");
		/*try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		//printSystemMessage(player);
		
		GUI.create("Karthas", 800, 600);
		
		while (GUI.isRunning()) {
			GUI.update();
			GUI.render();
			Display.sync(60);
			
			if(Display.isCloseRequested())
				GUI.shutdown();
			
			/*printMainMenu(); // TODO Add colors
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
		System.exit(0);

	}

	/**
	 * Initializes program
	 * 
	 * @throws IOException
	 */

	@SuppressWarnings("unused")
	private static void init() throws IOException {

		printSystemMessage("1. Load\n2. New Game");
		int choice = 0;
		while (choice != 1 && choice != 2) {
			printSystemMessage("Please enter 1 or 2");
			printPrompt(">>> ");
			try {
				choice = Integer.parseInt(getInput());
			} catch (NumberFormatException e) {
				printSystemError("NumberFormatException");
			}
		}
		switch (choice) {
			case 1:
				printPrompt("Enter character name to load: ");
				playerName = getInput();
				// playerName = "Binary";
				while (!load(playerName,XML)) {
					printSystemError("Player not found. Try again.");
					printPrompt("Enter character name to load: ");
					playerName = getInput();
				}
				break;
			case 2:
				printPrompt("Enter new character name: ");
				playerName = getInput();
				// playerName = "JAFelker";
				player = new Actor(0, 0, playerName, "Squire", 2, 1, 1, 0);
				run = true;
				break;
		}
	}

	/**
	 * Loads saved data
	 * 
	 * @param playerName - The player name to be loaded
	 * @return True if loaded successfully
	 */

	private static boolean load(String playerName, boolean Xstream) {
		if (Xstream) {
			XStream xstream = new XStream();
			xstream.alias("player", Actor.class);
			player = (Actor) xstream.fromXML(new File(playerName + ".xml"));
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
		return run;
	}

	/**
	 * Saves the current player
	 * 
	 * @param object The player
	 * @return True if saved successfully
	 */

	@SuppressWarnings("unused")
	private static boolean save(Entity object, boolean Xstream) {
		try {
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
		}
		return true;
	}
	
	/**
	 * Helper function to get input from user
	 * @return String - User's input
	 */

	private static String getInput() {
		String in;
		try {
			in = input.readLine();
		} catch (IOException e) {
			in = null;
		}
		return in;
	}
	
	/**
	 * Helper function to print the Main Menu
	 */
	
	@SuppressWarnings("unused")
	private static void printMainMenu(){
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
	 * Helper function to print custom prompt
	 * @param prompt
	 */

	private static void printPrompt(String prompt) {
		System.out.print(prompt);
	}

	/**
	 * Helper function to print an error message
	 * @param error - Error to be printed
	 */
	
	private static void printSystemError(String error) {
		System.err.println("Error: " + error);
	}
	
}
