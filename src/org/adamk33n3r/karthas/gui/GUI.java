package org.adamk33n3r.karthas.gui;

// Java imports
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
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.SlickException;
import org.adamk33n3r.karthas.InputAction;
// My imports
import org.adamk33n3r.karthas.Karthas;
import org.adamk33n3r.karthas.Resources;

public class GUI {
	
	public static final boolean DEBUG = true;
	
	static GUI gui = null;
	
	static boolean running = true;
	
	private static long lastFrame;
	private static int fps, curFPS;
	private static double delta;
	private static long lastTime;
	
	static HashMap<String, State> stateMap;
	static LinkedList<State> states;
	
	public static int width, height;
	public static boolean fullscreen, fullscreenChanged;
	
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
				Graphics.font = new AngelCodeFont(file, file2);
				file = Karthas.home + "/resources/Chalkduster20.fnt";
				file2 = Karthas.home + "/resources/Chalkduster20.png";
				Graphics.font2 = new AngelCodeFont(file, file2);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			stateMap = StateBuilder.downloading();
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
				Graphics.font = new AngelCodeFont(file, file2);
				file = Karthas.home + "/resources/Chalkduster20.fnt";
				file2 = Karthas.home + "/resources/Chalkduster20.png";
				Graphics.font2 = new AngelCodeFont(file, file2);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		Resources.load();
	}
	
	/**
	 * Creates a {@code GUI}
	 * 
	 * @param title - The title of the {@code GUI}
	 * @param width - The width of the {@code GUI}
	 * @param height - The height of the {@code GUI}
	 */
	public static GUI create(String title, int width, int height) {
		Keyboard.enableRepeatEvents(true);
		states = new LinkedList<State>();
		
		gui = new GUI(title, width, height);
		
		stateMap = StateBuilder.build();
		GUI.addState("Title");
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
	 * 
	 * @return true if running, false otherwise
	 */
	public boolean isRunning() {
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
	
	public static State getCurrentState() {
		return states.peekLast();
	}
	
	public static void goBack() {
		states.pollLast();
	}
	
	/**
	 * Changes the current state
	 * 
	 * @param menu - The {@link Menu} to change to
	 */
	public static void addState(State state) {
		states.add(state);
	}
	
	/**
	 * Changes the current state
	 * 
	 * @param menuName - The name of the {@code State} to change to
	 */
	public static void addState(String stateName) {
		State newState = stateMap.get(stateName);
		if (newState != null)
			addState(newState);
		else
			System.err.println("Can't find state!");
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
		if ((Display.getDisplayMode().getWidth() == width) && (Display.getDisplayMode().getHeight() == height) && (Display.isFullscreen() == fullscreen)) { return; }
		
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
			Display.update();
			
			GUI.width = Display.getWidth();
			GUI.height = Display.getHeight();
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			//glViewport(0, 0, width, height);
			glOrtho(0, width, height, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);
			setFullscreen(fullscreen);
			
		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
		}
	}
	
	/**
	 * Sets fullscreen flag
	 * 
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
	 * Gets input from the user using {@link ConsolePopup}
	 * 
	 * @param prompt - The prompt
	 * @param text - The {@code String} to set the input to
	 * @param action - The code to execute once the input has been retrieved
	 */
	public static void newConsole(String prompt, StringBuilder text, InputAction action) {
		if (DEBUG)
			System.out.println("Making a console: " + prompt);
		getCurrentState().addLayer(new ConsolePopup(prompt, text));
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
	public void update() {
		
		InputHandler.handleInput();
		
		if (inputCheck) {
			inputAction.execute(inputText);
			inputCheck = false;
		}
		if (Display.wasResized()) {
			width = Display.getWidth();
			height = Display.getHeight();
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			//glViewport(0, 0, width, height);
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
		states.getLast().update();
		Display.setTitle("Karthas - FPS: " + getFPS());
	}
	
	/**
	 * Renders the {@code GUI}
	 * 
	 * @param onlyUpdate If true, will only update display
	 */
	public void render() {
		setDelta();
		//glRecti(50, 50, 100, 100);
		renderDebug();
		states.getLast().render();
		Display.update();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		Display.sync(60);
	}
	
	private static void renderDebug() {
		if (!Karthas.DEBUG)
			return;
		Graphics.drawRect(0, 0, 1, 1, Graphics.DEFAULT_FONT_COLOR);
		Graphics.drawString(0, 0, String.format("Resolution: %sx%s\nFPS: %s", Display.getWidth(), Display.getHeight(), GUI.getFPS()), Graphics.DEFAULT_FONT_COLOR, Graphics.font);
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
	 * 
	 * @return delta
	 */
	
	public static double getDelta() {
		return delta;
	}
	
	/**
	 * Calculate how many milliseconds have passed since last frame.
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
