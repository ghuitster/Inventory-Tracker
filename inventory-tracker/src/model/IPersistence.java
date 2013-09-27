
package model;

/**
 * Interface which defines methods for persisting data
 * @author Brian
 */
public interface IPersistence
{
	/**
	 * When overridden in a child class, determines whether the passed store
	 * name is valid and exists as data which can be loaded
	 * @param storeName The name of the store to verify
	 * @pre (none)
	 * @post (none)
	 * @return True if the name is valid and the store exists. False if either
	 *         condition is not true.
	 */
	boolean DataStoreExists(String storeName);

	/**
	 * When overridden in a child class, determines whether the passed store
	 * name is valid for the current IPersistence implementation
	 * @param storeName The name to check for validity
	 * @return True if the name is a valid store name. Otherwise, false
	 */
	boolean DataStoreNameIsValid(String storeName);

	/**
	 * When overridden in a child class, loads all data from specified store to
	 * the passed Inventory
	 * @param inventory The Inventory to load the data to
	 * @param storeName The name of the data store to load data from (i.e.:
	 *            filename or database name)
	 * @pre inventory is not null. storeName refers to a valid data store.
	 * @post inventory's original contents have been cleared and replaced with
	 *       the data from the specified data store
	 */
	void LoadData(Inventory inventory, String storeName);

	/**
	 * When overridden in a child class, saves all data from the Inventory with
	 * the specified name
	 * @param inventory The Inventory to save
	 * @param storeName The name of the store (i.e.: filename or database name
	 *            to store the data in)
	 * @pre storeName is a valid string for the system we are saving to
	 * @post The passed Inventory has been saved under the passed name
	 */
	void SaveData(Inventory inventory, String storeName);
}
