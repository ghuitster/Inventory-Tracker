
package model;

import java.util.SortedSet;

import model.visitor.IVisitor;

public interface IProductContainer extends IObservable, ITaggable,
		Comparable<IProductContainer>
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
	public abstract boolean ableToAddItem(IItem item);

	/**
	 * @pre product.barcode != empty
	 * @pre product.description != empty
	 * @pre product.shelfLife > 0
	 * @pre product.threeMonthSupply > 0
	 * @pre product.unitSize > 0
	 * @param product the Product to attempt to add
	 * @return whether the Product can be added or not
	 */
	public abstract boolean ableToAddProduct(IProduct product);

	/**
	 * @pre productGroup.name != empty
	 * @pre productGroup.container != empty
	 * @param productGroup the ProductGroup to attempt to add
	 * @return whether the ProductGroup can be added or not
	 */
	public abstract boolean ableToAddProductGroup(IProductGroup productGroup);

	public boolean ableToAddProductGroupNamed(String name);

	/**
	 * @pre this.items.contains(item)
	 * @param item the Item to attempt to remove
	 * @return whether the Item can be removed or not
	 */
	public abstract boolean ableToRemoveItem(IItem item);

	/**
	 * @pre this.products.contains(product)
	 * @param product the Product to attempt to remove
	 * @return whether the Product can be removed or not
	 */
	public abstract boolean ableToRemoveProduct(IProduct product);

	/**
	 * @pre productGroup must be a valid ProductGroup and not null
	 * @param productGroup the ProductGroup to attempt to remove
	 * @return whether the ProductGroup can be removed or not
	 */
	public abstract boolean
			ableToRemoveProductGroup(IProductGroup productGroup);

	/**
	 * @pre item must be a valid Item and not null
	 * @post my.items.contains(item)
	 * @param item the Item to add
	 */
	public abstract void addItem(IItem item);

	/**
	 * @pre product must be a valid Product and not null
	 * @post my.products.contains(product)
	 * @param product the Product to add
	 */
	public abstract void addProduct(IProduct product);

	/**
	 * @pre productGroup must be a valid ProductGroup and not null
	 * @pre productGroup.name is unique in this ProductContainer
	 * @post my.productGroups.contains(productGroup)
	 * @param productGroup the ProductGroup to add
	 */
	public abstract void addProductGroup(IProductGroup productGroup);

	/**
	 * Finds the container which contains the passed product
	 * @param product The product to search with
	 * @return The matching product container, or null if not found
	 */
	public abstract IProductContainer findContainer(IProduct product);

	/**
	 * @pre this.items != null
	 * @return the Set<Item> of all Items
	 */
	public abstract SortedSet<IItem> getAllItems();

	/**
	 * @pre this.productGroups != null
	 * @return the Set<ProductGroup> of all ProductGroups
	 */
	public abstract SortedSet<IProductGroup> getAllProductGroups();

	/**
	 * @pre this.products != null
	 * @return the Set<Product> of all Products
	 */
	public abstract SortedSet<IProduct> getAllProducts();

	/**
	 * A unique ID to represent this product container
	 * @return
	 */
	public abstract int getId();

	/**
	 * @return the name
	 */
	public abstract String getName();

	public abstract IStorageUnit getStorageUnit();

	/**
	 * @pre item must be a valid Item and not null
	 * @post my.items.doesNotContain(item)
	 * @param item the Item to remove
	 */
	public abstract void removeItem(IItem item);

	void removeItemTemporary(IItem item);

	/**
	 * @pre product must be a valid Product and not null
	 * @pre my.items.doesNotContain(Items of this Product type)
	 * @post my.products.doesNotContain(product)
	 * @param product the Product to remove
	 */
	public abstract void removeProduct(IProduct product);

	/**
	 * @pre productGroup must be a valid ProductGroup and not null
	 * @post my.productGroups.doesNotContain(productGroup)
	 * @param productGroup the ProductGroup to remove
	 */
	public abstract void removeProductGroup(IProductGroup productGroup);

	void removeProductTemporary(IProduct product);

	/**
	 * @pre name must be a valid String and not null
	 * @post my.name == name
	 * @param name the name to set
	 */
	public abstract void setName(String name);

	/**
	 * @pre item must be a valid item and not null
	 * @pre targetContainer must be a valid ProductContainer and not null
	 * @post this.items.Contains(item)
	 * @post targetContainer.items.contains(item)
	 * @param item the Item to transfer
	 * @param targetContainer the target ProductContainer
	 */
	public abstract void transferItem(IItem item,
			IProductContainer targetContainer);

	public void traverse(IVisitor visitor);

}
