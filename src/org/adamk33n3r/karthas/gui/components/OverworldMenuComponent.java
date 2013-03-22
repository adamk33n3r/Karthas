package org.adamk33n3r.karthas.gui.components;

import org.adamk33n3r.karthas.Karthas;
import org.adamk33n3r.karthas.entities.Human;
import org.adamk33n3r.karthas.gui.GUI;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class OverworldMenuComponent extends MenuComponent {
	
	boolean using_menu = true;
	Human player;
	
	/**
	 * Overworld menu and controls
	 * @param player - You
	 */
	public OverworldMenuComponent(Human player) {
		this.player = player;
	}
	
	@Override
	public void update() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				int key = Keyboard.getEventKey();
				if (using_menu && key >= Keyboard.KEY_1 && key < Keyboard.KEY_0) {
					menu.toItem(key - 1);
					menu.getSelected().execute();
				} else {
					switch (key) {
						case Keyboard.KEY_F:
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
							break;
						case Keyboard.KEY_M:
							using_menu = !using_menu;
							break;
						case Keyboard.KEY_UP:
							if (using_menu)
								menu.prevItem();
							else
								player.moveUp();
							break;
						case Keyboard.KEY_DOWN:
							if (using_menu)
								menu.nextItem();
							else
								player.moveDown();
							break;
						case Keyboard.KEY_LEFT:
							if (!using_menu)
								player.moveLeft();
							break;
						case Keyboard.KEY_RIGHT:
							if (!using_menu)
								player.moveRight();
							break;
						case Keyboard.KEY_RETURN:
							if (using_menu && !Keyboard.isRepeatEvent())
								menu.getSelected().execute();
							break;
					}
				}
			}
		}
	}
	
	@Override
	public void render() {
		super.render();
		if (!using_menu)
			GUI.drawRect(0, GUI.height - 120, GUI.width, GUI.height, new Color(0, 0, 0, 100));
	}
}
