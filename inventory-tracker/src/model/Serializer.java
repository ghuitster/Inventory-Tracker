
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.exception.SerializerException;

/**
 * Saves and loads Inventory data to a file
 * @author Brian
 * 
 */
public class Serializer implements IPersistence, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5194089082016935560L;
	private String filePath;

	/**
	 * Initializes an instance of Serializer
	 * @pre (none)
	 * @post this.filePath is set to the passed path
	 * @param filePath The path to the file used to load and store data
	 */
	public Serializer(String filePath)
	{
		this.filePath = filePath;
	}

	private void addObserver(IProductContainer container)
	{
		container.addObserver(Inventory.getInstance());
		for(IProductGroup group: container.getAllProductGroups())
			addObserver(group);

		for(IItem item: container.getAllItems())
			item.addObserver(Inventory.getInstance());
		for(IProduct product: container.getAllProducts())
			product.addObserver(Inventory.getInstance());
	}

	/**
	 * Determined whether the data file exists and can be loaded
	 * @pre (none)
	 * @post (none)
	 * @return True if data can be loaded. Otherwise, false.
	 */
	@Override
	public boolean canLoadData()
	{
		File file = new File(this.filePath);
		return file.exists() && file.canRead();
	}

	/**
	 * Loads all data from specified store to the passed Inventory
	 * @pre inventory is not null. storeName refers to a valid data store.
	 * @post inventory's original contents have been cleared and replaced with
	 *       the data from the specified file
	 * @throws SerializerException
	 */
	@Override
	public void loadData() throws SerializerException
	{
		FileInputStream stream = null;
		ObjectInputStream objIn = null;
		try
		{
			stream = new FileInputStream(this.filePath);
			objIn = new ObjectInputStream(stream);
			Inventory inventory = (Inventory) objIn.readObject();
			Inventory.setInstance(inventory);
			for(IStorageUnit unit: Inventory.getInstance().getAllStorageUnits())
			{
				addObserver(unit);
			}

		}
		catch(Exception e)
		{
			throw new SerializerException(e.getMessage());
		}
		finally
		{
			try
			{
				if(objIn != null)
					objIn.close();
				else if(stream != null)
					stream.close();
			}
			catch(IOException e)
			{}
		}
	}

	/**
	 * Saves all data from the Inventory to a file with the specified name
	 * @param storeName The filename of the store (based on the current working
	 *            directory)
	 * @pre storeName is a valid string for the system we are saving to
	 * @post The passed Inventory has been saved under the passed name
	 */
	@Override
	public void saveData() throws SerializerException
	{
		FileOutputStream stream = null;
		ObjectOutputStream objOut = null;
		try
		{
			stream = new FileOutputStream(this.filePath);
			objOut = new ObjectOutputStream(stream);
			objOut.writeObject(Inventory.getInstance());
		}
		catch(IOException e)
		{
			throw new SerializerException(e.getMessage());
		}
		finally
		{
			try
			{
				if(objOut != null)
					objOut.close();
				else if(stream != null)
					stream.close();
			}
			catch(IOException e)
			{}
		}

	}

}
