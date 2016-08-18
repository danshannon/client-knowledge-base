package com.github.danshannon.ckb.data;

import java.util.List;

/**
 * General DAO specification for data access behind a service
 * 
 * @author Dan Shannon
 *
 * @param <U> Class of object being accessed by the DAO
 * @param <V> Class of the object's identifier in the data store
 */
public interface DAO<U, V> {
	/**
	 * <p>
	 * Get an object from the data store by its id
	 * </p>
	 * 
	 * @param id
	 * @throws DataNotFoundException if there is no object with the given id
	 * @throws DataSecurityException if the caller does not have access to the object with the given id 
	 * @return The object with the given id
	 */
	public U get(V id) throws DataNotFoundException, DataSecurityException, DataPrivacyException;
	/**
	 * <p>
	 * Get list of objects from the data store with the given ids
	 * </p>
	 * 
	 * <p>
	 * If the list contains an id for which there is no corresponding object in the store, no entry is returned in the list (note that the {@link #get(Object)} method would return a {@link DataNotFoundException}).
	 * </p>
	 * 
	 * <p>
	 * If the list contains an id for which there is no access for security or privacy purposes, no entry is returned in the list (note that the {@link #get(Object)} method would return a {@link DataSecurityException} or {@link DataPrivacyException}).
	 * </p>
	 * 
	 * @param ids
	 * @return
	 */
	public List<U> findByIds(List<V> ids);
	
	/**
	 * <p>
	 * Get paginated list of objects from the data store. The method implementation should guarantee ordering of the object so that pagination is consistent
	 * </p>
	 * 
	 * @param page Page number to be returned (1-indexed, i.e. the first element in the list is on page 1, there is no page 0)
	 * @param perPage Number of objects per page
	 * @return List of objects Returns objects in a defined order, from <code>((page - 1) * perPage + 1)</code> to <code>(page * perPage)</code>
	 */
	public List<U> getPage(Integer page, Integer perPage) throws DataPagingInstructionException;
	
	/**
	 * <p>
	 * Creates a single object
	 * </p>
	 * 
	 * <p>
	 * Implementations should
	 * </p>
	 * 
	 * @param object
	 * @throws DataDuplicateException if the object already exists in the store
	 * @return
	 */
	public U create(U object) throws DataDuplicateException;
	public List<U> createAll(List<U> objects);
	public U update(V id, U object);
	public List<U> updateAll(List<U> objects);
	public U delete(V object);
	public List<U> deleteAll(List<U> objects);
	public List<U> deleteAllById(List<V> ids);
}
