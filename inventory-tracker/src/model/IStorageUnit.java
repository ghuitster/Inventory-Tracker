
package model;

import model.visitor.IVisitor;

public interface IStorageUnit extends IProductContainer
{

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();
	
}
