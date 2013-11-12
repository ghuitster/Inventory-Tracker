
package model.exception;

/**
 * An Exception if there is an invalid Unit Size
 * @author chris
 * 
 */

public class InvalidUnitTypeException extends Exception
{
	/**
	 * Constructor for no message
	 */
	public InvalidUnitTypeException()
	{

	}

	/**
	 * Constructor for a detail message of error
	 * @param message
	 */
	public InvalidUnitTypeException(String message)
	{
		super(message);
	}
}
