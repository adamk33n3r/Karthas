package org.adamk33n3r.karthas;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.adamk33n3r.karthas.entities.*;

public class Karthas {

	static boolean run = false;
	static String playerName = "";
	static Actor player;
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting up....\n");
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}

		printSystemMessage(player);

		while (run) {
			printSystemMessage("1. ~Attack\n2. Print Stats\n3. Quit");
			printPrompt();
			switch(Integer.parseInt(getInput())){
				case 1:
					break;
				case 2:
					printSystemMessage(player);
					break;
				case 3:
					run = false;
					break;
			}
		}
		save(player);
	}

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
			while (!load(playerName)) {
				printSystemError("Player not found. Try again.");
				printPrompt("Enter character name to load: ");
				playerName = getInput();
			}
			break;
		case 2:
			printPrompt("Enter new character name: ");
			playerName = getInput();
			player = new Actor(0, 0, playerName, "Squire", 2, 1, 1, 0);
			run = true;
			break;
		}
	}

	private static boolean load(String playerName) {

		try {
			ObjectInput in = new ObjectInputStream(new FileInputStream(playerName + ".bin"));
			player = (Actor) in.readObject();
			run = true;
		} catch (IOException | ClassNotFoundException e) {
			// System.err.println(e.getLocalizedMessage());
		}
		return run;
	}

	private static boolean save(Entity object) {
		try {
			// Serialize data object to a file
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream(object.getName() + ".bin"));
			out.writeObject(object);
			out.close();
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return true;
	}
	
	private static String getInput(){
		String in;
		try {
			in = input.readLine();
		} catch (IOException e) {
			in = null;
		}return in;
	}

	private static void printSystemMessage(java.lang.Object msg) {
		System.out.println(msg);
	}
	
	private static void printPrompt(){
		System.out.print(">>> ");
	}

	private static void printPrompt(String prompt) {
		System.out.print(prompt);
	}

	private static void printSystemError(String error) {
		System.err.println("Error: " + error);
	}

}
