
package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ProductContainerTest
{
	@Test
	public void testAbleToAddItem()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test");

		assertTrue(container.ableToAddItem(new Item(null, null, null, null)));
	}

	@Test
	public void testAbleToAddProduct()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test");
		IProduct product =
				new Product(new Date(), "test", new ProductBarcode("400000000000"),
						new CountUnitSize(3), 3, null);
		assertTrue(container.ableToAddProduct(product));
		Set<IProduct> products = container.getAllProducts();

		for(IProduct prod: products)
			assertFalse(container.ableToAddProduct(prod));
	}

	@Test
	public void testAbleToAddProductGroup()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test");
		IProductGroup group =
				new ProductGroup("test");
		assertTrue(container.ableToAddProductGroup(group));
		Set<IProductGroup> groups = container.getAllProductGroups();

		for(IProductGroup gro: groups)
			assertFalse(container.ableToAddProductGroup(gro));
	}

	@Test
	public void testAbleToRemoveItem()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test");

		assertTrue(container.ableToRemoveItem(new Item(null, null, null, null)));
	}

	@Test
	public void testAbleToRemoveProduct()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test");

		IProduct product =
				new Product(new Date(), "test", new ProductBarcode("400000000000"),
						new CountUnitSize(3), 3, null);
		container.addProduct(product);
		Item item = new Item(product, null, null, null);
		for(IProduct prod: container.getAllProducts())
		{
			product = prod;
			item.setProduct(product);
		}

		container.addItem(item);
		assertFalse(container.ableToRemoveProduct(product));

	}

	@Test
	public void testAbleToRemoveProductGroup()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test");
		Product product =
				new Product(new Date(), "test", new ProductBarcode("400000000000"),
						new CountUnitSize(3), 3, null);
		ProductGroup group =
				new ProductGroup("test");
		group.addItem(new Item(product, new ProductBarcode("400000000000"),
				new Date(), new Date()));
		assertFalse(container.ableToRemoveProductGroup(group));
	}

	@Test
	public void testGetAllItems()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test");

		assertFalse(container.getAllItems() == null);
	}

	@Test
	public void testGetAllProductGroups()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test");

		assertFalse(container.getAllProductGroups() == null);
	}

	@Test
	public void testGetAllProducts()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test");

		assertFalse(container.getAllProducts() == null);
	}
}
