package org.adamk33n3r.karthas.gui.components;

//My imports
import org.adamk33n3r.karthas.gui.Menu;
import org.adamk33n3r.karthas.Input;
import org.adamk33n3r.karthas.InputLockedException;

public abstract class MenuComponent extends Component {
	Menu menu;
	
	public MenuComponent() {
		type = Component.Type.MENU;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	@Override
	public void update() {
		try {
			Input.processMenu(menu, this);
		} catch (InputLockedException e) {
			//e.printStackTrace();
		}
	}
	
	@Override
	public void render() {
		menu.render();
	}
	
}
