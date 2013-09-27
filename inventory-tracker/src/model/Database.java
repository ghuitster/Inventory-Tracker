package model;

public class Database implements IPersistence
{

	/**
	 * Saves all data from the Inventory with the specified name to a database
	 * @param inventory The Inventory to save
	 * @param storeName The name of the database to use
	 * @pre storeName is a valid string for a database name
	 * @post The passed Inventory has been saved under the passed name
	 */
	@Override
	public void SaveData(Inventory inventory, String storeName)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * Loads all data from specified store to the passed Inventory
	 * @param inventory The Inventory to load the data to
	 * @param storeName The name of the database to load data from
	 * @pre inventory is not null. storeName refers to a valid database
	 * @post inventory's original contents have been cleared and replaced with the data from the specified database
	 */
	@Override
	public void LoadData(Inventory inventory, String storeName)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * Determines whether the passed database name is valid and exists as in the database system
	 * @param storeName The name of the databse to verify
	 * @pre (none)
	 * @post (none)
	 * @return True if the name is valid and the database exists. False if either condition is not true.
	 */
	@Override
	public boolean DataStoreExists(String storeName)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Determines whether the passed store name is a valid database name
	 * @param storeName The name to check for validity
	 * @return True if the name is a valid database name. Otherwise, false
	 */
	@Override
	public boolean DataStoreNameIsValid(String storeName)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
