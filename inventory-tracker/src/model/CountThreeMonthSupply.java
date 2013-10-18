/**
 * 
 */

package model;

import model.exception.InvalidUnitSizeException;

/**
 * A class to represent a count three month supply, where unit type is always
 * "count"
 * @author Michael
 * 
 */
public class CountThreeMonthSupply extends CountAmount
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5789297880919876394L;

	/**
	 * @pre amount must be > 0;
	 * @post amount is set to passed in amount
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
