package org.adamk33n3r.karthas.gui.components;

// My imports
import org.adamk33n3r.karthas.gui.InputHandler;
import org.adamk33n3r.karthas.gui.Menu;
import org.lwjgl.input.Keyboard;

public abstract class MenuComponent extends Component {
	
	Menu menu;
	
	public MenuComponent() {
		this.type = Component.Type.MENU;
		
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	@Override
	public void update(boolean canHandleInput) {
		//System.out.println(this + ":" + canHandleInput);
		if (canHandleInput) {
			if (InputHandler.isKeyDown(Keyboard.KEY_UP))
				menu.prevItem();
			if (InputHandler.isKeyDown(Keyboard.KEY_DOWN))
				menu.nextItem();
			if (InputHandler.isKeyDown(Keyboard.KEY_RETURN))
				menu.getSelected().execute();
			
		}
	}
	
	@Override
	public void render() {
		menu.render();
	}
	
}
