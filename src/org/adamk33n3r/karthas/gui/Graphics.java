package org.adamk33n3r.karthas.gui;

import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glRecti;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Point;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;


public class Graphics {

	public static final Color DEFAULT_MENU_COLOR = Color.blue;
	public static final Color DEFAULT_DISABLED_COLOR = Color.gray;
	public static final Color DEFAULT_BORDER_COLOR = Color.gray;
	public static final Color DEFAULT_FONT_COLOR = Color.yellow;
	public static final Color DEFAULT_SELECTED_MENU_COLOR = new Color(255, 225, 0);
	public static final Color DEFAULT_SELECTED_FONT_COLOR = Color.orange;
	
	public static AngelCodeFont font = null;
	public static AngelCodeFont font2 = null;

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
		img.draw(0, 0, GUI.width, GUI.height);
		glDisable(GL_TEXTURE_2D);
	}
	
	/**
	 * Renders an image stretched to fullscreen while maintaining aspect ratio
	 * @param img
	 */
	public static void drawImageFullToScale(Image img) {
		int tw = 0, th = 0;
		while (tw < GUI.width && th < GUI.height) {
			tw++;
			th++;
		}

		img.draw((GUI.width - tw) / 2, (GUI.height - th) / 2, tw, th);
	}

	/**
	 * Sets the color of the {@code GUI} rendering
	 * @param color - The color to set
	 */
	private static void setColor(Color color) {
		glColor4f(color.r, color.g, color.b, color.a);
	}

}
