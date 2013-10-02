
package model;

/**
 * A class representing a Storage Unit
 * @author David
 */
public class StorageUnit extends ProductContainer
{
	public StorageUnit(String name)
	{
		super(name);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StorageUnit [name=" + name + ", products=" + products
				+ ", items=" + items + ", productGroups=" + productGroups + "]";
	}
}
