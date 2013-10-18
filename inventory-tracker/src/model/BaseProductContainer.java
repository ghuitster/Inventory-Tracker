
package model;

import java.util.Observable;
import java.util.Set;

public abstract class BaseProductContainer extends Observable implements ITaggable
{

	/**
	 * @pre item.barcode != empty
	 * @pre item.barcode is a valid UPC barcode
	 * @pre item.barcode is unique among all items
	 * @pre item.date != empty
	 * @pre item.date is not in the future
	 * @pre item.date is not before 1/1/2000
	 * @param item the Item to attempt to add
	 * @return whether the Item can be added or not
	 */
	public abstract boolean ableToAddItem(BaseItem item);

	/**
	 * @pre product.barcode != empty
	 * @pre product.description != empty
	 * @pre product.shelfLife > 0
	 * @pre product.threeMonthSupply > 0
	 * @pre product.unitSize > 0
	 * @param product the Product to attempt to add
	 * @return whether the Product can be added or not
	 */
	public abstract boolean ableToAddProduct(BaseProduct product);

	/**
	 * @pre productGroup.name != empty
	 * @pre productGroup.container != empty
	 * @param productGroup the ProductGroup to attempt to add
	 * @return whether the ProductGroup can be added or not
	 */
	public abstract boolean ableToAddProductGroup(IProductGroup productGroup);

	/**
	 * @pre this.items.contains(item)
	 * @param item the Item to attempt to remove
	 * @return whether the Item can be removed or not
	 */
	public abstract boolean ableToRemoveItem(BaseItem item);

	/**
	 * @pre this.products.contains(product)
	 * @param product the Product to attempt to remove
	 * @return whether the Product can be removed or not
	 */
	public abstract boolean ableToRemoveProduct(BaseProduct product);

	/**
	 * @pre productGroup must be a valid ProductGroup and not null
	 * @param productGroup the ProductGroup to attempt to remove
	 * @return whether the ProductGroup can be removed or not
	 */
	public abstract boolean ableToRemoveProductGroup(ProductGroup productGroup);

	/**
	 * @pre item must be a valid Item and not null
	 * @post my.items.contains(item)
	 * @param item the Item to add
	 */
	public abstract void addItem(Item item);

	/**
	 * @pre product must be a valid Product and not null
	 * @post my.products.contains(product)
	 * @param product the Product to add
	 */
	public abstract void addProduct(BaseProduct product);

	/**
	 * @pre productGroup must be a valid ProductGroup and not null
	 * @pre productGroup.name is unique in this ProductContainer
	 * @post my.productGroups.contains(productGroup)
	 * @param productGroup the ProductGroup to add
	 */
	public abstract void addProductGroup(ProductGroup productGroup);

	/**
	 * @pre this.items != null
	 * @return the Set<Item> of all Items
	 */
	public abstract Set<Item> getAllItems();

	/**
	 * @pre this.productGroups != null
	 * @return the Set<ProductGroup> of all ProductGroups
	 */
	public abstract Set<ProductGroup> getAllProductGroups();

	/**
	 * @pre this.products != null
	 * @return the Set<Product> of all Products
	 */
	public abstract Set<BaseProduct> getAllProducts();

	/**
	 * @return the name
	 */
	public abstract String getName();

	/**
	 * @pre item must be a valid Item and not null
	 * @post my.items.doesNotContain(item)
	 * @param item the Item to remove
	 */
	public abstract void removeItem(Item item);

	/**
	 * @pre product must be a valid Product and not null
	 * @pre my.items.doesNotContain(Items of this Product type)
	 * @post my.products.doesNotContain(product)
	 * @param product the Product to remove
	 */
	public abstract void removeProduct(BaseProduct product);

	/**
	 * @pre productGroup must be a valid ProductGroup and not null
	 * @post my.productGroups.doesNotContain(productGroup)
	 * @param productGroup the ProductGroup to remove
	 */
	public abstract void removeProductGroup(IProductGroup productGroup);

	/**
	 * @pre name must be a valid String and not null
	 * @post my.name == name
	 * @param name the name to set
	 */
	public abstract void setName(String name);

	/**
	 * @pre item must be a valid item and not null
	 * @pre otherProductContainer must be a valid ProductContainer and not null
	 * @post my.items.doesNotContain(item)
	 * @post otherProductContainer.items.contains(item)
	 * @param item the Item to transfer
	 * @param targetProductContainer the target ProductContainer
	 */
	public abstract void transferItem(Item item,
			ProductContainer targetContainer);

}
