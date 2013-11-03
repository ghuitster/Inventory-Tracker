package model.visitor;

import java.util.List;
import java.util.Map;
import model.IItem;
import model.IProduct;
import model.IProductGroup;
import model.IStorageUnit;
import model.IProductContainer;

/**
 * Visitor which searches for product groups with inconsistent products
 * @author Brian
 *
 */
public class NoticeVisitor implements IVisitor
{

	public Map<IProductContainer, List<IProduct>> result;
	
	/**
	 * Returns the result of the traversal
	 * @pre The traversal has been run
	 * @post none
	 * @return
	 */
	public Map<IProductContainer, List<IProduct>> getResult()
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
