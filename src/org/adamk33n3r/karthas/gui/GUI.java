package org.adamk33n3r.karthas.gui;

// Java import
import java.util.HashMap;

// LWGL imports
import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

// Slick import for font rendering
import org.newdawn.slick.Font;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Image;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class GUI {

	static GUI gui = null;

	static boolean running = true;

	static HashMap<String, Menu> menuMap;
	static Menu curMenu = null;

	static int width, height;

	static AngelCodeFont font = null;

	private GUI(String title, int width, int height) {
		GUI.width = width;
		GUI.height = height;

		menuMap = MenuCreator.create();

		curMenu = menuMap.get("Main");

		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.setVSyncEnabled(true);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a {@code GUI}
	 * @param title - The title of the {@code GUI}
	 * @param width - The width of the {@code GUI}
	 * @param height - The height of the {@code GUI}
	 */
	public static void create(String title, int width, int height) {
		gui = new GUI(title, width, height);
		glShadeModel(GL_SMOOTH);
		glDisable(GL_LIGHTING);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClearDepth(1);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 600, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		try {
			font = new AngelCodeFont("resources/Impact24.fnt", new Image("resources/Impact24.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns {@code true} if the {@code GUI} is running
	 * @return true if running, false otherwise
	 */
	public static boolean isRunning() {
		return running;
	}

	/**
	 * Sets the state of the {@code GUI} to not running in order to shutdown
	 */
	public static void shutdown() {
		running = false;
	}

	/**
	 * Destroys the display
	 */
	public static void destroy() {
		Display.destroy();
	}

	/**
	 * Changes the current menu
	 * @param menu - The {@link Menu} to change to
	 */
	public static void changeTo(Menu menu) {
		curMenu = menu;
	}

	/**
	 * Changes the current menu
	 * @param menuName - The name of the {@code Menu} to change to
	 */
	public static void changeTo(String menuName) {
		Menu newMenu = menuMap.get(menuName);
		if (newMenu != null)
			changeTo(newMenu);
	}

	/**
	 * Set the display mode to be used
	 * 
	 * @param width The width of the display required
	 * @param height The height of the display required
	 * @param fullscreen True if we want fullscreen mode
	 */
	public static void setDisplayMode(int width, int height, boolean fullscreen) {

		// return if requested DisplayMode is already set
		if ((Display.getDisplayMode().getWidth() == width) && (Display.getDisplayMode().getHeight() == height) && (Display.isFullscreen() == fullscreen)) {
			return;
		}

		try {
			DisplayMode targetDisplayMode = null;

			if (fullscreen) {
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;

				for (int i = 0; i < modes.length; i++) {
					DisplayMode current = modes[i];

					if ((current.getWidth() == width) && (current.getHeight() == height)) {
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						// if we've found a match for bpp and frequence against the 
						// original display mode then it's probably best to go for this one
						// since it's most likely compatible with the monitor
						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel())
								&& (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
							targetDisplayMode = current;
							break;
						}
					}
				}
			} else {
				targetDisplayMode = new DisplayMode(width, height);
			}

			if (targetDisplayMode == null) {
				System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);

		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
		}
	}

	/**
	 * Updates. Keyboard events
	 */
	public static void update() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				int key = Keyboard.getEventKey();
				if (key == Keyboard.KEY_DOWN)
					curMenu.nextItem();
				else if (key == Keyboard.KEY_UP)
					curMenu.prevItem();
				else if (key == Keyboard.KEY_RETURN) {
					curMenu.items.get(curMenu.selectedItem).execute();
				}
			}
		}
	}

	/**
	 * Renders the {@code GUI}
	 */
	public static void render() {

		drawString(100, 100, "Hey Sexy", Color.yellow, font);

		curMenu.render();
		Display.update();

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	/**
	 * Renders a string to the screen
	 * @param x - The x location to print the text
	 * @param y - The y location to print the text
	 * @param string - The text to print
	 * @param color - The color to print the text in
	 * @param font - The font in which to print the text
	 */
	public static void drawString(int x, int y, String string, Color color, Font font) {
		glEnable(GL_TEXTURE_2D);

		font.drawString(x, y, string, color);

		glDisable(GL_TEXTURE_2D);
	}
	
	public static void drawRect(int x1, int y1, int x2, int y2, Color color) {
		setColor(color);
		glRecti(x1, y1, x2, y2);
	}

	/**
	 * Sets the color of the {@code GUI} rendering
	 * @param color - The color to set
	 */
	private static void setColor(Color color) {
		glColor4f(color.r, color.g, color.b, color.a);
	}

	/**
	 * Gets input from the user using {@link Console}
	 */
	public static void getInput() {
		Console.start();
		while (Console.isRunning()) {
			if(Display.isCloseRequested()) {
				shutdown();
				break;
			}
			Console.update();
			Console.render();
		}
	}

}
