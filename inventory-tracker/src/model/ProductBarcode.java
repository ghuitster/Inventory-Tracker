/**
 * 
 */

package model;

/**
 * @author Michael
 * 
 */
public class ProductBarcode extends Barcode
{
	private static final long serialVersionUID = -1451440044816950946L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Barcode(java.lang.String)
	 */
	public ProductBarcode(String number)
	{
		super(number);
	}

	/**
	 * @pre number must not be null
	 * @param number string of the ProductBarcode number to validate
	 * @return true if valid number
	 */
	public static boolean isValid(String number)
	{
		boolean response = false;

		if(number.matches("[0-9]{12}"))
			response = true;

		return response;
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
			response = ProductBarcode.isValid(number);
		}

		return response;
	}

}
