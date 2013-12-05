
package model;

import java.util.List;

public class DatabaseAccess
{
	
	public DatabaseAccess(String databaseName)
	{
		
	}
	
	/**
	 * Adds an item to the database
	 * @param item The item to add
	 */
	public void addItem(IItem item)
	{

	}

	/**
	 * Adds a product to the database
	 * @param product The product to add
	 */
	public void addProduct(IProduct product)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Adds a product container to the database
	 * @param productContainer The product container to add
	 */
	public void addProductContainer(IProductContainer productContainer)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets the complete Product Container tree from the database. This will go
	 * get EVERYTHING from the database and build the in memory representation
	 */
	public List<IStorageUnit> getAllStorageUnits()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes an item by id from the database
	 * @param item The item to remove
	 */
	public void removeItem(IItem item)
	{

	}

	/**
	 * Removes a product by id from the database
	 * @param product The product to remove
	 */
	public void removeProduct(IProduct product)
	{

	}

	/**
	 * Removes a product container by id from the database
	 * @param container The container to remove
	 */
	public void removeProductContainer(IProductContainer container)
	{

	}

	/**
	 * Updates an item in the database
	 */
	public void updateItem(IItem item)
	{

	}

	/**
	 * Updates a product container in the database
	 * @param productContainer
	 */
	public void updateProductContainer(IProductContainer productContainer)
	{

	}

	/**
	 * Updates a product in the database
	 * @param product
	 */
	public void updateProdut(IProduct product)
	{

	}
}
