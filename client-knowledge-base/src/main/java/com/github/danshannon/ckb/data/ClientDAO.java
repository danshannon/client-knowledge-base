package com.github.danshannon.ckb.data;

import java.util.List;

import com.github.danshannon.ckb.model.Client;

/**
 * Additional client-specific DAO methods
 *
 * @author Dan Shannon
 *
 */
public interface ClientDAO extends DAO<Client, Long> {
	/**
	 * Find clients by name
	 *
	 * @param name
	 *            The (partial) name to search for
	 * @return List of clients with matching names
	 */
	public List<Client> findByName(String name);
}
