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
	 * @pre number must not be null
	 * @param number string of the ItemBarcode number to validate
	 * @return true if valid number
	 */
	@Override
	public boolean isValid(String number)
	{
		boolean response = false;

		if(number.matches("4[0-9]{11}"))
			response = true;

		return response;
	}

}
