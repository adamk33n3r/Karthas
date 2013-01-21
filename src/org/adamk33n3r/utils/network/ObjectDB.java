package org.adamk33n3r.utils.network;

import org.adamk33n3r.karthas.entities.Actor;

import com.db4o.*;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;

public class ObjectDB implements ServerInfo {
	Actor actor;
	public static void main(String[] args) {
		ClientConfiguration config = Db4oClientServer.newClientConfiguration();
		config.common().objectClass(ObjectDB.class).cascadeOnUpdate(true);
		config.common().objectClass(ObjectDB.class).cascadeOnDelete(true);
		ObjectContainer db = Db4oClientServer.openClient(config, HOST, PORT, USER, PASS);
		
		ObjectDB odb = new ObjectDB();
		odb.actor = new Actor(-1,1,"Elton","Sir",1,2,3,4,new Actor(1,-1,"Lilith","Lady",4,3,2,1));
		db.store(odb);
		db.close();
	}
}
