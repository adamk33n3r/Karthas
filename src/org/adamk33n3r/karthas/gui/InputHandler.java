package org.adamk33n3r.karthas.gui;

import java.util.Arrays;

import org.adamk33n3r.karthas.Karthas;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class InputHandler {
	
	public static boolean[] keys = new boolean[Keyboard.KEYBOARD_SIZE];
	private static int cursor = 0;
	
	private InputHandler() {
	}
	
	public static boolean isKeyDown(int key) {
		return keys[key];
	}
	
	public static boolean next() {
		if (cursor >= keys.length)
			cursor = 0;
		do {
			cursor++;
			if (cursor >= keys.length)
				return false;
			//System.out.println(cursor + " " + keys.length);
		} while (!keys[cursor]);
		return true;
	}
	
	public static int getKey() {
		return cursor;
	}
	
	public static boolean handleInput() {
		return handleInput(true);
	}
	
	public static boolean handleInput(boolean onKeyDown) {
		Arrays.fill(keys, false);
		while (Keyboard.next()) {
			if (onKeyDown == Keyboard.getEventKeyState()) {
				keys[Keyboard.getEventKey()] = true;
			}
		}
		
		if (isKeyDown(Keyboard.KEY_F)) {
			boolean fullscreen = !GUI.fullscreen;
			if (fullscreen) {
				GUI.width = Display.getDesktopDisplayMode().getWidth();
				GUI.height = Display.getDesktopDisplayMode().getHeight();
			} else {
				GUI.width = 800;
				GUI.height = 600;
			}
			GUI.setDisplayMode(GUI.width, GUI.height, fullscreen);
			Karthas.printDebug("Switched fullscreen to: " + fullscreen + " With: " + Display.getDisplayMode());
		}
		
		return false;
	}
}
