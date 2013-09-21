/**
 * 
 */
package model;

/**
 * @author Michael
 *
 */
public class UnitSize
{
	// Variables
	private float size = 0.0f;
	private UnitType unit = null;
	
	// Constructor
	public UnitSize(float s, UnitType u)
	{
		this.size = s;
		this.unit = u;
	}
	
	// Methods
	/**
	 * @return the size
	 */
	public float getSize()
	{
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(float size)
	{
		this.size = size;
	}

	/**
	 * @return the unit
	 */
	public UnitType getUnit()
	{
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(UnitType unit)
	{
		this.unit = unit;
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
