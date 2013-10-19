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

	/**
	 * 
	 */
	private static final long serialVersionUID = 5858104216462427971L;

	/**
	 * @pre size must be > 0
	 * @param size
	 */
	public CountUnitSize(int size)
	{
		super(size);
	}

	/**
	 * Method to see if we can set size
	 * @pre size must be > 0
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
		if(amount != other.amount)
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
		return amount;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + amount;
		return result;
	}

	/**
	 * @pre size must be > 0
	 * @post size is set to passed in size
	 * @param size the size to set
	 */
	public void setSize(int size)
	{
		super.setAmount(size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "" + amount;
	}
}
