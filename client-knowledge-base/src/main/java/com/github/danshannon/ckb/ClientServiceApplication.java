package com.github.danshannon.ckb;

import com.github.danshannon.ckb.data.mapdb.ClientDAOManagedImpl;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ClientServiceApplication extends Application<ClientServiceConfiguration>{

	public static void main(String[] args) throws Exception {
		new ClientServiceApplication().run(args);
	}

	@Override
	public String getName() {
		return "client-service";
	}

	@Override
	public void run(ClientServiceConfiguration configuration, Environment environment) throws Exception {
		final ClientDAOManagedImpl dao = new ClientDAOManagedImpl();
		environment.lifecycle().manage(dao);

		final ClientServiceResource resource = new ClientServiceResource();
		resource.setDao(dao);
		environment.jersey().register(resource);
		
		final ClientCreateServiceResource createResource = new ClientCreateServiceResource();
		createResource.setDao(dao);
		environment.jersey().register(createResource);
		
		environment.jersey().register(new ClientServiceExceptionMapper());
		
	}
	
	
	
}
