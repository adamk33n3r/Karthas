package org.adamk33n3r.utils;

import org.adamk33n3r.karthas.Karthas;

public class Timer {
	
	static Thread thread;
	
	/**
	 * Runs the {@code TimerAction} after {@code delay} milliseconds
	 * @param action The action to execute
	 * @param delay The delay in milliseconds to wait before executing
	 */
	public static void run(final TimerAction action, final int delay) {
		thread = new Thread() {
			@Override
			public void run() {
				try {
					java.lang.Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}if (Karthas.DEBUG) System.out.println("Executing timer action...");
				action.run();
			}
		};thread.setName("Timer thread");
		thread.start();
	}
	
}
