package model.exception;

import javax.naming.NamingException;

/**
 * A class if there is an invalid name
 * @author chris
 *
 */

public class InvalidNameException extends NamingException
{
	/**
	 * Constructor to display a detail message
	 * @param message
	 */
	public InvalidNameException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructor for no detail message
	 */
	public InvalidNameException()
	{
		
	}
}
