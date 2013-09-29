
package model.exception;

/**
 * This is a general Exception for the Product class
 * @author chris
 * 
 */

public class ProductException extends Exception
{
	/**
	 * Constructor that doesn't take a message
	 */
	public ProductException()
	{

	}

	/**
	 * Constructor that takes a message
	 * @param message
	 */
	public ProductException(String message)
	{
		super(message);
	}
}
