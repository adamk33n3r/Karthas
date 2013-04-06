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

import org.adamk33n3r.karthas.Action;
import org.adamk33n3r.karthas.InputAction;
// My imports
import org.adamk33n3r.karthas.Karthas;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.entities.Actor;
import org.adamk33n3r.karthas.gui.components.ConsoleComponent;

public class GUI extends Layer{

	public static final Color DEFAULT_MENU_COLOR = Color.blue;
	public static final Color DEFAULT_DISABLED_COLOR = Color.gray;
	public static final Color DEFAULT_BORDER_COLOR = Color.gray;
	public static final Color DEFAULT_FONT_COLOR = Color.yellow;
	public static final Color DEFAULT_SELECTED_MENU_COLOR = new Color(255, 225, 0);
	public static final Color DEFAULT_SELECTED_FONT_COLOR = Color.orange;

	static GUI gui = null;

	static boolean running = true;

	private static long lastFrame;
	private static int fps, curFPS;
	private static double delta;
	private static long lastTime;

	static HashMap<String, Layer> stateMap;
	static LinkedList<Layer> layers;

	public static int width, height;
	public static boolean fullscreen, fullscreenChanged;

	public static AngelCodeFont font = null;
	public static AngelCodeFont font2 = null;
	
	private static boolean inputCheck = false;
	private static StringBuilder inputText;
	private static InputAction inputAction;

	boolean downloading;

