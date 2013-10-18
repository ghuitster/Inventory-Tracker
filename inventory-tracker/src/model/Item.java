
package model;

import gui.common.Tagable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to represent an individual Item in the Inventory Tracker
 * @author Michael
 * 
 */
public class Item extends BaseItem implements Serializable, ITaggable
{
	// Variables
	private static final long serialVersionUID = -5007529040849157344L;
	private Product product;
	private IBarcode barcode;
	private Date entryDate;
	private Date expirationDate;
	private Date exitTime;
	private BaseProductContainer container;

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
	public Item(Product product, IBarcode barcode, Date entryDate,
			Date expirationDate, Date exitTime, BaseProductContainer container)
	{
		this.product = product;
		this.barcode = barcode;
		this.entryDate = entryDate;
		this.expirationDate = expirationDate;
		this.exitTime = exitTime;
		this.container = container;
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
			if(Barcode.isValid(barcode.getNumber()))
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
	public boolean ableToSetContainer(StorageUnit container)
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
	 * @see model.IItem#ableToSetExpirationDate(java.util.Date)
	 */
	@Override
	public boolean ableToSetExpirationDate(Date expirationDate)
	{
		boolean response = false;
		Date today = new Date();

		if(expirationDate != null)
		{
			if(today.compareTo(expirationDate) >= 0)
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
	public boolean ableToSetProduct(BaseProduct product)
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
			{
				return false;
			}
		}
		else if(!barcode.equals(other.barcode))
		{
			return false;
		}
		if(container == null)
		{
			if(other.container != null)
			{
				return false;
			}
		}
		else if(!container.equals(other.container))
		{
			return false;
		}
		if(entryDate == null)
		{
			if(other.entryDate != null)
			{
				return false;
			}
		}
		else if(!entryDate.equals(other.entryDate))
		{
			return false;
		}
		if(exitTime == null)
		{
			if(other.exitTime != null)
			{
				return false;
			}
		}
		else if(!exitTime.equals(other.exitTime))
		{
			return false;
		}
		if(expirationDate == null)
		{
			if(other.expirationDate != null)
			{
				return false;
			}
		}
		else if(!expirationDate.equals(other.expirationDate))
		{
			return false;
		}
		if(product == null)
		{
			if(other.product != null)
			{
				return false;
			}
		}
		else if(!product.equals(other.product))
		{
			return false;
		}
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
	public BaseProductContainer getContainer()
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
	 * @see model.IItem#getExpirationDate()
	 */
	@Override
	public Date getExpirationDate()
	{
		return expirationDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#getProduct()
	 */
	@Override
	public Product getProduct()
	{
		return product;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#hashCode()
	 */

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
		result =
				prime
						* result
						+ ((expirationDate == null) ? 0 : expirationDate
								.hashCode());
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#setContainer(model.ProductContainer)
	 */
	@Override
	public void setContainer(BaseProductContainer otherProductContainer)
	{
		this.container = otherProductContainer;
	}

	public void setEntryDate(Date entryDate)
	{
		this.entryDate = entryDate;
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#setExpirationDate(java.util.Date)
	 */
	@Override
	public void setExpirationDate(Date expirationDate)
	{
		this.expirationDate = expirationDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IItem#setProduct(model.Product)
	 */
	@Override
	public void setProduct(Product product)
	{
		this.product = product;
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
				+ expirationDate + ", exitTime=" + exitTime + ", container="
				+ container + "]";
	}
	
	private Object tag;
	@Override
	public Object getTag()
	{ return tag; }

	@Override
	public void setTag(Object tag)
	{ this.tag = tag; }

}
