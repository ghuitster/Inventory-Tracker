
package model;

import model.visitor.IVisitor;

/**
 * A class representing a Storage Unit
 * @author David
 */
public class StorageUnit extends ProductContainer implements IStorageUnit
{
	private static final long serialVersionUID = -4003256091489850639L;

	public StorageUnit(String name)
	{
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IStorageUnit#toString()
	 */
	@Override
	public String toString()
	{
		return "StorageUnit [name=" + name + ", products=" + products
				+ ", items=" + items + ", productGroups=" + productGroups + "]";
	}
	
	public void traverse(IVisitor visitor)
	{
		visitor.visitStorageUnit(this);
		super.visitChildren(visitor);
	}
}
