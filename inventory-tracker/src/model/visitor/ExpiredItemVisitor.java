package model.visitor;

import java.util.List;

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
	
	private List<IItem> result;
	
	/**
	 * Returns the result of the traversal
	 * @pre The traversal has been run
	 * @post none
	 * @return
	 */
	public List<IItem> getResult()
	{
		
	}

	@Override
	public void visitStorageUnit(IStorageUnit unit)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitProductGroup(IProductGroup group)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitProduct(IProduct product)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitItem(IItem item)
	{
		// TODO Auto-generated method stub
		
	}

}
