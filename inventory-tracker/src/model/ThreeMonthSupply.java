/**
 * 
 */

package model;

import model.exception.InvalidUnitSizeException;

/**
 * Class to represent a three month supply with unit types other than "count"
 * @author Michael
 * 
 */
public class ThreeMonthSupply extends NonCountAmount
{

	/**
	 * @precondition amount must be > 0.0f
	 * @precondition unitType must != UnitType.COUNT
	 * @param amount
	 * @param unitType
	 * ThreeMonthSupply constructor
	 * @param amount the amount IE 50.3, 20
	 * @param unitType what type of unit to use
	 * @throws InvalidUnitSizeException
	 */
	public ThreeMonthSupply(float amount, UnitType unitType) throws InvalidUnitSizeException
	{
		super(amount, unitType);
		/*this.amount = amount;
		this.unitType = unitType;*/
	}

	/**
	 * @param amount the amount to attempt to set
	 * @return if the amount can be set or not
	 */
	public boolean ableToSetAmount(float amount)
	{
		return true;
	}

	/**
	 * @param unitType the unitType to attempt to set
	 * @return if the unitType can be set or not
	 */
	public boolean ableToSetUnitType(UnitType unitType)
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
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		ThreeMonthSupply other = (ThreeMonthSupply) obj;
		if(Float.floatToIntBits(this.amount) != Float
				.floatToIntBits(other.amount))
			return false;
		if(this.unitType != other.unitType)
			return false;
		return true;
	}

	/**
	 * @return the amount
	 */
	public float getAmount()
	{
		return this.amount;
	}

	/**
	 * @return the unitType
	 */
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
		result = prime * result + Float.floatToIntBits(this.amount);
		result =
				prime
						* result
						+ ((this.unitType == null) ? 0 : this.unitType
								.hashCode());
		return result;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount)
	{
		this.amount = amount;
	}

	/**
	 * @param unitType the unitType to set
	 */
	public void setUnitType(UnitType unitType)
	{
		this.unitType = unitType;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NewThreeMonthSupply [amount=" + amount + ", unitType="
				+ unitType + "]";
	}

}
