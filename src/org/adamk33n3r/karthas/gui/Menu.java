package org.adamk33n3r.karthas.gui;

// Java imports
import java.util.ArrayList;
import java.util.Iterator;

// My import
import org.adamk33n3r.karthas.Renderable;
import org.adamk33n3r.karthas.Resources;
import org.adamk33n3r.karthas.SoundPlayer;

/**
 * A class that defines a menu that contains {@link MenuItem}s
 * @author adamk33n3r
 *
 */
public class Menu implements Renderable {

	public ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	private int selectedItem = -1;

	private int width;
	private int height;
	
	/**
	 * Creates a {@code Menu}
	 * @param parent This menu's parent menu. {@code null} if none.
	 * @param items - Items to add to the menu
	 */
	public Menu(MenuItem... items) {
		setWidth(300);
		if (items.length > 0) {
			for (int i = 0; i < items.length; i++) {
				items[i].position = i;
				items[i].setParent(this);
				this.items.add(items[i]);
			}
			selectedItem = 0;
			this.items.get(selectedItem).selected = true;
		}
		setHeight(this.items.size() * 25 - 5);
	}
	
	/**
	 * Adds a {@code MenuItem} to the menu
	 * @param item - The item to add
	 */
	public void addItem(MenuItem item) {
		items.add(item);
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public MenuItem getSelected() {
		return items.get(selectedItem);
	}
	
	/**
	 * Moves the selected item to the next one. Nothing if the current one is the last
	 */
	public void nextItem() {
		items.get(selectedItem).selected = false;
		SoundPlayer.play(Resources.SOUNDS.menuSelect, .5f, 1.0f);
		selectedItem = selectedItem == items.size() - 1 ? selectedItem : selectedItem + 1;
		items.get(selectedItem).selected = true;
	}
	
	/**
	 * Moves the selected item to the previous one. Nothing it the current one is the first
	 */
	public void prevItem() {
		items.get(selectedItem).selected = false;
		SoundPlayer.play(Resources.SOUNDS.menuSelect, .5f, 1.0f);
		selectedItem = selectedItem < 1 ? selectedItem : selectedItem - 1;
		items.get(selectedItem).selected = true;
	}
	
	public void toItem(int num) {
		if (num <= items.size() && num >= 0) {
			items.get(selectedItem).selected = false;
			selectedItem = num - 1;
			items.get(selectedItem).selected = true;
		}
	}

	/**
	 * Renders the {@code Menu}
	 */
	@Override
	public void render() {
		for(Iterator<MenuItem> it = items.iterator(); it.hasNext();)
			it.next().render();
	}

	@Override
	public void update() {
		
	}

}
