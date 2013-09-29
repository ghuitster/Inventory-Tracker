
package model.exception;

/**
 * An Exception if there is an error for Serialzer
 * @author chris
 * 
 */

public class SerialzerException extends Exception
{
	/**
	 * Constructor for no message
	 */
	public SerialzerException()
	{

	}

	/**
	 * Constructor for detail error message
	 * @param message
	 */
	public SerialzerException(String message)
	{
		super(message);
	}
}
