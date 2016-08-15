package com.github.danshannon.ckb;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.commercehub.dropwizard.mongo.MongoClientFactory;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ClientServiceConfiguration extends Configuration {
	@Valid
	@NotNull
	private MongoClientFactory mongo;
	
	@JsonProperty
	public MongoClientFactory getMongo() {
		return this.mongo;
	}
	
	@JsonProperty
	public void setMongo(MongoClientFactory mongo) {
		this.mongo = mongo;
	}

}
