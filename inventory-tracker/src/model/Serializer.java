
package model;

/**
 * Saves and loads Inventory data to a file
 * @author Brian
 * 
 */
public class Serializer implements IPersistence
{

	/**
	 * Determines whether the passed store name is a valid filename and exists
	 * as a file in the current working directory
	 * @param storeName The name of the store to verify
	 * @pre (none)
	 * @post (none)
	 * @return True if the name is valid and the store exists. False if either
	 *         condition is not true.
	 */
	@Override
	public boolean DataStoreExists(String storeName)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Determines whether the passed store name is a valid file name
	 * @param storeName The name to check for validity
	 * @return True if the name is a valid store name. Otherwise, false
	 */
	@Override
	public boolean DataStoreNameIsValid(String storeName)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Loads all data from specified store to the passed Inventory
	 * @param inventory The Inventory to load the data to
	 * @param storeName The filename of the data store to load data from (based
	 *            on the current working directory)
	 * @pre inventory is not null. storeName refers to a valid data store.
	 * @post inventory's original contents have been cleared and replaced with
	 *       the data from the specified file
	 */
	@Override
	public void LoadData(Inventory inventory, String storeName)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Saves all data from the Inventory to a file with the specified name
	 * @param inventory The Inventory to save
	 * @param storeName The filename of the store (based on the current working
	 *            directory)
	 * @pre storeName is a valid string for the system we are saving to
	 * @post The passed Inventory has been saved under the passed name
	 */
	@Override
	public void SaveData(Inventory inventory, String storeName)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
