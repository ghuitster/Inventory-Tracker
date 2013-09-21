
package model;

import java.util.Set;

/**
 * @author David A class to represent a product container
 */
public class ProductContainer
{
	private String name;
	private Set<Product> products;
	private Set<Item> items;
	private Set<ProductGroup> productGroups;

	/**
	 * @param name
	 * @param products
	 * @param items
	 * @param productGroups
	 */
	public ProductContainer(String name, Set<Product> products,
			Set<Item> items, Set<ProductGroup> productGroups)
	{
		super();
		this.name = name;
		this.products = products;
		this.items = items;
		this.productGroups = productGroups;
	}

	/**
	 * 
	 * @param item the Item to attempt to add
	 * @return whether the Item can be added or not
	 */
	public boolean ableToAddItem(Item item)
	{
		return true;
	}

	/**
	 * 
	 * @param product the Product to attempt to add
	 * @return whether the Product can be added or not
	 */
	public boolean ableToAddProduct(Product product)
	{
		return true;
	}

	/**
	 * 
	 * @param productGroup the ProductGroup to attempt to add
	 * @return whether the ProductGroup can be added or not
	 */
	public boolean ableToAddProductGroup(ProductGroup productGroup)
	{
		return true;
	}

	/**
	 * 
	 * @param item the Item to attempt to remove
	 * @return whether the Item can be removed or not
	 */
	public boolean ableToRemoveItem(Item item)
	{
		return true;
	}

	/**
	 * 
	 * @param product the Product to attempt to remove
	 * @return whether the Product can be removed or not
	 */
	public boolean ableToRemoveProduct(Product product)
	{
		return true;
	}

	/**
	 * 
	 * @param productGroup the ProductGroup to attempt to remove
	 * @return whether the ProductGroup can be removed or not
	 */
	public boolean ableToRemoveProductGroup(ProductGroup productGroup)
	{
		return true;
	}

	/**
	 * @return if the name can be set or not
	 * @param name the name to attempt to set
	 */
	public boolean ableToSetName(String name)
	{
		return true;
	}

	/**
	 * 
	 * @param item the Item to add
	 */
	public void addItem(Item item)
	{

	}

	/**
	 * 
	 * @param product the Product to add
	 */
	public void addProduct(Product product)
	{

	}

	/**
	 * 
	 * @param productGroup the ProductGroup to add
	 */
	public void addProductGroup(ProductGroup productGroup)
	{

	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		ProductContainer other = (ProductContainer) obj;
		if(items == null)
		{
			if(other.items != null)
				return false;
		}
		else if(!items.equals(other.items))
			return false;
		if(name == null)
		{
			if(other.name != null)
				return false;
		}
		else if(!name.equals(other.name))
			return false;
		if(productGroups == null)
		{
			if(other.productGroups != null)
				return false;
		}
		else if(!productGroups.equals(other.productGroups))
			return false;
		if(products == null)
		{
			if(other.products != null)
				return false;
		}
		else if(!products.equals(other.products))
			return false;
		return true;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result =
				prime
						* result
						+ ((productGroups == null) ? 0 : productGroups
								.hashCode());
		result =
				prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}

	/**
	 * 
	 * @param item the Item to remove
	 */
	public void removeItem(Item item)
	{

	}

	/**
	 * 
	 * @param product the Product to remove
	 */
	public void removeProduct(Product product)
	{

	}

	/**
	 * 
	 * @param productGroup the ProductGroup to remove
	 */
	public void removeProductGroup(ProductGroup productGroup)
	{

	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductContainer [name=" + name + ", products=" + products
				+ ", items=" + items + ", productGroups=" + productGroups + "]";
	}

}
