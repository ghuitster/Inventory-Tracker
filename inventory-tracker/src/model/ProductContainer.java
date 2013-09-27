
package model;

import java.util.Set;

/**
 * @author David
 * 
 */
public abstract class ProductContainer
{
	protected String name;
	protected Set<Product> products;
	protected Set<Item> items;
	protected Set<ProductGroup> productGroups;

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
	 * @return the name
	 */
	public String getName()
	{
		return name;
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
}
