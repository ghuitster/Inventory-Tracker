
package model;

/**
 * A class representing a Storage Unit
 * @author David
 */
public class StorageUnit extends ProductContainer
{
	/**
	 * @precondition name must be a valid String and not null
	 * @postcondition my.name == passed in name
	 * @param name the name to attempt to set
	 * @return whether the name can be set or not
	 */
	public boolean ableToSetName(String name)
	{
		return true;
	}
}
