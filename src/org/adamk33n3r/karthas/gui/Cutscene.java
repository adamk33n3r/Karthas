package org.adamk33n3r.karthas.gui;

// Java import
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

// My import
import org.adamk33n3r.karthas.Renderable;
import org.adamk33n3r.karthas.gui.Scene.SceneText;

public class Cutscene extends Renderable {

	LinkedList<Scene> scenes;

	int length = 15000, currentScene = 0, currentText = 0, currentLine = 0;

	boolean running = true, change = false;

	Thread timer;

	public Cutscene(Scene... scenes) {
		this.scenes = new LinkedList<Scene>();
		this.scenes.addAll(Arrays.asList(scenes));
	}

	public boolean isRunning() {
		return running;
	}

	@Override
	public void update() {

	}

	@Override
	public void render() {
		Graphics.drawImageFullToScale(scenes.get(currentScene).scenes.get(0));
		if (timer == null) {
			timer = new Thread() {
				public boolean done = false;
				long start = GUI.getTime(), curTime;
				
				@Override
				public void run() {
					while (!done) {
						curTime = GUI.getTime();
						if (curTime - start > scenes.get(0).strings.get(currentText).string.split(" ").length * 300) {
							change = true;
							done = true;
						}
					}
				}
			};timer.start();
		}
		Scene curScene = scenes.get(currentScene);
		SceneText curText = curScene.strings.get(currentText);
		int x = 0;
		for (Iterator<String> it = curText.lines.iterator(); it.hasNext();) {
			String curLine = it.next();
			Graphics.drawString(150, GUI.height - 200 + x, curLine, Graphics.DEFAULT_FONT_COLOR, Graphics.font);
			x += Graphics.font.getLineHeight();
		}
		if (change) {
			currentText++;
			if (scenes.get(currentScene).strings.size() == currentText) {
				currentText = 0;
				currentScene++;
			}
			if (scenes.size() == currentScene)
				running = false;
			change = false;
			timer = null;
		}
	}

}
