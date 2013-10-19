
package model;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import model.exception.InvalidNameException;

import common.util.DateUtils;

/**
 * Singleton class to manage the entire Inventory system
 * @author Brian
 * 
 */
public class Inventory extends Observable implements IInventory, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6708314065210008512L;

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

	protected static void setInstance(Inventory inventory)
	{
		instance = inventory;
	}

	/**
	 * A list of all the storage units
	 */
	private final Set<IStorageUnit> storageUnits;

	/**
	 * Persistence object for saving and loading data
	 */
	private final IPersistence persistence;

	/**
	 * Mapping of all Item expirations, for easy retrieval
	 */
	private final SortedMap<Date, Set<IItem>> itemExpirations;

	/**
	 * Mapping of all removed Items, for easy retrieval
	 */
	private final SortedMap<Date, Set<IItem>> removedItems;
	/**
	 * Mapping for the nMonthSupply report. The Date key represents a month. The
	 * corresponding products are those which will last up to the key's month
	 */
	private final SortedMap<Date, Set<IProduct>> nMonthSupplyMap;

	/**
	 * Date Mapping for the nMonthSupply report. The Date key represents a
	 * month. The corresponding ProductGroups are those which will last up to
	 * the key's month
	 */
	private final SortedMap<Date, Set<IProductGroup>> nMonthSupplyGroupMap;

	/**
	 * Static reference to the one and only Inventory instance
	 */
	private static Inventory instance;

	/**
	 * ItemBarcode Number mapping for all items.
	 */
	private final Map<String, IItem> barcodeItems;

	private long lastGeneratedBarCode;

	/**
	 * Creates a new instance of the Inventory class. Used only by singleton
	 * getter.
	 * @pre (none)
	 * @post This instance of Inventory will be ready for use
	 */
	private Inventory()
	{
		this.storageUnits = new HashSet<IStorageUnit>();
		this.persistence = new Serializer("./data.inventory");
		this.itemExpirations = new TreeMap<Date, Set<IItem>>();
		this.removedItems = new TreeMap<Date, Set<IItem>>();
		this.nMonthSupplyMap = new TreeMap<Date, Set<IProduct>>();
		this.nMonthSupplyGroupMap = new TreeMap<Date, Set<IProductGroup>>();
		this.barcodeItems = new HashMap<String, IItem>();
		this.lastGeneratedBarCode = 400000000000l;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#ableToAddStorageUnit(model.StorageUnit)
	 */
	@Override
	public boolean ableToAddStorageUnit(IStorageUnit storageUnit)
	{
		if(storageUnit == null)
			return false;

		return ableToAddStorageUnitNamed(storageUnit.getName());
	}

	@Override
	public boolean ableToAddStorageUnitNamed(String name)
	{
		if(name == null || name.isEmpty())
			return false;
		
		for(IStorageUnit unit: this.storageUnits)
		{
			if(unit.getName().equals(name))
				return false;
		}

		return true;
	}

	@Override
	public boolean ableToRemoveStorageUnit(IStorageUnit storageUnit)
	{
		HashSet<IProduct> products = new HashSet<IProduct>();
		recurseProductContainer(storageUnit, products);
		return products.size() == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#addStorageUnit(model.StorageUnit)
	 */
	@Override
	public void addStorageUnit(IStorageUnit storageUnit)
			throws InvalidNameException, NullPointerException
	{
		if(storageUnit == null)
			throw new NullPointerException();
		else if(this.storageUnitNameInUse(storageUnit.getName()))
			throw new InvalidNameException();

		this.storageUnits.add(storageUnit);

		this.setChanged();
		this.notifyObservers(new ObservableArgs(storageUnit, UpdateType.ADDED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getAllProducts()
	 */
	@Override
	public SortedSet<IProduct> getAllProducts()
	{
		TreeSet<IProduct> products = new TreeSet<IProduct>();
		for(IStorageUnit unit: this.storageUnits)
		{
			recurseProductContainer(unit, products);
		}
		return products;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getAllStorageUnits()
	 */
	@Override
	public SortedSet<IStorageUnit> getAllStorageUnits()
	{
		return new TreeSet<IStorageUnit>(this.storageUnits);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getItemExpirations()
	 */
	@Override
	public SortedMap<Date, Set<IItem>> getItemExpirations()
	{
		return new TreeMap<Date, Set<IItem>>(this.itemExpirations);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getNMonthSupplyGroupMap()
	 */
	@Override
	public SortedMap<Date, Set<IProductGroup>> getNMonthSupplyGroupMap()
	{
		return new TreeMap<Date, Set<IProductGroup>>(this.nMonthSupplyGroupMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getNMonthSupplyMap()
	 */
	@Override
	public SortedMap<Date, Set<IProduct>> getNMonthSupplyMap()
	{
		return new TreeMap<Date, Set<IProduct>>(this.nMonthSupplyMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getPersistence()
	 */
	@Override
	public IPersistence getPersistence()
	{
		return this.persistence;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getRemovedItems()
	 */
	@Override
	public SortedMap<Date, Set<IItem>> getRemovedItems()
	{
		return new TreeMap<Date, Set<IItem>>(this.removedItems);
	}

	/**
	 * Returns a unique bar code
	 * @return A bar code which is unique
	 */
	public long getUniqueBarCode()
	{
		return lastGeneratedBarCode++;
	}

	/**
	 * Adds all products from a container to the working set and recurses the
	 * sub-containers
	 * @param container The container to add the products from and recurse the
	 *            children of
	 * @pre workingSet is not null
	 * @post workingSet contains all products from container and its children
	 * @param workingSet The current set being built
	 */
	private void recurseProductContainer(IProductContainer container,
			Set<IProduct> workingSet)
	{
		workingSet.addAll(container.getAllProducts());
		for(IProductContainer subContainer: container.getAllProductGroups())
		{
			recurseProductContainer(subContainer, workingSet);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#removeAllStorageUnits()
	 */
	@Override
	public void removeAllStorageUnits()
	{
		for(IStorageUnit unit: storageUnits)
		{
			this.setChanged();
			this.notifyObservers(new ObservableArgs(unit, UpdateType.REMOVED));
		}

		this.storageUnits.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#removeStorageUnit(model.StorageUnit)
	 */
	@Override
	public void removeStorageUnit(IStorageUnit storageUnit)
	{
		if(!this.storageUnits.contains(storageUnit))
			throw new InvalidParameterException(
					"Passed StorageUnit was not found");

		this.storageUnits.remove(storageUnit);
		this.setChanged();
		this.notifyObservers(new ObservableArgs(storageUnit, UpdateType.REMOVED));
	}

	/**
	 * Reports that an item has been removed from the system
	 * @pre Item is not null
	 * @post Item's exit time has been set to the current time, its container is
	 *       null, and it has been added to the map of removed items
	 * @param item The item that has been removed
	 */
	private void reportRemovedItem(IItem item)
	{
		Date current = DateUtils.currentDate();
		item.setExitTime(current);
		item.setContainer(null);

		Date month =
				new Date(current.getYear(), current.getMonth(), 0);

		Set<IItem> itemsForMonth;
		if(removedItems.containsKey(month))
			itemsForMonth = removedItems.get(month);
		else
			itemsForMonth = new HashSet<IItem>();
		itemsForMonth.add(item);

		this.removedItems.put(month, itemsForMonth);
	}

	/**
	 * Checks if a storage unit name is taken
	 * @param unitName The name to check
	 * @pre (none)
	 * @post A boolean value is generated
	 * @return True if the name is taken. Otherwise, false
	 */
	private boolean storageUnitNameInUse(String unitName)
	{
		for(IStorageUnit unit: storageUnits)
		{
			if(unit.getName().equals(unitName))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		ObservableArgs obsArg = (ObservableArgs) arg;
		if(obsArg.getChangedObj() instanceof IItem)
		{
			IItem item = ((IItem) obsArg.getChangedObj());
			if(obsArg.getUpdateType() == UpdateType.REMOVED)
			{
				this.reportRemovedItem(item);
				this.barcodeItems.remove(item.getBarcode().toString());
			}
			else if(obsArg.getUpdateType() == UpdateType.ADDED)
			{
				this.barcodeItems.put(item.getBarcode().toString(), item);
			}
		}

		this.setChanged();
		this.notifyObservers(obsArg);
	}

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
	@Override
	public IItem getItem(String barcodeNumber)
	{
		return this.barcodeItems.get(barcodeNumber);
	}

}
