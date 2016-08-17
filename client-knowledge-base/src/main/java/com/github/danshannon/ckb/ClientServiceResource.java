package com.github.danshannon.ckb;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.github.danshannon.ckb.model.Client;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientServiceResource {
	private DAO<Client, String> dao;
	
	public DAO<Client, String> getDao() {
		return dao;
	}

	public void setDao(DAO<Client, String> dao) {
		this.dao = dao;
	}

	@GET
	@Timed
	public Client getClient(@QueryParam("id") String id) {
		Client client = dao.get(id);
		if (client == null) {
			throw new NotFoundException("No client found with id = " + id);
		}
		return client;
	}
	
	@POST
	@Timed
	public Client postClient(Client client) {
		return dao.create(client);
	}
}
