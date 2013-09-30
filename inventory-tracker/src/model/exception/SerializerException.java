
package model.exception;

/**
 * An Exception if there is an error for Serializer
 * @author chris
 * 
 */

public class SerializerException extends Exception
{
	/**
	 * Constructor for no message
	 */
	public SerializerException()
	{

	}

	/**
	 * Constructor for detail error message
	 * @param message
	 */
	public SerializerException(String message)
	{
		super(message);
	}
}
