
package model;

import model.exception.*;

public class Database implements IPersistence
{
	/**
	 * Initializes an instance of Database
	 * @param databaseName The name of the database to store/load information from
	 */
	public Database(String databaseName)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * Determines whether a database exists which data can be loaded from
	 * @return True if the database exists and is valid. Otherwise, false
	 */
	@Override
	public boolean canLoadData()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Loads all data from specified store to the passed Inventory
	 * @pre inventory is not null. storeName refers to a valid database
	 * @post inventory's original contents have been cleared and replaced with
	 *       the data from the specified database
	 * @throws SerializerException
	 */
	@Override
	public void loadData() throws SerializerException
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Saves all data from the Inventory with the specified name to a database
	 * @pre storeName is a valid string for a database name
	 * @post The passed Inventory has been saved under the passed name
	 * @throws SerializerException
	 */
	@Override
	public void saveData() throws SerializerException
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
