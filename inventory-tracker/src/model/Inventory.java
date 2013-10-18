
package model;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import model.exception.InvalidNameException;

/**
 * Singleton class to manage the entire Inventory system
 * @author Brian
 * 
 */
public class Inventory extends BaseInventory implements Serializable
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
	private Set<StorageUnit> storageUnits;

	/**
	 * Persistence object for saving and loading data
	 */
	private IPersistence persistence;

	/**
	 * Mapping of all Item expirations, for easy retrieval
	 */
	private SortedMap<Date, Set<BaseItem>> itemExpirations;

	/**
	 * Mapping of all removed Items, for easy retrieval
	 */
	private SortedMap<Date, Set<BaseItem>> removedItems;
	/**
	 * Mapping for the nMonthSupply report. The Date key represents a month. The
	 * corresponding products are those which will last up to the key's month
	 */
	private SortedMap<Date, Set<BaseProduct>> nMonthSupplyMap;

	/**
	 * Date Mapping for the nMonthSupply report. The Date key represents a
	 * month. The corresponding ProductGroups are those which will last up to
	 * the key's month
	 */
	private SortedMap<Date, Set<IProductGroup>> nMonthSupplyGroupMap;

	/**
	 * Static reference to the one and only Inventory instance
	 */
	private static Inventory instance;

	private long lastGeneratedBarCode;

	/**
	 * Creates a new instance of the Inventory class. Used only by singleton
	 * getter.
	 * @pre (none)
	 * @post This instance of Inventory will be ready for use
	 */
	private Inventory()
	{
		this.storageUnits = new HashSet<StorageUnit>();
		this.persistence = new Serializer("./data.inventory");
		this.itemExpirations = new TreeMap<Date, Set<BaseItem>>();
		this.removedItems = new TreeMap<Date, Set<BaseItem>>();
		this.nMonthSupplyMap = new TreeMap<Date, Set<BaseProduct>>();
		this.nMonthSupplyGroupMap = new TreeMap<Date, Set<IProductGroup>>();
		this.lastGeneratedBarCode = 400000000000l;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#ableToAddStorageUnit(model.StorageUnit)
	 */
	public boolean ableToAddStorageUnit(StorageUnit storageUnit)
	{
		if(storageUnit == null)
			return false;

		if(storageUnitNameInUse(storageUnit.getName()))
			return false;

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#addStorageUnit(model.StorageUnit)
	 */
	public void addStorageUnit(StorageUnit storageUnit)
			throws InvalidNameException, NullPointerException
	{
		if(storageUnit == null)
			throw new NullPointerException();
		else if(this.storageUnitNameInUse(storageUnit.getName()))
			throw new InvalidNameException();

		this.storageUnits.add(storageUnit);
		
		this.notifyObservers(new ObservableArgs(storageUnit, UpdateType.ADDED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getAllProducts()
	 */
	public Set<BaseProduct> getAllProducts()
	{
		HashSet<BaseProduct> products = new HashSet<BaseProduct>();
		for(StorageUnit unit: this.storageUnits)
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
	public Set<StorageUnit> getAllStorageUnits()
	{
		return new HashSet<StorageUnit>(this.storageUnits);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getItemExpirations()
	 */
	public SortedMap<Date, Set<BaseItem>> getItemExpirations()
	{
		return new TreeMap<Date, Set<BaseItem>>(this.itemExpirations);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getNMonthSupplyGroupMap()
	 */
	public SortedMap<Date, Set<IProductGroup>> getNMonthSupplyGroupMap()
	{
		return new TreeMap<Date, Set<IProductGroup>>(this.nMonthSupplyGroupMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getNMonthSupplyMap()
	 */
	public SortedMap<Date, Set<BaseProduct>> getNMonthSupplyMap()
	{
		return new TreeMap<Date, Set<BaseProduct>>(this.nMonthSupplyMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getPersistence()
	 */
	public IPersistence getPersistence()
	{
		return this.persistence;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#getRemovedItems()
	 */
	public SortedMap<Date, Set<BaseItem>> getRemovedItems()
	{
		return new TreeMap<Date, Set<BaseItem>>(this.removedItems);
	}

	/**
	 * Returns a unique bar code
	 * @return A bar code which is unique
	 */
	protected long getUniqueBarCode()
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
	private void recurseProductContainer(BaseProductContainer container,
			Set<BaseProduct> workingSet)
	{
		workingSet.addAll(container.getAllProducts());
		for(BaseProductContainer subContainer: container.getAllProductGroups())
		{
			recurseProductContainer(subContainer, workingSet);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#removeAllStorageUnits()
	 */
	public void removeAllStorageUnits()
	{
		for(StorageUnit unit : storageUnits)
			this.notifyObservers(new ObservableArgs(unit, UpdateType.REMOVED));
		
		this.storageUnits.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.BaseInventory#removeStorageUnit(model.StorageUnit)
	 */
	public void removeStorageUnit(IStorageUnit storageUnit)
	{
		if(!this.storageUnits.contains(storageUnit))
			throw new InvalidParameterException(
					"Passed StorageUnit was not found");

		this.storageUnits.remove(storageUnit);
		this.notifyObservers(new ObservableArgs(storageUnit, UpdateType.REMOVED));
	}

	/**
	 * Reports that an item has been removed from the system
	 * @pre Item is not null
	 * @post Item's exit time has been set to the current time, its container is
	 *       null, and it has been added to the map of removed items
	 * @param item The item that has been removed
	 */
	private void reportRemovedItem(BaseItem item)
	{
		Date current = new Date();
		item.setExitTime(current);
		item.setContainer(null);

		Date month =
				new Date(current.getYear(), current.getMonth(),
						current.getDate());

		Set<BaseItem> itemsForMonth;
		if(removedItems.containsKey(month))
			itemsForMonth = removedItems.get(month);
		else
			itemsForMonth = new HashSet<BaseItem>();
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
		for(StorageUnit unit: storageUnits)
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
		ObservableArgs obsArg = (ObservableArgs)arg;
		if(obsArg.getChangedObj() instanceof BaseItem
				&& obsArg.getUpdateType() == UpdateType.REMOVED)
			this.reportRemovedItem((BaseItem)obsArg.getChangedObj());
		this.notifyObservers(obsArg);
	}

}
