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
	 * @precondition amount must be > 0.0f
	 * @precondition unitType must != UnitType.COUNT
	 * @param amount
	 * @param unitType
	 */
	public ThreeMonthSupply(float amount, UnitType unitType)
	{
		super(amount, unitType);
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
