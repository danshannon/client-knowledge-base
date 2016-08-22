package com.github.danshannon.ckb;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.codahale.metrics.annotation.Timed;
import com.github.danshannon.ckb.data.DAO;
import com.github.danshannon.ckb.data.mapdb.DataDuplicateException;
import com.github.danshannon.ckb.model.Client;

/**
 *
 *
 * @author Dan Shannon
 *
 */
@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientCreateServiceResource {
	private DAO<Client, Long> dao;

	/**
	 * @return The Client DAO
	 */
	public DAO<Client, Long> getDao() {
		return this.dao;
	}

	/**
	 * @param dao
	 *            The Client DAO
	 */
	public void setDao(final DAO<Client, Long> dao) {
		this.dao = dao;
	}

	/**
	 * @param client
	 *            The client to create
	 * @return The Client created, will include the id
	 */
	@POST
	@Timed
	public Client postClient(final Client client) {
		try {
			return this.dao.create(client);
		} catch (final DataDuplicateException e) {
			throw new WebApplicationException("Client already exists", e, Status.CONFLICT);
		}
	}

}
