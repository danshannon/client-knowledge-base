package com.github.danshannon.ckb.data.mapdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.github.danshannon.ckb.data.ClientDAO;
import com.github.danshannon.ckb.model.Client;

import io.dropwizard.lifecycle.Managed;

/**
 * @author Dan Shannon
 *
 */
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

	public Client delete(final Long id) {
		final Client client = this.clients.get(id);
		this.clients.remove(id);
		return client;
	}

	public List<Client> getAll() throws DataOperationNotSupportedException {
		return new ArrayList<Client>(this.clients.values());
	}

	public List<Client> getPage(Integer page, Integer pageSize)
			throws DataPagingInstructionException, DataOperationNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> createAll(List<Client> objects) {
		final List<Client> clients = new ArrayList<Client>();
		for (final Client client : objects) {
			final Client newClient = this.create(client);
			clients.add(newClient);
		}
		return clients;
	}

	public Client update(Client object) throws DataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> updateAll(List<Client> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> deleteAll(List<Client> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> deleteAllById(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAll() throws DataOperationNotSupportedException {
		throw new DataOperationNotSupportedException("This operation is not supported");
	}

	public Client patch(Client object)
			throws DataNotFoundException, DataSecurityException, DataPrivacyException, DataOperationNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> patchAll(List<Client> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
