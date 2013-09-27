
package model;

import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

/**
 * Singleton class to manage the entire Inventory system
 * @author Brian
 * 
 */
public class Inventory
{
	/**
	 * Gets the static Inventory instance. Creates it if it does not exist.
	 * @pre (none)
	 * @post The single static instance of Inventory is not null
	 * @return The one and only instance of Inventory
	 */
	public static Inventory getInstance()
	{
		if(instance == null)
			instance = new Inventory();
		return instance;
	}

	/**
	 * A list of all the storage units
	 */
	private Set<StorageUnit> storageUnits;

	/**
	 * Persistence object for saving and loading data
	 */
	private IPersistence persistence;

	/**
	 * Mapping of all Item expirations, for easy retrieval
	 */
	private SortedMap<Date, Set<Item>> itemExpirations;

	/**
	 * Mapping of all removed Items, for easy retrieval
	 */
	private SortedMap<Date, Set<Item>> removedItems;
	/**
	 * Mapping for the nMonthSupply report. The Date key represents a month. The
	 * corresponding products are those which will last up to the key's month
	 */
	private SortedMap<Integer, Set<Product>> nMonthSupplyMap;

	/**
	 * Date Mapping for the nMonthSupply report. The Date key represents a
	 * month. The corresponding ProductGroups are those which will last up to
	 * the key's month
	 */
	private SortedMap<Date, Set<ProductGroup>> nMonthSupplyGroupMap;

	/**
	 * Static reference to the one and only Inventory instance
	 */
	private static Inventory instance;

	/**
	 * Creates a new instance of the Inventory class. Used only by singleton
	 * getter.
	 * @pre (none)
	 * @post This instance of Inventory will be ready for use
	 */
	private Inventory()
	{

	}

	/**
	 * Adds a new Storage Unit to the system
	 * @pre A storage unit does not exist with the same name
	 * @post The passed storage unit has been inserted into the system
	 * @param storageUnit The new storage unit
	 */
	public void addStorageUnit(StorageUnit storageUnit)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Gets all known products throughout all containers and their
	 * sub-containers
	 * @pre (none)
	 * @post A list has been created containing all known products. This list
	 *       may be empty
	 * @return A list containing references to all of the products
	 */
	public Set<Product> getAllProducts()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Gets a list of all Storage Units in the system
	 * @pre (none)
	 * @post A list has been created containing all known Storage Units. This
	 *       list may be empty
	 * @return A list containing references to all top level ProductContainers
	 */
	public Set<StorageUnit> getAllStorageUnits()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Gets a map of Dates to what items expire on those dates
	 * @pre (none)
	 * @post A Map has been created with the aforementioned expiration dates.
	 *       This map may be empty.
	 * @return A copy of the map containing all known expiration dates mapped to
	 *         which items are expiring
	 */
	public SortedMap<Date, Set<Item>> getItemExpirations()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Gets a map of Dates (each of which represents a specific month) mapped to
	 * what ProductGroups' three month supplies expire in the key's month
	 * @pre (none)
	 * @post A map has been created with the aforementioned properties. This map
	 *       may be empty.
	 * @return A copy of the map containing months correlated to what
	 *         ProductGroups' three month supplies expire on that month
	 */
	public SortedMap<Date, Set<ProductGroup>> getNMonthSupplyGroupMap()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Gets a map of Dates (each of which represents a specific month) mapped to
	 * what Products' three month supplies expire in the key's month
	 * @pre (none)
	 * @post A map has been created with the aforementioned properties. This map
	 *       may be empty.
	 * @return A copy of the map containing months correlated to what products'
	 *         three month supplies expire on that month
	 */
	public SortedMap<Date, Set<Product>> getNMonthSupplyMap()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Gets a map of Dates to what items were removed on each date
	 * @pre (none)
	 * @post A map has been created with the aforementioned properties. This map
	 *       may be empty.
	 * @return A copy of the map containing all item removal dates mapped to the
	 *         items removed.
	 */
	public SortedMap<Date, Set<Item>> getRemovedItems()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Clears all Storage Units from the system
	 * @pre (none)
	 * @post No storage units exist in the system
	 */
	public void removeAllStorageUnits()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Removes a storage unit from the system
	 * @pre The passed storage unit exists in the system
	 * @post The passed storage unit is no longer in the system
	 * @param storageUnit The Storage Unit to remove
	 */
	public void removeStorageUnit(StorageUnit storageUnit)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