	private GUI(String title, int width, int height) {
		GUI.width = width;
		GUI.height = height;

		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create();
			initGL();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		Resources.downloadInit();
		if (Resources.needDownload()) {
			Karthas.printDebug("needs to down");
			Resources.downloadFirst();
			try {
				String file = Karthas.home + "/resources/Chalkduster24.fnt";
				String file2 = Karthas.home + "/resources/Chalkduster24.png";
				font = new AngelCodeFont(file, file2);
				file = Karthas.home + "/resources/Chalkduster20.fnt";
				file2 = Karthas.home + "/resources/Chalkduster20.png";
				font2 = new AngelCodeFont(file, file2);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			stateMap = LayerBuilder.downloading();
			Karthas.printDebug("Downloading resources...");
			//GUI.addLayer("Downloading");
			downloading = true;
			new Thread() {
				@Override
				public void run() {
					Resources.downloadResources();
					downloading = false;
				}
			}.start();

			while (downloading) {
				//update();
				//gui.render();
			}
			//GUI.goBack();
		} else {
			try {
				String file = Karthas.home + "/resources/Chalkduster24.fnt";
				String file2 = Karthas.home + "/resources/Chalkduster24.png";
				font = new AngelCodeFont(file, file2);
				file = Karthas.home + "/resources/Chalkduster20.fnt";
				file2 = Karthas.home + "/resources/Chalkduster20.png";
				font2 = new AngelCodeFont(file, file2);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		Resources.load();
	}

	/**
	 * Creates a {@code GUI}
	 * @param title - The title of the {@code GUI}
	 * @param width - The width of the {@code GUI}
	 * @param height - The height of the {@code GUI}
	 */
	public static GUI create(String title, int width, int height) {
		Keyboard.enableRepeatEvents(true);
		layers = new LinkedList<Layer>();

		gui = new GUI(title, width, height);

		stateMap = LayerBuilder.build();
		GUI.addLayer("Title");
		initTime();
		setDelta();
		//GUI.changeTo("Downloading");
		return gui;
	}

	private static void initGL() {
		glShadeModel(GL_SMOOTH);
		glDisable(GL_LIGHTING);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		//glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
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
	public void shutdown() {
		running = false;
	}

	/**
	 * Destroys the display
	 */
	public void destroy() {
		Display.destroy();
	}

	public static Layer getCurrentLayer() {
		return layers.peekLast();
	}

	/**
	 * Changes the current layer
	 * @param menu - The {@link Menu} to change to
	 */
	public static void addLayer(Layer state) {
		layers.add(state);
	}

	/**
	 * Changes the current layer
	 * @param menuName - The name of the {@code Menu} to change to
	 */
	public static void addLayer(String stateName) {
		Layer newState = stateMap.get(stateName);
		if (newState != null)
			addLayer(newState);
		else
			System.err.println("Can't find state!");
	}

	public static void goBack() {
		layers.pollLast();
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

			setFullscreen(fullscreen);

		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
		}
	}
	
	/**
	 * Sets fullscreen flag
	 * @param fullscreen
	 */
	private static void setFullscreen(boolean fullscreen) {
		if (GUI.fullscreen != fullscreen)
			GUI.fullscreenChanged = true;
		else
			GUI.fullscreenChanged = false;
		GUI.fullscreen = fullscreen;
		Karthas.printDebug("GUI is " + GUI.fullscreen + " and display is " + Display.isFullscreen());
	}
	
	/**
	 * Renders the current state
	 */
	public static void renderState() {
		layers.get(layers.size() - 1).render();
	}
	
	public static void drawChar(int x, int y, char ch, Color color, Font font) {
		glEnable(GL_TEXTURE_2D);
		font.drawString(x, y, Character.toString(ch), color);
		glDisable(GL_TEXTURE_2D);
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
	/**
	 * Renders a string to the screen, centered on x
	 * @param x - The x location to print the text
	 * @param y - The y location to print the text
	 * @param string - The text to print
	 * @param color - The color to print the text in
	 * @param font - The font in which to print the text
	 */
	public static void drawStringCentered(int x, int y, String string, Color color, Font font) {
		glEnable(GL_TEXTURE_2D);

		font.drawString(x - font.getWidth(string) / 2, y, string, color);

		glDisable(GL_TEXTURE_2D);
	}
	
	/**
	 * Draws a rectangle
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param color
	 */
	public static void drawRect(int x1, int y1, int x2, int y2, Color color) {
		setColor(color);
		glRecti(x1, y1, x2, y2);
	}
	
	/**
	 * Draws a rectangle with a border
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param color
	 * @param borderWidth
	 * @param borderColor
	 */
	public static void drawRect(int x1, int y1, int x2, int y2, Color color, int borderWidth, Color borderColor) {
		drawRect(x1 - borderWidth, y1 - borderWidth, x2 + borderWidth, y2 + borderWidth, borderColor);
		drawRect(x1, y1, x2, y2, color);
	}
	 
	/**
	 * Draws a polygon
	 * @param color
	 * @param points - Points to draw
	 */
	public static void drawPolygon(Color color, Point... points) {
		setColor(color);
		glBegin(GL_POLYGON);
		for (int i = 0; i < points.length; i++)
			glVertex2f(points[i].x, points[i].y);
		glEnd();
	}
	
	/**
	 * Renders image to screen at x,y, keeping size
	 * @param img - The image to render
	 * @param x
	 * @param y
	 */
	public static void drawImage(Image img, int x, int y) {
		img.draw(x, y);
		glDisable(GL_TEXTURE_2D);
	}
	
	/**
	 * Renders an image stretched fullscreen
	 * @param img - The image to render
	 */
	public static void drawImageFull(Image img) {
		img.draw(0, 0, width, height);
		glDisable(GL_TEXTURE_2D);
	}
	
	/**
	 * Renders an image stretched to fullscreen while maintaining aspect ratio
	 * @param img
	 */
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
	 * @param prompt - The prompt
	 * @param text - The {@code String} to set the input to
	 * @param action - The code to execute once the input has been retrieved
	 */
	public static void newConsole(String prompt, StringBuilder text, InputAction action) {
		addLayer(new Console(prompt, text, layers.getLast()));
		inputText = text;
		inputAction = action;
	}
	
	/*public static String getCurrentConsoleText() {
		String string = ((ConsoleComponent)((Popup) layers.getLast()).children[0]).getText();
		layers.pop();
		return string;
	}*/
	
	public static void setInputCheck() {
		inputCheck = true;
	}

	public static void getUserConfirmation(Layer parent) {
		//addLayer(new ConfirmPopup(parent));
	}

	/**
	 * Updates. Keyboard events
	 */
	@Override
	public void update() {
		
		if (inputCheck) {
			inputAction.execute(inputText);
			inputCheck = false;
		}

		if (Display.wasResized()) {
			width = Display.getWidth();
			height = Display.getHeight();
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glViewport(0, 0, width, height);
			glOrtho(0, width, height, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);
			System.out.println("resized to: " + width + "x" + height);
		}
		if (fullscreen && !Display.isFullscreen() || !fullscreen && fullscreenChanged) {
			try {
				Display.setFullscreen(fullscreen);
				fullscreenChanged = false;
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
		layers.getLast().update();
		Display.setTitle("Karthas - FPS: " + getFPS());
	}

	/**
	 * Renders the {@code GUI}
	 * @param onlyUpdate If true, will only update display
	 */
	@Override
	public void render() {
		setDelta();
		//glRecti(50, 50, 100, 100);
		renderDebug();
		layers.getLast().render();
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
