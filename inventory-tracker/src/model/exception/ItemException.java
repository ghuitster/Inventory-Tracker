package model.exception;

/**
 * A general exception for an error that happens in Item class
 * @author chris
 *
 */

public class ItemException extends Exception
{
	/**
	 * Constructor that takes in a message
	 * @param message
	 */
	public ItemException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructor that does not take a message
	 */
	public ItemException()
	{
		
	}
}
