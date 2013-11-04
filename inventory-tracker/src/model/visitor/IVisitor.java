
package model.visitor;

import model.IItem;
import model.IProduct;
import model.IProductGroup;
import model.IStorageUnit;

/**
 * Interface for implementing objects which traverse the inventory tree
 * @author Brian
 * 
 */
public interface IVisitor
{
	/**
	 * Called when an item is visited
	 * @pre item is not null
	 * @post none
	 * @param item The visited item
	 */
	void visitItem(IItem item);

	/**
	 * Called when a product is visited
	 * @pre product is not null
	 * @post none
	 * @param product The visited product
	 */
	void visitProduct(IProduct product);

	/**
	 * Called when a product group is visited
	 * @pre group is not null
	 * @post none
	 * @param group The visited product group
	 */
	void visitProductGroup(IProductGroup group);

	/**
	 * Called when a storage unit is visited
	 * @pre unit is not null
	 * @post none
	 * @param unit The visited storage unit
	 */
	void visitStorageUnit(IStorageUnit unit);
}
