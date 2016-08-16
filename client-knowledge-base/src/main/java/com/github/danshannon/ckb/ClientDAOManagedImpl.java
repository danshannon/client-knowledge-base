package com.github.danshannon.ckb;

import java.util.Map;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.github.danshannon.ckb.model.Client;

import io.dropwizard.lifecycle.Managed;

public class ClientDAOManagedImpl implements DAO<Client, String>, Managed {
	private DB database;
	private Map<String, Client> clients;
	long id = 0L;

	public void start() throws Exception {
		this.database = DBMaker.fileDB("Client.db").make();
		Serializer<Client> keySerializer = new ClientSerializer();
		this.clients = database.hashMap("clients").keySerializer(Serializer.STRING).valueSerializer(keySerializer).createOrOpen();
		
	}

	public void stop() throws Exception {
		this.database.close();
		
	}

	public Client get(String id) {
		Client client = clients.get(id);
		return client;
	}

	public Client create(Client client) {
		id++;
		String stringId = new Long(id).toString();
		client.setId(stringId);
		clients.put(stringId, client);
		return client;
	}

}
