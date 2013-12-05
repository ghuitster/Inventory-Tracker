
package model;

import model.visitor.IVisitor;

/**
 * A class to represent a ProductGroup
 * @author David
 */
public class ProductGroup extends ProductContainer implements IProductGroup
{
	private static final long serialVersionUID = 4647265154277890697L;
	private IProductContainer container;
	private Amount threeMonthSupply;

	/**
	 * @pre container must be a valid ProductContainer and not null
	 * @pre threeMonthSupply must be a valid ThreeMonthSupply and not null
	 * @param container
	 * @param threeMonthSupply
	 */
	public ProductGroup(String name, Amount threeMonthSupply)
	{
		super(name);
		this.threeMonthSupply = threeMonthSupply;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductGroup#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!super.equals(obj))
			return false;
		if(getClass() != obj.getClass())
			return false;
		ProductGroup other = (ProductGroup) obj;
		if(container == null)
		{
			if(other.container != null)
				return false;
		}
		else if(!container.equals(other.container))
			return false;
		if(threeMonthSupply == null)
		{
			if(other.threeMonthSupply != null)
				return false;
		}
		else if(!threeMonthSupply.equals(other.threeMonthSupply))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductGroup#getContainer()
	 */
	@Override
	public IProductContainer getContainer()
	{
		return this.container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductGroup#getThreeMonthSupply()
	 */
	@Override
	public Amount getThreeMonthSupply()
	{
		return this.threeMonthSupply;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductGroup#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result =
				prime * result
						+ ((container == null) ? 0 : container.hashCode());
		result =
				prime
						* result
						+ ((threeMonthSupply == null) ? 0 : threeMonthSupply
								.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductGroup#setContainer(model.IProductContainer)
	 */
	@Override
	public void setContainer(IProductContainer container)
	{
		this.container = container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductGroup#setThreeMonthSupply(model.ThreeMonthSupply)
	 */
	@Override
	public void setThreeMonthSupply(Amount threeMonthSupply)
	{
		this.threeMonthSupply = threeMonthSupply;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductGroup#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductGroup [container=" + this.container
				+ ", threeMonthSupply=" + this.threeMonthSupply + "]";
	}

	@Override
	public void traverse(IVisitor visitor)
	{
		visitor.visitProductGroup(this);
		super.visitChildren(visitor);

	}
}
