
package model;

/**
 * Class to represent the unit size of a Product.
 * @author Michael
 * 
 */
public class UnitSize
{
	// Variables
	private float size;
	private UnitType unit;
	
	/**
	 * The UnitSize constructor.
	 * @param size a float value indicating the size of the Product with a unit size.
	 * @param unit a UnitType indicating the type of units for the Product
	 */
	public UnitSize(float size, UnitType unit)
	{
		this.size = size;
		this.unit = unit;
	}
	
	// Methods
	/**
	 * Returns the size value of the UnitSize object
	 * @return the size
	 */
	public float getSize()
	{
		return size;
	}

	/**
	 * Sets the size value of the UnitSize object
	 * @param size the size to set
	 */
	public void setSize(float size)
	{
		this.size = size;
	}

	/**
	 * Checks to see if the size we want to set is valid.
	 * @param size the size to attempt to set
	 * @return if the size can be set or not
	 */
	public boolean ableToSetSize(float size)
	{
		return true;
	}
	/**
	 * Returns the UnitType of the UnitSize object
	 * @return unit the UnitType enumeration to return
	 */
	public UnitType getUnit()
	{
		return unit;
	}

	/**
	 * Sets the UnitType value of the UnitSize object
	 * @param unit the UnitType to set.
	 */
	public void setUnit(UnitType unit)
	{
		this.unit = unit;
	}
	
	/**
	 * @param unit the UnitType to attempt to set
	 * @return if the UnitType can be assigned or not
	 */
	public boolean ableToSetUnit(UnitType unit)
	{
		return true;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(size);
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
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
		UnitSize other = (UnitSize) obj;
		if(Float.floatToIntBits(size) != Float.floatToIntBits(other.size))
		{
			return false;
		}
		if(unit != other.unit)
		{
			return false;
		}
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.size + " " + this.unit;
	}
	
}
