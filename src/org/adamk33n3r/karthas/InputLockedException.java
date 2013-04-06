package org.adamk33n3r.karthas;

public class InputLockedException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public InputLockedException() {
		super();
	}
	
	public InputLockedException(String message) {
		super(message);
	}
	
	public InputLockedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InputLockedException(Throwable cause) {
		super(cause);
	}
	
}
