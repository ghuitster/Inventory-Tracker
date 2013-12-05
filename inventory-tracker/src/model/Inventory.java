
package model;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import model.exception.InvalidNameException;
import model.plugin.Plugin;
import model.plugin.PluginManager;
import model.visitor.ExpiredItemVisitor;
import model.visitor.IVisitor;
import model.visitor.ItemVisitor;
import model.visitor.NMonthSupplyVisitor;
import model.visitor.NoticeVisitor;
import model.visitor.ProductVisitor;
import common.util.DateUtils;

/**
 * Singleton class to manage the entire Inventory system
 * @author Brian
 * 
 */
public class Inventory extends Observable implements IInventory, Serializable
{
	private class DateInfo
	{
		public DateType dateType;

		public IItem item;

		public DateInfo(DateType dateType, IItem item)
		{
			this.dateType = dateType;
			this.item = item;
		}
	}

	private enum DateType
	{
		EntryDate, ExitDate, StartOfReport, EndOfReport
	}

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
	private Set<IStorageUnit> storageUnits;

	/**
	 * Persistence object for saving and loading data
	 */
	private IPersistence persistence;
	/**
	 * Mapping of all removed Items, for easy retrieval
	 */
	private SortedSet<IItem> removedItems;

	/**
	 * Record of orphaned products
	 */
	private SortedSet<IProduct> removedProducts;

	/**
	 * Static reference to the one and only Inventory instance
	 */
	private static Inventory instance;

	/**
	 * ItemBarcode Number mapping for all items.
	 */
	private Map<String, IItem> barcodeItems;

	private long lastGeneratedBarCode;
	
	@Override
	public void setLastGeneratedBarCode(ItemBarcode barcode)
	{
		lastGeneratedBarCode = Long.parseLong(barcode.getNumber());
	}

	private Date lastRemovedItemReportTime;

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
		this.removedItems = new TreeSet<IItem>(new RemovedItemComparator());
		this.removedProducts = new TreeSet<IProduct>();
		this.barcodeItems = new HashMap<String, IItem>();
		this.lastGeneratedBarCode = 400000000000l;
		this.lastRemovedItemReportTime = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IInventory#ableToAddStorageUnit(model.StorageUnit)
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
			if(unit.getName().toLowerCase().equals(name.toLowerCase()))
				return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IInventory#ableToRemoveProduct(IProduct product)
	 */
	@Override
	public boolean ableToRemoveProduct(IProduct product)
	{
		return this.removedProducts.contains(product);
	}

