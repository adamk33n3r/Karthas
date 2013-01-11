package org.adamk33n3r.karthas;

import java.util.HashMap;

public class Resources {
	
	private static HashMap<String, Object> resourceMap;
	
	public static void load() {
		if(resourceMap != null)
			return;
		else {
			resourceMap = new HashMap<String, Object>();
			ResizableImage componentBack = new ResizableImage("resources/MenuItemBack.png", 10, 1, 10, 10, 1, 10);
			resourceMap.put("ComponentBack", componentBack);
			resourceMap.put("MenuItemBack", componentBack.build(300, 30));
		}
	}
	
	public static Object get(String string) {
		return resourceMap.get(string);
	}
	
	public static void set(String string, Object obj) {
		resourceMap.put(string, obj);
	}
}
