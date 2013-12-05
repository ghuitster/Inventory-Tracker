
package model;

import java.util.Observable;
import java.util.Observer;

import model.exception.SerializerException;

public class Database implements IPersistence, Observer
{
	private DatabaseAccess dbAccess;
	private String databaseName;
	
	
	/**
	 * Initializes an instance of Database
	 * @param databaseName The name of the database to store/load information
	 *            from
	 */
	public Database(String databaseName)
	{
		dbAccess = new DatabaseAccess(databaseName);
		this.databaseName = databaseName;
		Inventory.getInstance().addObserver(this);
	}

	/**
	 * Determines whether a database exists which data can be loaded from
	 * @return True if the database exists and is valid. Otherwise, false
	 */
	@Override
	public boolean canLoadData()
	{
		return true;
	}

	/**
	 * Loads all data from specified store to the passed Inventory
	 * @pre inventory is not null. storeName refers to a valid database
	 * @post inventory's original contents have been cleared and replaced with
	 *       the data from the specified database
	 * @throws SerializerException
	 */
	@Override
	public void loadData()
	{
		dbAccess.loadInventory();
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

	@Override
	public void update(Observable o, Object arg)
	{
		ObservableArgs obsArg = (ObservableArgs) arg;
		if(obsArg.getChangedObj() instanceof IProductContainer)
		{
			IProductContainer container = (IProductContainer)obsArg.getChangedObj();
			switch(obsArg.getUpdateType())
			{
				case ADDED:
					dbAccess.addProductContainer(container);
					break;
				case REMOVED:
					dbAccess.removeProductContainer(container);
					break;
				case UPDATED:
					dbAccess.updateProductContainer(container);
					break;
				
			}
		}
		else if(obsArg.getChangedObj() instanceof IProduct)
		{
			IProduct product = (IProduct)obsArg.getChangedObj();
			switch(obsArg.getUpdateType())
			{
				case ADDED:
					dbAccess.addProduct(product);
					break;
				case REMOVED:
					dbAccess.removeProduct(product);
					break;
				case UPDATED:
					dbAccess.updateProduct(product);
					break;
				
			}
		}
		else if(obsArg.getChangedObj() instanceof IItem)
		{
			IItem item = (IItem)obsArg.getChangedObj();
			switch(obsArg.getUpdateType())
			{
				case ADDED:
					dbAccess.addItem(item);
					break;
				case REMOVED:
					dbAccess.removeItem(item);
					break;
				case UPDATED:
					dbAccess.updateItem(item);
					break;
				
			}
		}
	}

}
