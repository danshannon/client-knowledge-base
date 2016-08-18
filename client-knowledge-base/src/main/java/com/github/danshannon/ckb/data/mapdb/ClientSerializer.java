package com.github.danshannon.ckb.data.mapdb;

import java.io.IOException;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.danshannon.ckb.model.Client;

public class ClientSerializer implements Serializer<Client> {
	ObjectMapper mapper = new ObjectMapper();

	public int compare(final Client o1, final Client o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Client deserialize(final DataInput2 input, final int available) throws IOException {
		return this.mapper.readValue(input.readLine(), Client.class);
	}

	public void serialize(final DataOutput2 output, final Client client) throws IOException {
		output.writeBytes(this.mapper.writeValueAsString(client));

	}

}
