
package model;

import java.util.Date;
import java.util.Set;

/**
 * A class to represent a product
 * @author David
 */
public class Product
{
	private Date creationDate;
	private String description;
	private Barcode barcode;
	private UnitSize size;
	private int shelfLife;
	private ThreeMonthSupply threeMonthSupply;
	private final Set<ProductContainer> containers;

	/**
	 * @param creationDate
	 * @param description
	 * @param barcode
	 * @param size
	 * @param shelfLife
	 * @param threeMonthSupply
	 * @param containers
	 */
	public Product(Date creationDate, String description, Barcode barcode,
			UnitSize size, int shelfLife, ThreeMonthSupply threeMonthSupply,
			Set<ProductContainer> containers)
	{
		super();
		this.creationDate = creationDate;
		this.description = description;
		this.barcode = barcode;
		this.size = size;
		this.shelfLife = shelfLife;
		this.threeMonthSupply = threeMonthSupply;
		this.containers = containers;
	}

	/**
	 * 
	 * @param container the container to attempt to add
	 * @return whether the container can be added or not
	 */
	public boolean ableToAddContainer(ProductContainer container)
	{
		return true;
	}

	/**
	 * @param barcode the Barcode to attempt to set
	 * @return if the Barcode can be set or not
	 */
	public boolean ableToSetBarcode(Barcode barcode)
	{
		return true;
	}

	/**
	 * @param description the description to attempt to set
	 * @return if the description can be set or not
	 */
	public boolean ableToSetDescription(String description)
	{
		return true;
	}

	/**
	 * @param shelfLife the shelfLife to attempt to set
	 * @return if the shelflife can be set or not
	 */
	public boolean ableToSetShelfLife(int shelfLife)
	{
		return true;
	}

	/**
	 * @param size the size to attempt to set
	 * @return whether the size can be set or not
	 */
	public boolean ableToSetSize(UnitSize size)
	{
		return true;
	}

	/**
	 * @param threeMonthSupply the threeMonthSupply to attempt to set
	 * @return whether the threeMonthSupply could be set or not
	 */
	public boolean ableToSetThreeMonthSupply(ThreeMonthSupply threeMonthSupply)
	{
		return true;
	}

	/**
	 * 
	 * @param container the container to add
	 */
	public void addContainer(ProductContainer container)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if(barcode == null)
		{
			if(other.barcode != null)
				return false;
		}
		else if(!barcode.equals(other.barcode))
			return false;
		if(containers == null)
		{
			if(other.containers != null)
				return false;
		}
		else if(!containers.equals(other.containers))
			return false;
		if(creationDate == null)
		{
			if(other.creationDate != null)
				return false;
		}
		else if(!creationDate.equals(other.creationDate))
			return false;
		if(description == null)
		{
			if(other.description != null)
				return false;
		}
		else if(!description.equals(other.description))
			return false;
		if(shelfLife != other.shelfLife)
			return false;
		if(size == null)
		{
			if(other.size != null)
				return false;
		}
		else if(!size.equals(other.size))
			return false;
		if(threeMonthSupply == null)
		{
			if(other.threeMonthSupply != null)
				return false;
		}
		else if(!threeMonthSupply.equals(other.threeMonthSupply))
			return false;
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
	 * @return the creationDate
	 */
	public Date getCreationDate()
	{
		return creationDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the shelfLife
	 */
	public int getShelfLife()
	{
		return shelfLife;
	}

	/**
	 * @return the size
	 */
	public UnitSize getSize()
	{
		return size;
	}

	/**
	 * @return the threeMonthSupply
	 */
	public ThreeMonthSupply getThreeMonthSupply()
	{
		return threeMonthSupply;
	}

	/*
	 * (non-Javadoc)
	 * 
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
						+ ((containers == null) ? 0 : containers.hashCode());
		result =
				prime
						* result
						+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result =
				prime * result
						+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + shelfLife;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result =
				prime
						* result
						+ ((threeMonthSupply == null) ? 0 : threeMonthSupply
								.hashCode());
		return result;
	}

	/**
	 * @param barcode the Barcode to set
	 */
	public void setBarcode(Barcode barcode)
	{
		this.barcode = barcode;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @param shelfLife the shelfLife to set
	 */
	public void setShelfLife(int shelfLife)
	{
		this.shelfLife = shelfLife;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(UnitSize size)
	{
		this.size = size;
	}

	/**
	 * @param threeMonthSupply the threeMonthSupply to set
	 */
	public void setThreeMonthSupply(ThreeMonthSupply threeMonthSupply)
	{
		this.threeMonthSupply = threeMonthSupply;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Product [creationDate=" + creationDate + ", description="
				+ description + ", barcode=" + barcode + ", size=" + size
				+ ", shelfLife=" + shelfLife + ", threeMonthSupply="
				+ threeMonthSupply + ", containers=" + containers + "]";
	}
}
