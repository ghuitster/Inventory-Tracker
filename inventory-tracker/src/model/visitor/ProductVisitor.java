
package model.visitor;

import java.util.SortedSet;
import java.util.TreeSet;

import model.IItem;
import model.IProduct;
import model.IProductGroup;
import model.IStorageUnit;

public class ProductVisitor implements IVisitor
{
	private SortedSet<IProduct> results;

	public ProductVisitor()
	{
		results = new TreeSet<IProduct>();
	}

	public SortedSet<IProduct> getResults()
	{
		return new TreeSet<IProduct>(results);
	}

	@Override
	public void visitItem(IItem item)
	{}

	@Override
	public void visitProduct(IProduct product)
	{
		results.add(product);
	}

	@Override
	public void visitProductGroup(IProductGroup group)
	{}

	@Override
	public void visitStorageUnit(IStorageUnit unit)
	{}
}
