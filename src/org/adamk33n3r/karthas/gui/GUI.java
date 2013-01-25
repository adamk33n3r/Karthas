package org.adamk33n3r.karthas.gui;

// Java imports
import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;

// LWGL imports
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

// Slick import for font rendering
import org.newdawn.slick.Font;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Image;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

// My imports
import org.adamk33n3r.karthas.Karthas;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.utils.Timer;
import org.adamk33n3r.utils.TimerAction;

public class GUI {

	public static final Color DEFAULT_MENU_COLOR = Color.blue;
	public static final Color DEFAULT_DISABLED_COLOR = Color.gray;
	public static final Color DEFAULT_BORDER_COLOR = Color.gray;
	public static final Color DEFAULT_FONT_COLOR = Color.yellow;
	public static final Color DEFAULT_SELECTED_MENU_COLOR = new Color(255, 225, 0);
	public static final Color DEFAULT_SELECTED_FONT_COLOR = Color.orange;
	
	static GUI gui = null;

	static boolean running = true;
	static boolean console = false;
	public static boolean applet = false;

	private static long lastFrame;
	private static int fps, curFPS;
	private static double delta;
	private static long lastTime;

	static HashMap<String, State> stateMap;
	static LinkedList<State> stateLayers;

	public static int width, height;
	public static boolean fullscreen;

	public static AngelCodeFont font = null;

	boolean downloading;

