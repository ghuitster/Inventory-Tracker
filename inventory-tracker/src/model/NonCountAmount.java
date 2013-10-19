/**
 * 
 */

package model;

import model.exception.InvalidUnitSizeException;

/**
 * Abstract Class to further define Non Count Three Month Supply and UnitSize
 * @author Michael
 * 
 */
public abstract class NonCountAmount extends Amount
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2175122254980347945L;
	// Variables
	protected float amount;

	/**
	 * @pre amount must be > 0.0f
	 * @pre unitType must != UnitType.COUNT
	 * @param amount the amount
	 * @param unitType the unit type for the amount
	 * @throws InvalidUnitSizeException
	 */
	public NonCountAmount(float amount, UnitType unitType)
	{
		super(unitType);
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
		boolean result = false;
		if(amount > 0.0 && unitType != UnitType.COUNT)
			result = true;
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
		if(unitType != other.getUnitType())
		{
			return false;
		}
		return true;
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
	 * Method to get the unit type returns unitType
	 * @return unitType the UnitType for the amount
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
		result = prime * result + Float.floatToIntBits(amount);
		return result;
	}

	/**
	 * Method to set the amount
	 * @param amount the amount to set
	 * @throws InvalidUnitSizeException
	 */
	public void setAmount(float amount)
	{
		this.amount = amount;
	}
	
	@Override
	public String toString()
	{
		return amount + " " + this.unitType;
	}

}
