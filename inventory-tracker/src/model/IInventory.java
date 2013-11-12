
package model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import model.exception.InvalidNameException;
import model.visitor.IVisitor;

public interface IInventory extends Observer, IObservable
{

	/**
	 * Determined if a storage unit is valid for addition
	 * @param storageUnit The new storage unit to check
	 * @pre (none)
	 * @post A boolean value is generated
	 * @return True if the storage unit may be added. Otherwise, false
	 */
	boolean ableToAddStorageUnit(IStorageUnit storageUnit);

	boolean ableToAddStorageUnitNamed(String name);

	/**
	 * Returns whether attempting to remove a product is valid
	 * @pre none
	 * @post none
	 * @param product The product to check
	 * @return True if the product is orphaned. Otherwise, false
	 */
	boolean ableToRemoveProduct(IProduct product);

	boolean ableToRemoveStorageUnit(IStorageUnit storageUnit);

	/**
	 * Adds a new Storage Unit to the system
	 * @pre A storage unit does not exist with the same name
	 * @post The passed storage unit has been inserted into the system
	 * @param storageUnit The new storage unit
	 * @throws InvalidNameException, NullPointerException
	 */
	void addStorageUnit(IStorageUnit storageUnit) throws InvalidNameException,
			NullPointerException;

	/**
	 * Gets all items of a certain product type
	 * @pre none
	 * @post none
	 * @param product The product to filter by. If null, gets all items.
	 * @return All items of type product
	 */
	SortedSet<IItem> getAllItems(IProduct product);

	/**
	 * Gets a list of all Storage Units in the system
	 * @pre (none)
	 * @post A list has been created containing all known Storage Units. This
	 *       list may be empty
	 * @return A list containing references to all top level ProductContainers
	 */

	/**
	 * Gets all known products throughout all containers and their
	 * sub-containers
	 * @pre (none)
	 * @post A list has been created containing all known products. This list
	 *       may be empty
	 * @return A list containing references to all of the products
	 */
	SortedSet<IProduct> getAllProducts();

	SortedSet<IStorageUnit> getAllStorageUnits();

	/**
	 * Gets a list of items in the system which have expired
	 * @pre none
	 * @post none
	 * @return A list of items
	 */
	List<IItem> getExpiredItems();

	/**
	 * Gets a list of ProductGroups which have mismatched 3-month supplies
	 * @pre (none)
	 * @post (none)
	 * @return A map. The keys are the inconsistent product groups, the
	 *         corresponding products are the offending products
	 */
	Map<IProductGroup, List<IProduct>> getInconsistencies();

	/**
	 * Returns an item from the system given a barcode, or null if item does not
	 * exist.
	 * @pre The passed ItemBarcode is valid
	 * @post The item referenced by the ItemBarcode is returned, or null if the
	 *       item does not exist
	 * @param barcodeNumber the String representing the ItemBarcode referencing
	 *            the item.
	 * @return result the item referenced by the passed in ItemBarcode number,
	 *         or null if the item does not exist
	 */
	IItem getItem(String barcodeNumber);

	/**
	 * Gets a list of products and productGroups which will run out in the passed time span
	 * @param months The number of months ahead to check
	 * @pre none
	 * @post none
	 * @return An object containing lists of the products and product groups with low supplies
	 */
	ProductSupplyReport getLowSupplies(int months);

	/**
	 * Gets the persistence object for saving and loading data to this object
	 * @return An object capable of saving and loading this object's state
	 */
	IPersistence getPersistence();

	/**
	 * Gets statistics for products since for a period of time
	 * @pre none
	 * @post none
	 * @param since The cutoff date
	 * @return A list describing statistics for all products changed in the
	 *         timespan
	 */
	List<ProductStats> getProductStats(Date since);

	/**
	 * Gets all items which were removed since the passed date
	 * @param since The cutoff date
	 * @pre none
	 * @post none
	 * @return A list which describes which items were removed and how many
	 */
	SortedSet<RemovedItems> getRemovedItems(Date since);

	/**
	 * The last time the Removed Items Report was run
	 */
	Date getLastRemovedItemReportTime();
	
	/**
	 * Clears all Storage Units from the system
	 * @pre (none)
	 * @post No storage units exist in the system
	 */
	void removeAllStorageUnits();

	/**
	 * Removes a product from the system
	 * @pre ableToRemoveProduct(product)
	 * @post product is removed from the system
	 * @param product The product to remove
	 */
	void removeProduct(IProduct product);

	/**
	 * Removes a storage unit from the system
	 * @pre The passed storage unit exists in the system
	 * @post The passed storage unit is no longer in the system
	 * @param storageUnit The Storage Unit to remove
	 */
	void removeStorageUnit(IStorageUnit storageUnit);

	/**
	 * Traverses the passed visitor across the inventory Calls the appropriate
	 * visitor methods once for each Product Container, Product, and Item
	 * @pre none
	 * @post none
	 * @param visitor The visitor to use
	 */
	void traverse(IVisitor visitor);

}
