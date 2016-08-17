package com.github.danshannon.ckb;

import java.util.HashMap;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.eclipse.jetty.http.HttpStatus;

public class ClientServiceExceptionMapper implements ExceptionMapper<WebApplicationException> {

	public Response toResponse(WebApplicationException e) {
		// If not status, set to 500 by default
		int status = e.getResponse() == null ? 500 : e.getResponse().getStatus();

		final String message = e.getMessage() == null ? HttpStatus.getMessage(status) : status + ": " + e.getMessage();

		return Response.status(status).type(MediaType.APPLICATION_JSON_TYPE).entity(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;

			{
				put("error", message);
			}
		}).build();
	}

}
