package com.github.danshannon.ckb;

import javax.ws.rs.GET;
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
	private ClientDAO dao;
	
	public ClientDAO getDao() {
		return dao;
	}

	public void setDao(ClientDAO dao) {
		this.dao = dao;
	}

	@GET
	@Timed
	public Client getClient(@QueryParam("id") String id) {
		return new Client(id,"John Doe");
		// TODO return dao.getClient(id);
	}
	
	@POST
	@Timed
	public Client postClient(Client client) {
		return dao.createClient(client);
	}
}
