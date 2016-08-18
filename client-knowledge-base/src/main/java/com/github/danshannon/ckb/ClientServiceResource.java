package com.github.danshannon.ckb;

import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.github.danshannon.ckb.data.ClientDAO;
import com.github.danshannon.ckb.data.DAO;
import com.github.danshannon.ckb.data.DataNotFoundException;
import com.github.danshannon.ckb.data.DataPrivacyException;
import com.github.danshannon.ckb.data.DataSecurityException;
import com.github.danshannon.ckb.model.Client;

@Path("/client/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class ClientServiceResource {
	private ClientDAO dao;

	public DAO<Client, Long> getDao() {
		return this.dao;
	}

	public void setDao(final ClientDAO dao) {
		this.dao = dao;
	}

	@GET
	@Timed
	public Client getClient(@PathParam("id") final Long id) {
		Client client;
		try {
			client = this.dao.get(id);
		} catch (final DataNotFoundException e) {
			throw new NotFoundException(e);
		} catch (final DataSecurityException e) {
			throw new ForbiddenException(e);
		} catch (final DataPrivacyException e) {
			throw new ForbiddenException(e);
		}
		if (client == null) {
			throw new NotFoundException("No client found with id = " + id);
		}
		return client;
	}

	@PUT
	@Timed
	public Client updateClient(final Client client) {
		return this.dao.update(client);
	}

	@DELETE
	@Timed
	public Client deleteClient(@PathParam("id") final Long id) {
		return this.dao.delete(id);
	}
}
