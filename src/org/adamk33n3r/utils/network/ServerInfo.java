package org.adamk33n3r.utils.network;

/**
 * Configuration used for {@link StartServer} and {@link StopServer}.
 */
public interface ServerInfo {

	/**
	 * the host to be used. <br>
	 * If you want to run the client server examples on two computers,
	 * enter the computer name of the one that you want to use as server.
	 */
	public String HOST = "75.128.154.47";

	/**
	 * the database file to be used by the server.
	 */
	public String FILE = "serverTestOnServerDB.db4o";

	/**
	 * the port to be used by the server.
	 */
	public int PORT = 1745;

	/**
	 * the user name for access control.
	 */
	public String USER = "adamk33n3r";

	/**
	 * the pasword for access control.
	 */
	public String PASS = "az19k24forza";
}