
package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Set;

import org.junit.Test;

import common.util.DateUtils;

public class ProductContainerTest
{
	@Test
	public void testAbleToAddItem()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));

		assertTrue(container.ableToAddItem(new Item(null, null, null, null)));
	}

	@Test
	public void testAbleToAddProduct()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));
		IProduct product =
				new Product(new Date(), "test", new ProductBarcode(
						"400000000000"), new CountUnitSize(3), 3, null);
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
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));
		IProductGroup group =
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));
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
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));

		assertTrue(container.ableToRemoveItem(new Item(null, null, null, null)));
	}

	@Test
	public void testAbleToRemoveProduct()
	{
		StorageUnit unit = new StorageUnit("test");
		ProductGroup container =
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));
		container.setContainer(unit);

		IProduct product =
				new Product(new Date(), "test", new ProductBarcode(
						"400000000000"), new CountUnitSize(3), 3, null);
		container.addProduct(product);
		Item item = new Item(product, new ItemBarcode(Inventory.getInstance()
				.getUniqueBarCode() + ""), DateUtils.currentDate(), null);
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
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));
		Product product =
				new Product(new Date(), "test", new ProductBarcode(
						"400000000000"), new CountUnitSize(3), 3, null);
		ProductGroup group =
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));
		group.addItem(new Item(product, new ProductBarcode("400000000000"),
				new Date(), new Date()));
		assertFalse(container.ableToRemoveProductGroup(group));
	}

	@Test
	public void testGetAllItems()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));

		assertFalse(container.getAllItems() == null);
	}

	@Test
	public void testGetAllProductGroups()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));

		assertFalse(container.getAllProductGroups() == null);
	}

	@Test
	public void testGetAllProducts()
	{
		StorageUnit unit = new StorageUnit("test");
		IProductContainer container =
				new ProductGroup("test", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));

		assertFalse(container.getAllProducts() == null);
	}
}
