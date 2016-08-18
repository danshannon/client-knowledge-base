package com.github.danshannon.ckb.data;

import java.util.List;

/**
 * General DAO specification for data access behind a service
 *
 * @author Dan Shannon
 *
 * @param <U>
 *            Class of object being accessed by the DAO
 * @param <V>
 *            Class of the object's identifier in the data store
 */
public interface DAO<U, V> {
	/**
	 * <p>
	 * Get an object from the data store by its id
	 * </p>
	 *
	 * @param id
	 *            The identifier of the object to be returned
	 * @throws DataNotFoundException
	 *             if there is no object with the given id
	 * @throws DataSecurityException
	 *             if the caller does not have access to the object with the given id
	 * @return The object with the given id
	 * @throws DataPrivacyException
	 *             if the caller does not have access to the object because of privacy requirements
	 */
	public U get(V id) throws DataNotFoundException, DataSecurityException, DataPrivacyException;

	/**
	 * <p>
	 * Get all the objects from the data store
	 * </p>
	 *
	 * <p>
	 * Objects which cannot be accessed because of privacy or security will not be included in the list
	 * </p>
	 *
	 * @return List of all objects in the data store
	 * @throws DataOperationNotSupportedException
	 *             if this operation is not supported
	 */
	public List<U> getAll() throws DataOperationNotSupportedException;

	/**
	 * <p>
	 * Get paginated list of objects from the data store. The method implementation should guarantee ordering of the object so that
	 * pagination is consistent.
	 * </p>
	 *
	 * <p>
	 * Objects which violate security and/or privacy constraints should not be included in the returned list
	 * </p>
	 *
	 * @param page
	 *            Page number to be returned (1-indexed, i.e. the first element in the list is on page 1, there is no page 0)
	 * @param pageSize
	 *            Number of objects per page
	 * @throws DataPagingInstructionException
	 *             if the request is for page <= 0 or pageSize <=0
	 * @return List of objects Returns objects in a defined order, from <code>((page - 1) * pageSize + 1)</code> to
	 *         <code>(page * pageSize)</code>
	 * @throws DataOperationNotSupportedException
	 *             if this operation is not supported
	 */
	public List<U> getPage(Integer page, Integer pageSize)
			throws DataPagingInstructionException, DataOperationNotSupportedException;

	/**
	 * <p>
	 * Creates a single object
	 * </p>
	 *
	 * @param object
	 *            The object to be persisted
	 * @throws DataDuplicateException
	 *             if the object already exists in the store
	 * @return Object created including the id (which may itself be created during persistence to the data store)
	 */
	public U create(U object) throws DataDuplicateException;

	/**
	 * <p>
	 * Creates all of the objects in the list, in the order that they appear in the list
	 * </p>
	 *
	 * @param objects
	 *            The objects to be created
	 * @return A list containing the objects that were created successfully
	 */
	public List<U> createAll(List<U> objects);

	/**
	 * <p>
	 * Update an existing object. The entire object as it exists in the data store will be replaced with the one provided.
	 * </p>
	 *
	 * @param object
	 *            The object to be updated.
	 * @return The object as updated.
	 * @throws DataNotFoundException
	 *             if the object does not exist in the data store
	 */
	TODO

	public U update(U object) throws DataNotFoundException;

	/**
	 * <p>
	 * Update a list of existing objects in the data store. The entire object as it exists in the data store will be replaced with
	 * the one provided.
	 * </p>
	 *
	 * <p>
	 * If an object does not exist in the data store or cannot be updated for security or privacy reasons, then it will not be
	 * included in the list returned.
	 * </p>
	 *
	 * @param objects
	 *            The list of objects to be updated
	 * @return A list containing the objects that were successfully updated
	 */
	TODO

	public List<U> updateAll(List<U> objects);

	/**
	 * <p>
	 * Delete the object in the data store with the given id.
	 * </p>
	 *
	 * @param id
	 *            The id of the object to be deleted
	 * @return The object that was deleted
	 * @throws DataNotFoundException
	 *             If the object does not exist in the data store
	 * @throws DataSecurityException
	 *             If the object cannot be deleted for security reasons
	 * @throws DataPrivacyException
	 *             If the object cannot be deleted for privacy reasons
	 */
	public U delete(V id) throws DataNotFoundException, DataSecurityException, DataPrivacyException;

	/**
	 * <p>
	 * Delete all the objects in the given list from the data store.
	 * </p>
	 *
	 * <p>
	 * Objects which could not be successfully deleted for privacy or security reasons will not be returned in the return list.
	 * </p>
	 *
	 * @param objects
	 *            The list of objects to be deleted
	 * @return The list of objects that were successfully deleted
	 */
	public List<U> deleteAll(List<U> objects);

	/**
	 * <p>
	 * Delete all the objects with ids in the given list from the data store.
	 * </p>
	 *
	 * <p>
	 * Objects which could not be successfully deleted for privacy or security reasons will not be returned in the return list.
	 * </p>
	 *
	 * @param ids
	 *            The list of object ids to be deleted
	 * @return The list of objects that were successfully deleted
	 */
	TODO

	public List<U> deleteAllById(List<V> ids);

	/**
	 * <p>
	 * Delete all the objects from the data store.
	 * </p>
	 *
	 * @throws DataOperationNotSupportedException
	 *             if the operation is not supported
	 */
	public void deleteAll() throws DataOperationNotSupportedException;

	/**
	 * <p>
	 * Patch the object by updating only the non-null fields in the given object (because the caller may only have limited knowledge
	 * of the object and therefore can't entirely replace the object).
	 * </p>
	 *
	 * @param object
	 *            The object to be patched, must include the id which is used to find the object in the store
	 * @return The object as patched
	 * @throws DataNotFoundException
	 *             If the object to be patched does not exist in the data store
	 * @throws DataSecurityException
	 *             If the object cannot be accessed due to security restrictions
	 * @throws DataPrivacyException
	 *             If the object cannot be accessed due to privacy restrictions
	 * @throws DataOperationNotSupportedException
	 *             If this operation is not supported
	 */
	TODO

	public U patch(U object)
			throws DataNotFoundException, DataSecurityException, DataPrivacyException, DataOperationNotSupportedException;

	TODO

	public List<U> patchAll(List<U> objects);
}
