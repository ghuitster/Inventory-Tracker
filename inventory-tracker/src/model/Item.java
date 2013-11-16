
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

/**
 * Class to represent an individual Item in the Inventory Tracker
 * @author Michael
 * 
 */
public class Item extends Observable implements IItem, Serializable, ITaggable
{
	// Variables
	private static final long serialVersionUID = -5007529040849157344L;
	private IProduct product;
	private IBarcode barcode;
	private Date entryDate;
	private Date exitTime = null;
	private IProductContainer container;

	private transient Object tag;

	/**
	 * @pre product must != null && must exist
	 * @pre barcode must != null and must be valid
	 * @pre entryDate must be > 1/1/2000 and == the date item was entered
	 * @pre expirationDate != null only if Product shelfLife is defined
	 * @pre exitTime is only set when Item is removed.
	 * @pre container Before it is removed, an Item is contained in one Product
	 *      Container. After it is removed, it is contained in no Product
	 *      Containers.
	 * @param product
	 * @param barcode
	 * @param entryDate
	 * @param expirationDate
	 * @param exitTime
	 * @param container
	 */
	public Item(IProduct product, IBarcode barcode, Date entryDate,
			Date expirationDate)
	{
		this.product = product;
		this.barcode = barcode;
		this.entryDate = entryDate;
		this.addObserver(Inventory.getInstance());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#ableToSetBarcode(model.IBarcode)
	 */
	@Override
	public boolean ableToSetBarcode(IBarcode barcode)
	{
		boolean response = false;

		if(barcode != null)
		{
			if(ItemBarcode.isValid(barcode.getNumber()))
			{
				response = true;
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#ableToSetContainer(model.StorageUnit)
	 */
	@Override
	public boolean ableToSetContainer(IStorageUnit container)
	{
		return container.ableToAddItem(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#ableToSetExitTime(java.util.Date)
	 */
	@Override
	public boolean ableToSetExitTime(Date exitTime)
	{
		boolean response = false;
		Calendar today = Calendar.getInstance();

		if(exitTime != null)
		{
			if(today.getTime().compareTo(exitTime) == 0)
			{
				response = true;
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#ableToSetProduct(model.Product)
	 */
	@Override
	public boolean ableToSetProduct(IProduct product)
	{
		boolean response = false;

		if(product != null)
		{
			if(ProductDescription.isValid(product.getDescription()
					.getDescription()))
			{
				response = true;
			}
		}

		return response;
	}

	@Override
	public int compareTo(IItem o)
	{
		int ret = this.getEntryDate().compareTo(o.getEntryDate());
		if(ret == 0)
			return this.getBarcode().toString()
					.compareTo(o.getBarcode().toString());
		else
			return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(getClass() != obj.getClass())
		{
			return false;
		}
		Item other = (Item) obj;
		if(barcode == null)
		{
			if(other.barcode != null)
				return false;
		}
		else if(!barcode.equals(other.barcode))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#getBarcode()
	 */
	@Override
	public IBarcode getBarcode()
	{
		return barcode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#getContainer()
	 */
	@Override
	public IProductContainer getContainer()
	{
		return container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#getEntryDate()
	 */
	@Override
	public Date getEntryDate()
	{
		return entryDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#getExitTime()
	 */
	@Override
	public Date getExitTime()
	{
		return exitTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#hashCode()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#getExpirationDate()
	 */
	@Override
	public Date getExpirationDate()
	{
		if(this.getProduct().getShelfLife() == 0)
			return null;

		Date expiration = (Date) this.getEntryDate().clone();
		expiration.setMonth(expiration.getMonth()
				+ this.getProduct().getShelfLife());
		return expiration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#getProduct()
	 */
	@Override
	public IProduct getProduct()
	{
		return product;
	}

	@Override
	public Object getTag()
	{
		return tag;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result =
				prime * result
						+ ((container == null) ? 0 : container.hashCode());
		result =
				prime * result
						+ ((entryDate == null) ? 0 : entryDate.hashCode());
		result =
				prime * result + ((exitTime == null) ? 0 : exitTime.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#setBarcode(model.IBarcode)
	 */
	@Override
	public void setBarcode(IBarcode barcode)
	{
		this.barcode = barcode;

		signalChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#setContainer(model.ProductContainer)
	 */
	@Override
	public void setContainer(IProductContainer otherProductContainer)
	{
		this.container = otherProductContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#setEntryDate(java.util.Date)
	 */
	@Override
	public void setEntryDate(Date entryDate)
	{
		this.entryDate = entryDate;
		signalChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#setExitTime(java.util.Date)
	 */
	@Override
	public void setExitTime(Date exitTime)
	{
		this.exitTime = exitTime;
		signalChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#setProduct(model.Product)
	 */
	@Override
	public void setProduct(IProduct product)
	{
		this.product = product;
		signalChanged();
	}

	@Override
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	@Override
	public void signalChanged()
	{
		this.setChanged();
		this.notifyObservers(new ObservableArgs(this, UpdateType.UPDATED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#toString()
	 */
	@Override
	public String toString()
	{
		return "Item [product=" + product + ", barcode=" + barcode
				+ ", entryDate=" + entryDate + ", expirationDate="
				+ getExpirationDate() + ", exitTime=" + exitTime
				+ ", container=" + container + "]";
	}

}
