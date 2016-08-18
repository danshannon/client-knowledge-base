package com.github.danshannon.ckb.data;

import java.util.List;

import com.github.danshannon.ckb.model.Client;

public interface ClientDAO extends DAO<Client, Long> {
	public List<Client> findByName(String name);
}
