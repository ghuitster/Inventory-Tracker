
package model;

import model.exception.*;

/**
 * Interface which defines methods for persisting data
 * @author Brian
 */
public interface IPersistence
{
	/**
	 * When overridden in a child class, determines whether data exists which can be loaded
	 * @pre (none)
	 * @post (none)
	 * @return True if data can be loaded. Otherwise, false.
	 */
	boolean canLoadData();

	/**
	 * When overridden in a child class, loads all data from specified store to
	 * the passed Inventory
	 * @pre inventory is not null. storeName refers to a valid data store.
	 * @post inventory's original contents have been cleared and replaced with
	 *       the data from the specified data store
	 * @throws SerializerException
	 */
	void loadData() throws SerializerException;

	/**
	 * When overridden in a child class, saves all data from the Inventory with
	 * the specified name
	 * @pre storeName is a valid string for the system we are saving to
	 * @post The passed Inventory has been saved under the passed name
	 * @throws SerializerException
	 */
	void saveData() throws SerializerException;
}
