/**
 * 
 */

package model;

/**
 * @author Michael
 * 
 */
public class ItemBarcode extends Barcode
{

	private static final long serialVersionUID = -4515093831774485774L;

	/**
	 * @pre number must not be null
	 * @param number string of the ItemBarcode number to validate
	 * @return true if valid number
	 */
	public static boolean isValid(String number)
	{
		boolean response = false;

		if(number.matches("4[0-9]{11}"))
			response = true;

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Barcode(java.lang.String)
	 */
	public ItemBarcode(String number)
	{
		super(number);
	}

	/**
	 * @param number the number to attempt to set
	 * @return whether or not the number can be set
	 */
	@Override
	public boolean ableToSetNumber(String number)
	{
		boolean response = false;

		if(number != null)
		{
			response = ItemBarcode.isValid(number);
		}

		return response;
	}

}
