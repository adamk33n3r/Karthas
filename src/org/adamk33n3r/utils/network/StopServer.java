package org.adamk33n3r.utils.network;

import com.db4o.ObjectContainer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.messaging.MessageSender;

/**
 * Got cod from tutorial
 *
 */
public class StopServer implements ServerInfo {
	public static void main(String[] args) {
		ObjectContainer objectContainer = null;
		try {
			// connect to the server
			System.out.println("Opening up connection...");
			objectContainer = Db4oClientServer.openClient(Db4oClientServer.newClientConfiguration(), HOST, PORT, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (objectContainer != null) {
			// get the messageSender for the ObjectContainer
			MessageSender messageSender = objectContainer.ext().configure().clientServer().getMessageSender();
			// send an instance of a StopServer object
			System.out.println("...sending stop message...");
			messageSender.send(new StopServer());
			System.out.println("...sent");
		}
	}
}