	private GUI(String title, int width, int height) {
		GUI.width = width;
		GUI.height = height;

		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			if (!applet)
				Display.setResizable(true);
			Display.create();
			initGL();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		Resources.downloadInit();
		stateMap = StateBuilder.downloading();
		try {
			String file = Karthas.home + "/resources/Chalkduster24.fnt";
			String file2 = Karthas.home + "/resources/Chalkduster24.png";
			font = new AngelCodeFont(file, file2);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		if (Resources.needDownload()) {
			Karthas.printDebug("");
			GUI.changeTo("Downloading");
			downloading = true;
			new Thread() {
				@Override
				public void run() {
					Resources.downloadResources();
					downloading = false;
				}
			}.start();

			while (downloading) {
				GUI.update();
				GUI.render(false);
			}
			GUI.goBack();
		}
		Resources.load();
	}

	/**
	 * Creates a {@code GUI}
	 * @param title - The title of the {@code GUI}
	 * @param width - The width of the {@code GUI}
	 * @param height - The height of the {@code GUI}
	 */
	public static void create(String title, int width, int height) {
		Keyboard.enableRepeatEvents(true);
		stateLayers = new LinkedList<State>();

		gui = new GUI(title, width, height);

		stateMap = StateBuilder.build();
		GUI.changeTo("Title");
		initTime();
		setDelta();
		//GUI.changeTo("Downloading");

	}

	private static void initGL() {
		glShadeModel(GL_SMOOTH);
		glDisable(GL_LIGHTING);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClearDepth(1);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
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

	public static State getCurrentState() {
		return stateLayers.get(stateLayers.size() - 1);
	}

	/**
	 * Changes the current menu
	 * @param menu - The {@link Menu} to change to
	 */
	private static void changeTo(State state) {
		stateLayers.add(state);
	}

	/**
	 * Changes the current menu
	 * @param menuName - The name of the {@code Menu} to change to
	 */
	public static void changeTo(String stateName) {
		State newState = stateMap.get(stateName);
		if (newState != null)
			changeTo(newState);
		else
			System.err.println("Can't find state!");
	}

	public static void goBack() {
		stateLayers.pop();
	}

	/**
	 * Set the display mode to be used
	 * 
	 * @param width The width of the display required
	 * @param height The height of the display required
	 * @param fullscreen True if we want fullscreen mode
	 */
	public static void setDisplayMode(int width, int height, final boolean fullscreen) {

		// return if requested DisplayMode is already set
		if ((Display.getDisplayMode().getWidth() == width) && (Display.getDisplayMode().getHeight() == height) && (Display.isFullscreen() == fullscreen)) {
			return;
		}

		try {
			DisplayMode targetDisplayMode = null;

			if (fullscreen) {
				int freq = 0;

				for (DisplayMode current : Display.getAvailableDisplayModes()) {
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

			GUI.width = width;
			GUI.height = height;
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glViewport(0, 0, width, height);
			glOrtho(0, width, height, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);

			//System.out.println("It is now: " + GUI.width + "x" + GUI.height + " when it should be: " + width + "x" + height);

			Timer.run(new TimerAction() {
				@Override
				public void run() {
					GUI.setFullscreen(fullscreen);
				}
			}, 100);

		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
		}
	}

	private static void setFullscreen(boolean fullscreen) {
		GUI.fullscreen = fullscreen;
	}

	public static void renderState() {
		stateLayers.get(stateLayers.size() - 1).render();
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

	public static void drawStringCentered(int x, int y, String string, Color color, Font font) {
		glEnable(GL_TEXTURE_2D);

		font.drawString(x - font.getWidth(string) / 2, y, string, color);

		glDisable(GL_TEXTURE_2D);
	}

	public static void drawRect(int x1, int y1, int x2, int y2, Color color) {
		setColor(color);
		glRecti(x1, y1, x2, y2);
	}

	public static void drawRect(int x1, int y1, int x2, int y2, Color color, int borderWidth, Color borderColor) {
		drawRect(x1 - borderWidth, y1 - borderWidth, x2 + borderWidth, y2 + borderWidth, borderColor);
		drawRect(x1, y1, x2, y2, color);
	}

	public static void drawPolygon(Color color, Point... points) {
		setColor(color);
		glBegin(GL_POLYGON);
		for (int i = 0; i < points.length; i++)
			glVertex2f(points[i].x, points[i].y);
		glEnd();
	}

	public static void drawImage(Image img, int x, int y) {
		img.draw(x, y);
		glDisable(GL_TEXTURE_2D);
	}

	public static void drawImageFull(Image img) {
		img.draw(0, 0, width, height);
	}

	public static void drawImageFullToScale(Image img) {
		int tw = 0, th = 0;
		while (tw < width && th < height) {
			tw++;
			th++;
		}

		img.draw((width - tw) / 2, (height - th) / 2, tw, th);
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
	public static String getInput(String prompt) {
		console = true;
		Console.start(prompt);
		while (Console.isRunning()) {
			if (Display.isCloseRequested()) {
				shutdown();
				break;
			}
			Console.update();
			Console.render();
		}
		console = false;
		return Console.getInput();
	}

	public static boolean confirm() {
		return Console.confirm();
	}

	/**
	 * Updates. Keyboard events
	 */
	public static void update() {

		if (!applet && Display.wasResized()) {
			width = Display.getWidth();
			height = Display.getHeight();
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glViewport(0, 0, width, height);
			glOrtho(0, width, height, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);
			System.out.println("resized to: " + width + "x" + height);
		}
		if (fullscreen && !Display.isFullscreen() || !fullscreen && Display.isFullscreen()) {
			try {
				Display.setFullscreen(fullscreen);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
		if (getTime() - lastTime > 1000) {
			lastTime += 1000;
			fps = curFPS;
			curFPS = 0;
		}
		curFPS++;
		if (!console)
			stateLayers.get(stateLayers.size() - 1).update();
		Display.setTitle("Karthas - FPS: " + getFPS());
	}

	/**
	 * Renders the {@code GUI}
	 * @param onlyUpdate If true, will only update display
	 */
	public static void render(boolean updateOnly) {
		setDelta();
		//glRecti(50, 50, 100, 100);
		renderDebug();
		if (!updateOnly)
			stateLayers.get(stateLayers.size() - 1).render();
		Display.update();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		Display.sync(60);
	}

	private static void renderDebug() {
		if (!Karthas.DEBUG)
			return;
		drawRect(0, 0, 1, 1, DEFAULT_FONT_COLOR);
		drawString(0, 0, String.format("Resolution: %sx%s\nFPS: %s", Display.getWidth(), Display.getHeight(), GUI.getFPS()), DEFAULT_FONT_COLOR, font);
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Returns the delta
	 * @return delta
	 */

	public static double getDelta() {
		return delta;
	}

	/**
	 * Calculate how many milliseconds have passed
	 * since last frame.
	 */
	private static void setDelta() {
		long time = getTime();
		double delta = time - lastFrame;
		lastFrame = time;
		GUI.delta = delta;
	}

	public static int getFPS() {
		return fps;
	}

	public static void initTime() {
		lastTime = getTime();
		lastFrame = getTime();
	}

}
