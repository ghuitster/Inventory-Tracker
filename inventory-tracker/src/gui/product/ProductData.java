
package gui.product;

import gui.common.Tagable;

/**
 * Display data class for products.
 */
public class ProductData extends Tagable
{

	/**
	 * Description attribute.
	 */
	private String _description;

	/**
	 * Size attribute.
	 */
	private String _size;

	/**
	 * Count attribute.
	 */
	private String _count;

	/**
	 * Shelf Life attribute
	 */
	private String _shelfLife;

	/**
	 * Supply attribute.
	 */
	private String _supply;

	/**
	 * Barcode attribute.
	 */
	private String _barcode;

	/**
	 * Constructor.
	 * 
	 * {@pre None}
	 * 
	 * {@post getDescription() == ""} {@post getSize() == ""} {@post getCount()
	 * == ""} {@post getShelfLife() == ""} {@post getSupply() == ""} {@post
	 * getBarcode() == ""}
	 */
	public ProductData()
	{
		_description = "";
		_size = "";
		_count = "";
		_shelfLife = "";
		_supply = "";
		_barcode = "";
	}

	/**
	 * Returns the value of the Barcode attribute.
	 */
	public String getBarcode()
	{
		return _barcode;
	}

	/**
	 * Returns the value of the Count attribute.
	 */
	public String getCount()
	{
		return _count;
	}

	/**
	 * Returns the value of the Description value.
	 */
	public String getDescription()
	{
		return _description;
	}

	/**
	 * Returns the value of the Shelf Life attribute.
	 */
	public String getShelfLife()
	{
		return _shelfLife;
	}

	/**
	 * Returns the value of the Size attribute.
	 */
	public String getSize()
	{
		return _size;
	}

	/**
	 * Returns the value of the Supply attribute.
	 */
	public String getSupply()
	{
		return _supply;
	}

	/**
	 * Sets the value of the Barcode attribute.
	 * 
	 * @param barcode New Barcode value
	 * 
	 *            {@pre barcode != null}
	 * 
	 *            {@post getBarcode() == barcode}
	 */
	public void setBarcode(String barcode)
	{
		this._barcode = barcode;
	}

	/**
	 * Sets the value of the Count attribute.
	 * 
	 * @param count New Count value
	 * 
	 *            {@pre count != null}
	 * 
	 *            {@post getCount() == count}
	 */
	public void setCount(String count)
	{
		this._count = count;
	}

	/**
	 * Sets the value of the Description value.
	 * 
	 * @param description New Description value
	 * 
	 *            {@pre description != null}
	 * 
	 *            {@post getDescription() == description}
	 */
	public void setDescription(String description)
	{
		this._description = description;
	}

	/**
	 * Sets the value of the Shelf Life attribute.
	 * 
	 * @param shelfLife New Shelf Life value
	 * 
	 *            {@pre shelfLife != null}
	 * 
	 *            {@post getShelfLife() == shelfLife}
	 */
	public void setShelfLife(String shelfLife)
	{
		this._shelfLife = shelfLife;
	}

	/**
	 * Sets the value of the Size attribute.
	 * 
	 * @param size New Size value
	 * 
	 *            {@pre size != null}
	 * 
	 *            {@post getSize() == size}
	 */
	public void setSize(String size)
	{
		this._size = size;
	}

	/**
	 * Sets the value of the Supply attribute.
	 * 
	 * @param supply New Supply value
	 * 
	 *            {@pre supply != null}
	 * 
	 *            {@post getSupply() == supply}
	 */
	public void setSupply(String supply)
	{
		this._supply = supply;
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
		result =
				prime * result + ((_barcode == null) ? 0 : _barcode.hashCode());
		result = prime * result + ((_count == null) ? 0 : _count.hashCode());
		result =
				prime
						* result
						+ ((_description == null) ? 0 : _description.hashCode());
		result =
				prime * result
						+ ((_shelfLife == null) ? 0 : _shelfLife.hashCode());
		result = prime * result + ((_size == null) ? 0 : _size.hashCode());
		result = prime * result + ((_supply == null) ? 0 : _supply.hashCode());
		return result;
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
		ProductData other = (ProductData) obj;
		if(_barcode == null)
		{
			if(other._barcode != null)
			{
				return false;
			}
		}
		else if(!_barcode.equals(other._barcode))
		{
			return false;
		}
		if(_count == null)
		{
			if(other._count != null)
			{
				return false;
			}
		}
		else if(!_count.equals(other._count))
		{
			return false;
		}
		if(_description == null)
		{
			if(other._description != null)
			{
				return false;
			}
		}
		else if(!_description.equals(other._description))
		{
			return false;
		}
		if(_shelfLife == null)
		{
			if(other._shelfLife != null)
			{
				return false;
			}
		}
		else if(!_shelfLife.equals(other._shelfLife))
		{
			return false;
		}
		if(_size == null)
		{
			if(other._size != null)
			{
				return false;
			}
		}
		else if(!_size.equals(other._size))
		{
			return false;
		}
		if(_supply == null)
		{
			if(other._supply != null)
			{
				return false;
			}
		}
		else if(!_supply.equals(other._supply))
		{
			return false;
		}
		return true;
	}

}
