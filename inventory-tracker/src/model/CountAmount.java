/**
 * 
 */

package model;

/**
 * Abstract class to further define CountThreeMonthSupply and CountUnitSize
 * where the unit type will only be "count"
 * @author Michael
 * 
 */
public abstract class CountAmount extends Amount
{
	// Variables
	protected int amount;

	/**
	 * @precondition amount must be > 0
	 * @postcondition amount == passed in amount
	 * @postcondition unitType == UnitType.COUNT
	 * @param amount the amount of the count
	 * @param unitType the type of unit. "count" in this case
	 */
	public CountAmount(int amount)
	{
		super(UnitType.COUNT);
		this.amount = amount;
	}

	/**
	 * Method to test the amount to set
	 * @precondition amount must be > 0
	 * @param amount
	 * @return result the result of the test
	 */
	public boolean ableToSetAmount(int amount)
	{
		boolean result = true;
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
		CountAmount other = (CountAmount) obj;
		if(amount != other.amount)
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the amount
	 */
	public int getAmount()
	{
		return amount;
	}

	/**
	 * Method to get the unit type returns unitType
	 */
	@Override
	public UnitType getUnitType()
	{
		return this.unitType;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		return result;
	}

	/**
	 * @precondition amount must be > 0
	 * @postcondition amount == passed in amount
	 * @param amount the amount to set
	 */
	public void setAmount(int amount)
	{
		this.amount = amount;
	}

}
