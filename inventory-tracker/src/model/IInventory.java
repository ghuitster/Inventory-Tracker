
package model;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import model.exception.InvalidNameException;

public interface IInventory extends Observer, IObservable
{

	/**
	 * Determined if a storage unit is valid for addition
	 * @param storageUnit The new storage unit to check
	 * @pre (none)
	 * @post A boolean value is generated
	 * @return True if the storage unit may be added. Otherwise, false
	 */
	public abstract boolean ableToAddStorageUnit(IStorageUnit storageUnit);
	
	public abstract boolean ableToRemoveStorageUnit(IStorageUnit storageUnit);

	/**
	 * Adds a new Storage Unit to the system
	 * @pre A storage unit does not exist with the same name
	 * @post The passed storage unit has been inserted into the system
	 * @param storageUnit The new storage unit
	 * @throws InvalidNameException, NullPointerException
	 */
	public abstract void addStorageUnit(IStorageUnit storageUnit)
			throws InvalidNameException, NullPointerException;

	/**
	 * Gets all known products throughout all containers and their
	 * sub-containers
	 * @pre (none)
	 * @post A list has been created containing all known products. This list
	 *       may be empty
	 * @return A list containing references to all of the products
	 */
	public abstract SortedSet<IProduct> getAllProducts();

	/**
	 * Gets a list of all Storage Units in the system
	 * @pre (none)
	 * @post A list has been created containing all known Storage Units. This
	 *       list may be empty
	 * @return A list containing references to all top level ProductContainers
	 */
	public abstract SortedSet<IStorageUnit> getAllStorageUnits();

	/**
	 * Gets a map of Dates to what items expire on those dates
	 * @pre (none)
	 * @post A Map has been created with the aforementioned expiration dates.
	 *       This map may be empty.
	 * @return A copy of the map containing all known expiration dates mapped to
	 *         which items are expiring
	 */
	public abstract SortedMap<Date, Set<IItem>> getItemExpirations();

	/**
	 * Gets a map of Dates (each of which represents a specific month) mapped to
	 * what ProductGroups' three month supplies expire in the key's month
	 * @pre (none)
	 * @post A map has been created with the aforementioned properties. This map
	 *       may be empty.
	 * @return A copy of the map containing months correlated to what
	 *         ProductGroups' three month supplies expire on that month
	 */
	public abstract SortedMap<Date, Set<IProductGroup>>
			getNMonthSupplyGroupMap();

	/**
	 * Gets a map of Dates (each of which represents a specific month) mapped to
	 * what Products' three month supplies expire in the key's month
	 * @pre (none)
	 * @post A map has been created with the aforementioned properties. This map
	 *       may be empty.
	 * @return A copy of the map containing months correlated to what products'
	 *         three month supplies expire on that month
	 */
	public abstract SortedMap<Date, Set<IProduct>> getNMonthSupplyMap();

	/**
	 * Gets the persistence object for saving and loading data to this object
	 * @return An object capable of saving and loading this object's state
	 */
	public abstract IPersistence getPersistence();

	/**
	 * Gets a map of Dates to what items were removed on each date
	 * @pre (none)
	 * @post A map has been created with the aforementioned properties. This map
	 *       may be empty.
	 * @return A copy of the map containing all item removal dates mapped to the
	 *         items removed.
	 */
	public abstract SortedMap<Date, Set<IItem>> getRemovedItems();

	/**
	 * Clears all Storage Units from the system
	 * @pre (none)
	 * @post No storage units exist in the system
	 */
	public abstract void removeAllStorageUnits();

	/**
	 * Removes a storage unit from the system
	 * @pre The passed storage unit exists in the system
	 * @post The passed storage unit is no longer in the system
	 * @param storageUnit The Storage Unit to remove
	 */
	public abstract void removeStorageUnit(IStorageUnit storageUnit);

}
