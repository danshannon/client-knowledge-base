package com.github.danshannon.ckb;

import com.github.danshannon.ckb.model.Client;

public interface ClientDAO {
	public Client getClient(String id);
	public Client createClient(Client client);
}
