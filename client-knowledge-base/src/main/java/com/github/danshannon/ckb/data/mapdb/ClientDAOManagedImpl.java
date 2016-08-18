package com.github.danshannon.ckb.data.mapdb;

import java.util.Map;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.github.danshannon.ckb.data.ClientDAO;
import com.github.danshannon.ckb.model.Client;

import io.dropwizard.lifecycle.Managed;

public class ClientDAOManagedImpl implements ClientDAO, Managed {
	private DB					database;
	private Map<Long, Client>	clients;
	private Map<String, Long>	ids;

	public void start() throws Exception {
		this.database = DBMaker.fileDB("client.db").make();
		final Serializer<Client> keySerializer = new ClientSerializer();
		this.clients = this.database.hashMap("clients").keySerializer(Serializer.LONG).valueSerializer(keySerializer)
				.createOrOpen();
		this.ids = this.database.hashMap("ids", Serializer.STRING, Serializer.LONG).createOrOpen();

	}

	public void stop() throws Exception {
		this.database.rollback();
		this.database.close();

	}

	public Client get(final Long id) {
		if (id == null) {
			return null;
		}
		final Client client = this.clients.get(id);
		return client;
	}

	private synchronized Long getNewId() {
		Long id = this.ids.get("client");
		if (id == null) {
			id = 0L;
		}
		id++;
		this.ids.put("client", id);
		return id;
	}

	public Client create(final Client client) {

		final Long id = getNewId();
		client.setId(id);
		this.clients.put(id, client);
		return client;
	}

	public Client update(final Long id, final Client client) {
		this.clients.put(client.getId(), client);
		return client;
	}

	public Client delete(final Long id) {
		final Client client = this.clients.get(id);
		this.clients.remove(id);
		return client;
	}

}
