
package model;

import java.util.Set;

/**
 * A class to represent a ProductGroup
 * @author David
 */
public class ProductGroup extends ProductContainer
{
	private ProductContainer container;
	private ThreeMonthSupply threeMonthSupply;

	/**
	 * @param name
	 * @param products
	 * @param items
	 * @param productGroups
	 * @param container
	 * @param threeMonthSupply
	 */
	public ProductGroup(String name, Set<Product> products, Set<Item> items,
			Set<ProductGroup> productGroups, ProductContainer container,
			ThreeMonthSupply threeMonthSupply)
	{
		super(name, products, items, productGroups);
		this.container = container;
		this.threeMonthSupply = threeMonthSupply;
	}

	/**
	 * @param container the ProductContainer to attempt to set
	 * @return if the container can be set or not
	 */
	public boolean ableToSetContainer(ProductContainer container)
	{
		return true;
	}

	/**
	 * @param threeMonthSupply the ThreeMonthSupply to attempt to set
	 * @return if the threeMonthSupply can be set or not
	 */
	public boolean ableToSetThreeMonthSupply(ThreeMonthSupply threeMonthSupply)
	{
		return true;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
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

	/**
	 * @return the container
	 */
	public ProductContainer getContainer()
	{
		return container;
	}

	/**
	 * @return the threeMonthSupply
	 */
	public ThreeMonthSupply getThreeMonthSupply()
	{
		return threeMonthSupply;
	}

	/**
	 * @see java.lang.Object#hashCode()
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

	/**
	 * @param container the ProductContainer to set
	 */
	public void setContainer(ProductContainer container)
	{
		this.container = container;
	}

	/**
	 * @param threeMonthSupply the ThreeMonthSupply to set
	 */
	public void setThreeMonthSupply(ThreeMonthSupply threeMonthSupply)
	{
		this.threeMonthSupply = threeMonthSupply;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductGroup [container=" + container + ", threeMonthSupply="
				+ threeMonthSupply + "]";
	}
}
