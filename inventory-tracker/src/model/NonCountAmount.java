/**
 * 
 */

package model;

/**
 * Abstract Class to further define Non Count Three Month Supply and UnitSize
 * @author Michael
 * 
 */
public abstract class NonCountAmount extends Amount
{
	// Variables
	protected float amount;

	/**
	 * @precondition amount must be > 0.0f
	 * @precondition unitType must != UnitType.COUNT
	 * @param amount the amount
	 * @param unitType the unit type for the amount
	 */
	public NonCountAmount(float amount, UnitType unitType)
	{
		super(unitType);
		this.amount = amount;

	}

	/**
	 * Method to get the unit type returns unitType
	 * @return unitType the UnitType for the amount
	 */
	@Override
	public UnitType getUnitType()
	{
		return this.unitType;
	}

	/**
	 * Method to get the amount
	 * @return the amount
	 */
	public float getAmount()
	{
		return amount;
	}

	/**
	 * Method to set the amount
	 * @param amount the amount to set
	 */
	public void setAmount(float amount)
	{
		this.amount = amount;
	}

	/**
	 * Method to see if we can set the amount to the amount provided
	 * @param amount the amount to see if we can set
	 * @return result the result of the test
	 * 
	 */
	public boolean ableToSetAmount(float amount)
	{
		boolean result = true;

		return result;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
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
		NonCountAmount other = (NonCountAmount) obj;
		if(Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
		{
			return false;
		}
		return true;
	}

}
