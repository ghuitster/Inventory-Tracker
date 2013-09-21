package model;

import java.util.List;

/**
 * Singleton class to manage the entire Inventory system
 * @author Brian
 *
 */
public class Inventory 
{
	/**
	 * A list of all the storage units
	 */
	private List<ProductContainer> storageUnits;

	/**
	 * Creates a new instance of the Inventory class. Used only by singleton getter.
	 */
	private Inventory() 
	{
		
	}
	
	/**
	 * Static reference to the one and only Inventory instance
	 */
	private static Inventory instance;
	/**
	 * Gets the static Inentory instance. Creates it if it does not exist.
	 * @return
	 */
	public static Inventory getInstance() 
	{
		if(instance == null)
			instance = new Inventory();
		return instance;
	}
	
	/**
	 * Gets all known products throughout all containers and their sub-containers
	 * @return A list containing referenes to all of the products
	 */
	public List<Product> getAllProducts()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * Gets a list of all Storage Units in the system
	 * @return A list containing references to all top level ProductContainers
	 */
	public List<ProductContainer> getAllStorageUnits()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * Adds a new Storage Unit to the system
	 * @param storageUnit The new storage unit
	 */
	public void addStorageUnit(ProductContainer storageUnit)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * Removes a storage unit from the system
	 * @param storageUnit The Storage Unit to remove
	 */
	public void removeStorageUnit(ProductContainer storageUnit)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * Clears all Storage Units from the system
	 */
	public void removeAllStorageUnits()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	
}
