
package model.visitor;

import java.util.ArrayList;
import java.util.List;

import common.util.DateUtils;

import model.IItem;
import model.IProduct;
import model.IProductGroup;
import model.IStorageUnit;

/**
 * Visitor which searches for expired items
 * @author Brian
 * 
 */
public class ExpiredItemVisitor implements IVisitor
{

	private List<IItem> results;

	public ExpiredItemVisitor()
	{
		results = new ArrayList<IItem>();
	}
	
	/**
	 * Returns the result of the traversal
	 * @pre The traversal has been run
	 * @post none
	 * @return
	 */
	public List<IItem> getResult()
	{
		return new ArrayList<IItem>(results);
	}

	/**
	 * Checks if the passed item is expired. If so, adds it to the results
	 * @pre item is not null
	 * 		An item is not visited more than once by this visitor
	 * 		The item is currently in the system (no exit date)
	 * @post none
	 * @param item The item to check
	 */
	@Override
	public void visitItem(IItem item)
	{
		if(item.getExpirationDate() != null &&
				item.getExpirationDate().compareTo(DateUtils.currentDate()) < 0)
		{
			results.add(item);
		}
	}

	/**
	 * Does nothing
	 * @pre none
	 * @post none
	 */
	@Override
	public void visitProduct(IProduct product)
	{ }

	/**
	 * Does nothing
	 * @pre none
	 * @post none
	 */
	@Override
	public void visitProductGroup(IProductGroup group)
	{ }

	/**
	 * Does nothing
	 * @pre none
	 * @post none
	 */
	@Override
	public void visitStorageUnit(IStorageUnit unit)
	{ }

}
