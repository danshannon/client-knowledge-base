package com.github.danshannon.ckb.data.mapdb;

import java.util.List;
import java.util.Map;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.github.danshannon.ckb.data.ClientDAO;
import com.github.danshannon.ckb.data.DataPagingInstructionException;
import com.github.danshannon.ckb.model.Client;

import io.dropwizard.lifecycle.Managed;

public class ClientDAOManagedImpl implements ClientDAO, Managed {
	private DB database;
	private Map<Long, Client> clients;
	private Map<String, Long> ids;

	public void start() throws Exception {
		this.database = DBMaker.fileDB("client.db").make();
		Serializer<Client> keySerializer = new ClientSerializer();
		this.clients = database.hashMap("clients").keySerializer(Serializer.LONG).valueSerializer(keySerializer).createOrOpen();
		this.ids = database.hashMap("ids",Serializer.STRING,Serializer.LONG).createOrOpen();
		
	}

	public void stop() throws Exception {
		this.database.rollback();
		this.database.close();
		
	}

	public Client get(Long id) {
		if (id == null) {
			return null;
		}
		Client client = clients.get(id);
		return client;
	}
	
	private synchronized Long getNewId() {
		Long id = ids.get("client");
		if (id == null) {
			id = 0L;
		}
		id++;
		ids.put("client", id);
		return id;
	}

	public Client create(Client client) {
		
		Long id = getNewId();
		client.setId(id);
		clients.put(id, client);
		return client;
	}

	public Client update(Long id, Client client) {
		clients.put(client.getId(), client);
		return client;
	}

	public Client delete(Long id) {
		Client client = clients.get(id);
		clients.remove(id);
		return client;
	}

	public List<Client> findByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> getPage(Integer page, Integer pageSize) throws DataPagingInstructionException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> createAll(List<Client> objects) {
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

	public List<Client> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