	@Override
	public boolean ableToRemoveStorageUnit(IStorageUnit storageUnit)
	{
		ProductVisitor visitor = new ProductVisitor();
		storageUnit.traverse(visitor);
		return visitor.getResults().size() == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IInventory#addStorageUnit(model.StorageUnit)
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

	private void ensureDateUniqueness(SortedMap<Date, DateInfo> dateList,
			Date entry)
	{
		while(dateList.containsKey(entry))
			entry.setTime(entry.getTime() + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IInventory#getAllItems(IProduct product)
	 */
	@Override
	public SortedSet<IItem> getAllItems(IProduct product)
	{
		ItemVisitor visitor = new ItemVisitor(product);
		this.traverse(visitor);
		return visitor.getResults();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IInventory#getAllProducts()
	 */
	@Override
	public SortedSet<IProduct> getAllProducts()
	{
		ProductVisitor visitor = new ProductVisitor();
		this.traverse(visitor);
		return visitor.getResults();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IInventory#getAllStorageUnits()
	 */
	@Override
	public SortedSet<IStorageUnit> getAllStorageUnits()
	{
		return new TreeSet<IStorageUnit>(this.storageUnits);
	}

	@Override
	public List<IItem> getExpiredItems()
	{
		ExpiredItemVisitor visitor = new ExpiredItemVisitor();
		this.traverse(visitor);
		return visitor.getResult();
	}

	@Override
	public Map<IProductGroup, List<IProduct>> getInconsistencies()
	{
		NoticeVisitor visitor = new NoticeVisitor();
		this.traverse(visitor);
		return visitor.getResult();
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

	private float getItemLifeSpanDays(Date exit, Date entry)
	{
		return (int) (exit.getTime() - entry.getTime()) / 1000f / 3600f / 24f;
	}

	@Override
	public Date getLastRemovedItemReportTime()
	{
		return this.lastRemovedItemReportTime;
	}

	@Override
	public ProductSupplyReport getLowSupplies(int months)
	{
		NMonthSupplyVisitor visitor = new NMonthSupplyVisitor(months);
		this.traverse(visitor);
		ProductSupplyReport report = new ProductSupplyReport(months);
		report.setGroupSupplies(visitor.getGroupSupplies());
		report.setProductSupplies(visitor.getProductSupplies());
		return report;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IInventory#getPersistence()
	 */
	@Override
	public IPersistence getPersistence()
	{
		return this.persistence;
	}

	@Override
	public List<ProductStats> getProductStats(int months)
	{
		List<ProductStats> allStats = new ArrayList<ProductStats>();

		Date start = DateUtils.currentDate();
		start.setMonth(start.getMonth() - months);

		for(IProduct product: this.getAllProducts())
		{
			SortedSet<IItem> items = this.getAllItems(product);

			SortedMap<Date, DateInfo> dateList = new TreeMap<Date, DateInfo>();

			// Go through all items for this product and place them in a sorted
			// mapping
			// This mapping serves as a sorted list of every time the item count
			// changed
			for(IItem item: items)
			{
				Date entry = (Date) item.getEntryDate().clone();
				ensureDateUniqueness(dateList, entry);

				dateList.put(entry, new DateInfo(DateType.EntryDate, item));
			}
			for(IItem item: removedItems)
			{
				if(item.getProduct() == product)
				{
					Date entry = (Date) item.getEntryDate().clone();
					ensureDateUniqueness(dateList, entry);
					Date exit = (Date) item.getExitTime().clone();
					ensureDateUniqueness(dateList, exit);

					dateList.put(entry, new DateInfo(DateType.EntryDate, item));
					dateList.put(exit, new DateInfo(DateType.ExitDate, item));
				}
			}

			dateList.put(start, new DateInfo(DateType.StartOfReport, null));
			dateList.put(DateUtils.currentDate(), new DateInfo(
					DateType.EndOfReport, null));

			Date lastDate = null;
			boolean track = false;
			int runningTotal = 0;

			int supplyMin = 0, supplyMax = 0, supplyUsed = 0, supplyAdded = 0, totalSeconds =
					0;
			float supplyEnumerator = 0;

			int usedAgeMax = 0, usedAgeCount = 0;
			float usedAgeTotal = 0;

			int currentAgeCount = 0, currentAgeMax = 0;
			float currentAgeTotal = 0;

			for(Date date: dateList.keySet())
			{
				DateInfo info = dateList.get(date);
				if(info.dateType != DateType.StartOfReport)
				{
					if(track)
					{
						if(info.dateType == DateType.EntryDate)
							supplyAdded++;
						if(info.dateType == DateType.ExitDate)
							supplyUsed++;
					}

					if(track && lastDate != null && runningTotal > 0)
					{
						int blockSeconds =
								(int) ((date.getTime() - lastDate.getTime()) / 1000);
						totalSeconds += blockSeconds;
						supplyEnumerator += runningTotal * blockSeconds;

						if(info.dateType == DateType.ExitDate)
						{
							float usedAge =
									getItemLifeSpanDays(
											info.item.getExitTime(),
											info.item.getEntryDate());
							usedAge = Math.round(usedAge);
							usedAgeMax = Math.max(usedAgeMax, (int) usedAge);

							usedAgeTotal += usedAge;
							usedAgeCount++;
						}

					}

					if(info.dateType == DateType.EntryDate)
						runningTotal++;
					else if(info.dateType == DateType.ExitDate)
						runningTotal--;

					if(track && lastDate != null)
					{
						supplyMin = Math.min(supplyMin, runningTotal);
						supplyMax = Math.max(supplyMax, runningTotal);
					}
				}
				else
				{
					track = true;
					supplyMin = runningTotal;
					supplyMax = runningTotal;
				}

				lastDate = date;

				if(info.item != null && info.item.getExitTime() == null)
				{
					float itemAge =
							getItemLifeSpanDays(DateUtils.currentDate(),
									info.item.getEntryDate());
					currentAgeTotal += itemAge;
					currentAgeCount++;

					itemAge = Math.round(itemAge);
					currentAgeMax = Math.max(currentAgeMax, (int) itemAge);
				}
			}

			ProductStats stats = new ProductStats(product);
			stats.setCurrentSupply(runningTotal);
			stats.setAverageSupply(supplyEnumerator / totalSeconds);
			stats.setMinSupply(supplyMin);
			stats.setMaxSupply(supplyMax);
			stats.setUsedSupply(supplyUsed);
			stats.setAddedSupply(supplyAdded);
			stats.setAvgUsedAge(usedAgeTotal / usedAgeCount);
			stats.setMaxUsedAge(usedAgeMax);
			stats.setAvgCurrentAge(currentAgeTotal / currentAgeCount);
			stats.setMaxCurrentAge(currentAgeMax);

			allStats.add(stats);
		}
		return allStats;
	}

	@Override
	public SortedSet<RemovedItems> getRemovedItems(Date since)
	{

		SortedSet<RemovedItems> results = new TreeSet<RemovedItems>();

		for(IItem item: this.removedItems)
		{
			if(item.getExitTime().compareTo(since) < 0)
				break;

			RemovedItems result = null;
			boolean found = false;
			for(RemovedItems existingResult: results)
			{
				if(existingResult.getProduct() == item.getProduct())
				{
					result = existingResult;
					found = true;
					break;
				}
			}

			if(!found)
			{
				result = new RemovedItems(item.getProduct());

				ItemVisitor currentFinder = new ItemVisitor(item.getProduct());
				this.traverse(currentFinder);
				result.setSupply(currentFinder.getResults().size());
				results.add(result);

			}

			result.setCount(result.getCount() + 1);

		}

		this.lastRemovedItemReportTime = DateUtils.currentDate();
		return results;
	}

	/**
	 * Returns a unique bar code
	 * @return A bar code which is unique
	 */
	public long getUniqueBarCode()
	{
		return lastGeneratedBarCode++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IInventory#removeAllStorageUnits()
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
	 * @see model.removeProduct(IProduct product)
	 */
	@Override
	public void removeProduct(IProduct product)
	{
		this.removedProducts.remove(product);
		product.deleteObserver(this);
		this.setChanged();
		this.notifyObservers(new ObservableArgs(product, UpdateType.REMOVED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IInventory#removeStorageUnit(model.StorageUnit)
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

	private void reportAddedItem(IItem item)
	{
		if(item.getExitTime() != null)
			this.removedItems.remove(item);
		item.setExitTime(null);
		this.barcodeItems.put(item.getBarcode().toString(), item);
	}

	/**
	 * Reports that an item has been removed from the system
	 * @pre Item is not null
	 * @post Item's exit time has been set to the current time, its container is
	 *       null, and it has been added to the map of removed items
	 * @param item The item that has been removed
	 */
	@Override
	public void reportRemovedItem(IItem item)
	{
		if(item.getExitTime() == null)
			item.setExitTime(DateUtils.currentDate());
		item.setContainer(null);
		this.removedItems.add(item);
		this.barcodeItems.remove(item.getBarcode().toString());
	}
	
	@Override
	public void reportRemovedProduct(IProduct product)
	{
		this.removedProducts.add(product);
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
	public void traverse(IVisitor visitor)
	{
		for(IStorageUnit unit: this.storageUnits)
		{
			unit.traverse(visitor);
		}
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
			}
			else if(obsArg.getUpdateType() == UpdateType.ADDED)
			{
				this.reportAddedItem(item);
			}
		}
		else if(obsArg.getChangedObj() instanceof IProduct)
		{
			IProduct product = (IProduct)obsArg.getChangedObj();
			
			if(obsArg.getUpdateType() == UpdateType.REMOVED)
				this.removedProducts.add((IProduct) obsArg.getChangedObj());
			else if (obsArg.getUpdateType() == UpdateType.ADDED)
				this.removedProducts.remove(obsArg.getChangedObj());
		}

		this.setChanged();
		this.notifyObservers(obsArg);
	}

	public String findProductInfo(String barcode)
	{
		PluginManager pm = new PluginManager();
		Plugin plugin = pm.getFirstPlugin();
		String result = plugin.findProduct(barcode);
		if(result == null)
			return "";
		return result;
	}

}
