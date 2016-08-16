package com.github.danshannon.ckb;

public interface DAO<U, V> {
	public U get(V id);
	public U create(U client);
}
