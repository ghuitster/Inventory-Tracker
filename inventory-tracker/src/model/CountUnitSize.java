/**
 * 
 */

package model;

/**
 * A class to represent a count three month supply, where unit type is always
 * "count"
 * @author Michael
 * 
 */
public class CountUnitSize extends CountAmount
{
	// Variables
	private int size;

	/**
	 * @precondition size must be > 0
	 * @param size
	 */
	public CountUnitSize(int size)
	{
		super(size);
		this.size = size;
	}

	/**
	 * Method to see if we can set size
	 * @precondition size must be > 0
	 * @param size
	 * @return
	 */
	public boolean ableToSetUnitSize(int size)
	{
		return super.ableToSetAmount(size);
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
		if(!super.equals(obj))
		{
			return false;
		}
		if(getClass() != obj.getClass())
		{
			return false;
		}
		CountUnitSize other = (CountUnitSize) obj;
		if(size != other.size)
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the size
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + size;
		return result;
	}

	/**
	 * @precondition size must be > 0
	 * @postcondition size is set to passed in size
	 * @param size the size to set
	 */
	public void setSize(int size)
	{
		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CountUnitSize [size=" + size + ", unitType=" + unitType + "]";
	}
}
