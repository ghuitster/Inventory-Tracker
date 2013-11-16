/**
 * 
 */

package model;

/**
 * Class to represent a three month supply with unit types other than "count"
 * @author Michael
 * 
 */
public class ThreeMonthSupply extends NonCountAmount
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8151916103900443511L;

	/**
	 * @pre amount must be > 0.0f
	 * @pre unitType must != UnitType.COUNT
	 * @param amount
	 * @param unitType ThreeMonthSupply constructor
	 * @param amount the amount IE 50.3, 20
	 * @param unitType what type of unit to use
	 */
	public ThreeMonthSupply(float amount, UnitType unitType)
	{
		super(amount, unitType);
		/*
		 * this.amount = amount; this.unitType = unitType;
		 */
	}

	/**
	 * @param unitType the unitType to attempt to set
	 * @return if the unitType can be set or not
	 */
	public boolean ableToSetUnitType(UnitType unitType)
	{
		boolean result = false;
		if(unitType != UnitType.COUNT)
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
		return amount + " " + unitType;
	}

}
