package com.github.danshannon.ckb;

import com.commercehub.dropwizard.mongo.ManagedMongoClient;
import com.mongodb.client.MongoDatabase;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
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
	public void initialize(Bootstrap<ClientServiceConfiguration> bootstrap) {
		// TODO Auto-generated method stub
		super.initialize(bootstrap);
	}

	@Override
	public void run(ClientServiceConfiguration configuration, Environment environment) throws Exception {
		final ClientServiceResource resource = new ClientServiceResource();
		environment.jersey().register(resource);
		
		ManagedMongoClient mongoClient = configuration.getMongo().build();
        environment.lifecycle().manage(mongoClient);
        MongoDatabase db = mongoClient.getDatabase(configuration.getMongo().getDbName());
		
	}
	
	
}
