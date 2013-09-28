package model.exception;

/**
 * An Exception if there is an invalid Unit Size
 * @author chris
 *
 */

public class InvalidUnitSizeException extends Exception
{
	/**
	 * Constructor for a detail message of error
	 * @param message
	 */
	public InvalidUnitSizeException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructor for no message
	 */
	public InvalidUnitSizeException()
	{
		
	}
}
