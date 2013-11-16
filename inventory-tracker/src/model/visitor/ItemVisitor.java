
package model.visitor;

import java.util.SortedSet;
import java.util.TreeSet;

import model.IItem;
import model.IProduct;
import model.IProductGroup;
import model.IStorageUnit;

public class ItemVisitor implements IVisitor
{
	private SortedSet<IItem> results;
	private IProduct filter;

	public ItemVisitor()
	{
		results = new TreeSet<IItem>();
	}

	public ItemVisitor(IProduct filter)
	{
		results = new TreeSet<IItem>();
		this.filter = filter;
	}

	public SortedSet<IItem> getResults()
	{
		return new TreeSet<IItem>(results);
	}

	@Override
	public void visitItem(IItem item)
	{
		if(this.filter == null || item.getProduct() == this.filter)
			this.results.add(item);
	}

	@Override
	public void visitProduct(IProduct product)
	{}

	@Override
	public void visitProductGroup(IProductGroup group)
	{}

	@Override
	public void visitStorageUnit(IStorageUnit unit)
	{}
}
