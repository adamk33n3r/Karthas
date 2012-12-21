package org.adamk33n3r.karthas.gui;

// Java import
import java.util.ArrayList;
import java.util.Iterator;

// Slick import for font rendering
import org.newdawn.slick.Color;

// My import
import org.adamk33n3r.karthas.Renderable;

/**
 * A class that defines a menu that contains {@link MenuItem}s
 * @author adamk33n3r
 *
 */
public class Menu implements Renderable {

	public ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	public int selectedItem = -1;

	int width, height;
	
	private Menu parent;
	
	/**
	 * Creates a {@code Menu}
	 * @param parent This menu's parent menu. {@code null} if none.
	 * @param items - Items to add to the menu
	 */
	public Menu(Menu parent, MenuItem... items) {
		width = GUI.width - 100;
		this.parent = parent;
		if (items.length > 0) {
			for (int i = 0; i < items.length; i++) {
				items[i].position = i;
				items[i].setParent(this);
				this.items.add(items[i]);
			}
			selectedItem = 0;
		}
		height = this.items.size() * 25 - 5;
	}
	
	/*public void addItems(MenuItem... items){
		if (items.length > 0) {
			for (int i = 0; i < items.length; i++) {
				items[i].position = i;
				this.items.add(items[i]);
			}
			selectedItem = 0;
		}
		height = this.items.size() * 25 - 5;
	}*/
	
	/**
	 * Adds a {@code MenuItem} to the menu
	 * @param item - The item to add
	 */
	public void addItem(MenuItem item) {
		items.add(item);
	}
	
	/**
	 * Returns the parent of the menu
	 * @return The parent
	 */
	public Menu getParent() {
		return parent;
	}
	
	/**
	 * Moves the selected item to the next one. Nothing if the current one is the last
	 */
	public void nextItem() {
		selectedItem = selectedItem == items.size() - 1 ? selectedItem : selectedItem + 1;
	}
	
	/**
	 * Moves the selected item to the previous one. Nothing it the current one is the first
	 */
	public void prevItem() {
		selectedItem = selectedItem < 1 ? selectedItem : selectedItem - 1;
	}

	/**
	 * Renders the {@code Menu}
	 */
	@Override
	public void render() {
		GUI.drawRect(45, GUI.height - 45 - height, 45 + width + 10, GUI.height - 45 + 10, Color.gray);
		int i = GUI.height - 40 - height;
		for(Iterator<MenuItem> it = items.iterator(); it.hasNext(); i+=25){
			MenuItem cur = it.next();
			if(cur == items.get(selectedItem))
				GUI.drawRect(50, i, 50 + width, i + 20, Color.red);
			else
				GUI.drawRect(50, i, 50 + width, i + 20, Color.blue);
			cur.render();
		}
	}

	@Override
	public void update() {
		
	}

}
