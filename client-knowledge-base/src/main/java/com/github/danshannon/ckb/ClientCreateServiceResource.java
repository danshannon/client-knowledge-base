package com.github.danshannon.ckb;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.github.danshannon.ckb.data.DAO;
import com.github.danshannon.ckb.model.Client;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientCreateServiceResource {
	private DAO<Client, Long> dao;
	
	public DAO<Client, Long> getDao() {
		return dao;
	}

	public void setDao(DAO<Client, Long> dao) {
		this.dao = dao;
	}
	
	@POST
	@Timed
	public Client postClient(Client client) {
		return dao.create(client);
	}
	
}
