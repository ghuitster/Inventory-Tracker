
package model;

import java.util.Date;

/**
 * Class to represent an individual Item in the Intentory Tracker
 * @author Michael
 * 
 */
public class Item
{
	// Variables
	private Product product;
	private Barcode barcode;
	private final Date entryDate;
	private Date expirationDate;
	private Date exitTime;
	private ProductContainer container;

	/**
	 * @param product
	 * @param barcode
	 * @param entryDate
	 * @param expirationDate
	 * @param exitTime
	 * @param container
	 */
	public Item(Product product, Barcode barcode, Date entryDate,
			Date expirationDate, Date exitTime, ProductContainer container)
	{
		this.product = product;
		this.barcode = barcode;
		this.entryDate = entryDate;
		this.expirationDate = expirationDate;
		this.exitTime = exitTime;
		this.container = container;
	}

	/**
	 * @param barcode the Barcode to attempt to set
	 * @return whether or not the barcode can be set
	 */
	public boolean ableToSetBarcode(Barcode barcode)
	{
		return true;
	}

	/**
	 * @param container the ProductContainer to attempt to set
	 * @return whether or not container can be set
	 */
	public boolean ableToSetContainer(ProductContainer container)
	{
		return true;
	}

	/**
	 * @param exitTime the exitTime Date to attempt to set
	 * @return whether or not we can set the exitTime
	 */
	public boolean ableToSetExitTime(Date exitTime)
	{
		return true;
	}

	/**
	 * @param expirationDate the expirationDate Date to attempt to set
	 * @return whether or not the expirationDate can be set
	 */
	public boolean ableToSetExpirationDate(Date expirationDate)
	{
		return true;
	}

	/**
	 * @param product the Product to attempt to set
	 * @return whether or not the product can be set
	 */
	public boolean ableToSetProduct(Product product)
	{
		return true;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
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

	/**
	 * @return the barcode
	 */
	public Barcode getBarcode()
	{
		return barcode;
	}

	/**
	 * @return the container
	 */
	public ProductContainer getContainer()
	{
		return container;
	}

	/**
	 * @return the entryDate
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @return the exitTime
	 */
	public Date getExitTime()
	{
		return exitTime;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate()
	{
		return expirationDate;
	}

	/**
	 * @return the product
	 */
	public Product getProduct()
	{
		return product;
	}

	/**
	 * @see java.lang.Object#hashCode()
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

	/**
	 * @param barcode the barcode to set
	 */
	public void setBarcode(Barcode barcode)
	{
		this.barcode = barcode;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(ProductContainer container)
	{
		this.container = container;
	}

	/**
	 * @param exitTime the exitTime to set
	 */
	public void setExitTime(Date exitTime)
	{
		this.exitTime = exitTime;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate)
	{
		this.expirationDate = expirationDate;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product)
	{
		this.product = product;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Item [product=" + product + ", barcode=" + barcode
				+ ", entryDate=" + entryDate + ", expirationDate="
				+ expirationDate + ", exitTime=" + exitTime + ", container="
				+ container + "]";
	}

}
