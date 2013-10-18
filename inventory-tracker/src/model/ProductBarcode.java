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
	@Override
	public boolean isValid(String number)
	{
		boolean response = false;

		if(number.matches("[0-9]{12}"))
			response = true;

		return response;
	}

}
