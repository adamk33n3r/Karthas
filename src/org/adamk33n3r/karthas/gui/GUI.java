package org.adamk33n3r.karthas.gui;

// Java import
import java.awt.Point;
import java.util.HashMap;

import org.adamk33n3r.karthas.ResizableImage;
// LWGL imports
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

// Slick import for font rendering
import org.newdawn.slick.Font;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Image;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class GUI {
	
	public static final Color DEFAULT_MENU_COLOR = Color.blue;
	public static final Color DEFAULT_DISABLED_COLOR = Color.gray;
	public static final Color DEFAULT_BORDER_COLOR = Color.gray;
	public static final Color DEFAULT_FONT_COLOR = Color.yellow;
	public static final Color DEFAULT_SELECTED_MENU_COLOR = new Color(255,225,0);
	public static final Color DEFAULT_SELECTED_FONT_COLOR = Color.orange;

	static GUI gui = null;

	static boolean running = true;
	
	private static long lastFrame;
	private static int fps, curFPS;
	private static double delta;
	private static long lastTime;

	static HashMap<String, State> stateMap;
	static State curState = null;
	static boolean console = false;

	public static int width, height;

	public static AngelCodeFont font = null;

	private GUI(String title, int width, int height) {
		GUI.width = width;
		GUI.height = height;

		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
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
		Keyboard.enableRepeatEvents(true);
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

		stateMap = StateBuilder.create();
		curState = stateMap.get("Title");
		
		try {
			//font = new AngelCodeFont("resources/Impact24.fnt", new Image("resources/Impact24.png"));
			//font = new AngelCodeFont("resources/ComicSans32.fnt", new Image("resources/ComicSans32.png"));
			//font = new AngelCodeFont("resources/ComicSans24Bold.fnt", new Image("resources/ComicSans24Bold.png"));
			font = new AngelCodeFont("resources/Chalkduster24.fnt", new Image("resources/Chalkduster24.png"));
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
	
	public static State getCurrentState() {
		return curState;
	}

	/**
	 * Changes the current menu
	 * @param menu - The {@link Menu} to change to
	 */
	public static void changeTo(State state) {
		curState = state;
	}

	/**
	 * Changes the current menu
	 * @param menuName - The name of the {@code Menu} to change to
	 */
	public static void changeTo(String stateName) {
		State newState = stateMap.get(stateName);
		if (newState != null)
			changeTo(newState);
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
	
	public static void renderState() {
		curState.render();
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
		glBegin(GL11.GL_POLYGON);
		for(int i = 0; i < points.length; i++)
			glVertex2f(points[i].x, points[i].y);
		glEnd();
	}
	
	public static void drawImage(Image img, int x, int y) {
		img.draw(x, y);
		glDisable(GL_TEXTURE_2D);
	}
	
	public static void drawResizableImage(ResizableImage img, int x, int y, int width, int height) {
		img.getImage(width, height).draw(x, y);
		glDisable(GL_TEXTURE_2D);
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
			if(Display.isCloseRequested()) {
				shutdown();
				break;
			}
			Console.update();
			Console.render();
		}console = false;
		return Console.getInput();
	}
	
	public static boolean confirm() {
		return Console.confirm();
	}
	
	/**
	 * Updates. Keyboard events
	 */
	public static void update() {
		if(getTime() - lastTime > 1000) {
			lastTime+=1000;
			fps = curFPS;
			curFPS = 0;
		}curFPS++;
		if (!console)
			curState.update();
		Display.setTitle("Karthas - FPS: " + getFPS());
	}

	/**
	 * Renders the {@code GUI}
	 * @param onlyUpdate If true, will only update display
	 */
	public static void render(boolean updateOnly) { //TODO Render "layers" of states
		setDelta();
		if(!updateOnly)
			curState.render();
		Display.update();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		Display.sync(60);
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
