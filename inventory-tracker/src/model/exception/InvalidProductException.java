
package model.exception;

/**
 * A class if there is an invalid product request
 * @author chris
 * 
 */

public class InvalidProductException extends Exception
{
	/***
	 * Constructor for no message.
	 */
	public InvalidProductException()
	{}

	/**
	 * constructor to display a detail message
	 * @param message
	 */
	public InvalidProductException(String message)
	{
		super(message);
	}
}
