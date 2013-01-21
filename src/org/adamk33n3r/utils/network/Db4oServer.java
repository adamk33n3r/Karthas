package org.adamk33n3r.utils.network;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;
import com.db4o.messaging.MessageContext;
import com.db4o.messaging.MessageRecipient;

/**
 * Got code from tutorial
 *
 */

public class Db4oServer implements ServerInfo, MessageRecipient {

	private boolean stop = false;

	public static void main(String[] args) {
		System.out.println("Starting up server...");
		new Db4oServer().start();
	}

	private void start() {
		synchronized (this) {
			ServerConfiguration config = Db4oClientServer.newServerConfiguration();
			// Using the messaging functionality to redirect all
			// messages to this.processMessage 
			config.networking().messageRecipient(this);
			ObjectServer db4oServer = Db4oClientServer.openServer(config, FILE, PORT);
			db4oServer.grantAccess(USER, PASS);

			// to identify the thread in a debugger
			Thread.currentThread().setName(this.getClass().getName());
			// We only need low priority since the db4o server has
			// it's own thread.
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			try {
				if (!stop) {
					// wait forever for notify() from close()
					System.out.println("...running");
					this.wait(Long.MAX_VALUE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}System.out.println("Shutting down server...");
			db4oServer.close();
		}
	}

	@Override
	public void processMessage(MessageContext con, Object message) {
		if (message instanceof StopServer) {
			close();
		}
	}

	public void close() {
		synchronized (this) {
			stop = true;
			this.notify();
		}
	}

}
