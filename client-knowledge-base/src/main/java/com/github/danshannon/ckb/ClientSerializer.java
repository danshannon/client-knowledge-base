package com.github.danshannon.ckb;

import java.io.IOException;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.danshannon.ckb.model.Client;

public class ClientSerializer implements Serializer<Client> {
	ObjectMapper mapper = new ObjectMapper();

	public int compare(Client o1, Client o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Client deserialize(DataInput2 input, int available) throws IOException {
		return mapper.readValue(input.readLine(), Client.class);
	}

	public void serialize(DataOutput2 output, Client client) throws IOException {
		output.writeBytes(mapper.writeValueAsString(client));
		
	}

}
