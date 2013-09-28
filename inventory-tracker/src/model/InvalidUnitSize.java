package model;

/**
 * An Exception if there is an invalid Unit Size
 * @author chris
 *
 */

public class InvalidUnitSize extends Exception
{
	/**
	 * Constructor for a detail message of error
	 * @param message
	 */
	public InvalidUnitSize(String message)
	{
		super(message);
	}
	
	/**
	 * Constructor for no message
	 */
	public InvalidUnitSize()
	{
		
	}
}
