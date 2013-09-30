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
public class CountThreeMonthSupply extends CountAmount
{

	/**
	 * @precondition amount must be > 0;
	 * @postcondition amount is set to passed in amount
	 * @param amount
	 * @throws InvalidUnitSizeException 
	 */
	public CountThreeMonthSupply(int amount)
	{
		super(amount);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CountThreeMonthSupply [amount=" + amount + ", unitType="
				+ unitType + "]";
	}
}
