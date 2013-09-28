package model;

/**
 * This is a general Exception for the Product class
 * @author chris
 *
 */

public class ProductException extends Exception
{
	/**
	 * Constructor that takes a message
	 * @param message
	 */
	public ProductException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructor that doesn't take a message
	 */
	public ProductException()
	{
		
	}
}
