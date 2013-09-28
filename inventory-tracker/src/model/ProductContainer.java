
package model;

import java.util.Set;

/**
 * @author David
 * 
 *         A class to represent a product container
 * @author David
 */
public abstract class ProductContainer
{
	protected String name;
	protected Set<Product> products;
	protected Set<Item> items;
	protected Set<ProductGroup> productGroups;

	/**
	 * @precondition item must be a valid Item and not null
	 * @param item the Item to attempt to add
	 * @return whether the Item can be added or not
	 */
	public boolean ableToAddItem(Item item)
	{
		return true;
	}

	/**
	 * @precondition product must be a valid Product and not null
	 * @param product the Product to attempt to add
	 * @return whether the Product can be added or not
	 */
	public boolean ableToAddProduct(Product product)
	{
		return true;
	}

	/**
	 * @precondition productGroup must be a valid ProductGroup and not null
	 * @param productGroup the ProductGroup to attempt to add
	 * @return whether the ProductGroup can be added or not
	 */
	public boolean ableToAddProductGroup(ProductGroup productGroup)
	{
		return true;
	}

	/**
	 * @precondition item must be a valid Item and not null
	 * @param item the Item to attempt to remove
	 * @return whether the Item can be removed or not
	 */
	public boolean ableToRemoveItem(Item item)
	{
		return true;
	}

	/**
	 * @precondition product must be a valid Product and not null
	 * @param product the Product to attempt to remove
	 * @return whether the Product can be removed or not
	 */
	public boolean ableToRemoveProduct(Product product)
	{
		return true;
	}

	/**
	 * @precondition productGroup must be a valid ProductGroup and not null
	 * @param productGroup the ProductGroup to attempt to remove
	 * @return whether the ProductGroup can be removed or not
	 */
	public boolean ableToRemoveProductGroup(ProductGroup productGroup)
	{
		return true;
	}

	/**
	 * @precondition item must be a valid Item and not null
	 * @postcondition my.items.contains(item)
	 * @param item the Item to add
	 */
	public void addItem(Item item)
	{

	}

	/**
	 * @precondition product must be a valid Product and not null
	 * @postcondition my.products.contains(product)
	 * @param product the Product to add
	 */
	public void addProduct(Product product)
	{

	}

	/**
	 * @precondition productGroup must be a valid ProductGroup and not null
	 * @postcondition my.productGroups.contains(productGroup)
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
	 * @precondition item must be a valid Item and not null
	 * @postcondition my.items.doesNotContain(item)
	 * @param item the Item to remove
	 */
	public void removeItem(Item item)
	{

	}

	/**
	 * @precondition product must be a valid Product and not null
	 * @postcondition my.products.doesNotContain(product)
	 * @param product the Product to remove
	 */
	public void removeProduct(Product product)
	{

	}

	/**
	 * @precondition productGroup must be a valid ProductGroup and not null
	 * @postcondition my.productGroups.doesNotContain(productGroup)
	 * @param productGroup the ProductGroup to remove
	 */
	public void removeProductGroup(ProductGroup productGroup)
	{

	}

	/**
	 * @precondition name must be a valid String and not null
	 * @postcondition my.name == name
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @precondition item must be a valid item and not null
	 * @precondition otherProductContainer must be a valid ProductContainer and
	 *               not null
	 * @postcondition my.items.doesNotContain(item)
	 * @postcondition otherProductContainer.items.contains(item)
	 * @param item the Item to transfer
	 * @param otherProductContainer the target ProductContainer
	 */
	public void transferItem(Item item, ProductContainer otherProductContainer)
	{

	}
}
