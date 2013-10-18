
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
		BaseProductContainer container =
				new ProductGroup("test", unit, new ThreeMonthSupply(1,
						UnitType.FLUID_OUNCES));

		assertTrue(container.ableToAddItem(new Item(null, null, null, null,
				null, unit)));
	}

	@Test
	public void testAbleToAddProduct()
	{
		StorageUnit unit = new StorageUnit("test");
		BaseProductContainer container =
				new ProductGroup("test", unit, new ThreeMonthSupply(1,
						UnitType.FLUID_OUNCES));
		BaseProduct product =
				new Product(new Date(), "test", new Barcode("400000000000"),
						new CountUnitSize(3), 3, null);
		assertTrue(container.ableToAddProduct(product));
		Set<Product> products = container.getAllProducts();

		for(BaseProduct prod: products)
			assertFalse(container.ableToAddProduct(prod));
	}

	@Test
	public void testAbleToAddProductGroup()
	{
		StorageUnit unit = new StorageUnit("test");
		BaseProductContainer container =
				new ProductGroup("test", unit, new ThreeMonthSupply(1,
						UnitType.FLUID_OUNCES));
		IProductGroup group =
				new ProductGroup("test", container, new ThreeMonthSupply(3,
						UnitType.FLUID_OUNCES));
		assertTrue(container.ableToAddProductGroup(group));
		Set<ProductGroup> groups = container.getAllProductGroups();

		for(IProductGroup gro: groups)
			assertFalse(container.ableToAddProductGroup(gro));
	}

	@Test
	public void testAbleToRemoveItem()
	{
		StorageUnit unit = new StorageUnit("test");
		BaseProductContainer container =
				new ProductGroup("test", unit, new ThreeMonthSupply(1,
						UnitType.FLUID_OUNCES));

		assertTrue(container.ableToRemoveItem(new Item(null, null, null, null,
				null, unit)));
	}

	@Test
	public void testAbleToRemoveProduct()
	{
		StorageUnit unit = new StorageUnit("test");
		BaseProductContainer container =
				new ProductGroup("test", unit, new ThreeMonthSupply(1,
						UnitType.FLUID_OUNCES));

		Product product =
				new Product(new Date(), "test", new Barcode("400000000000"),
						new CountUnitSize(3), 3, null);
		container.addProduct(product);
		Item item = new Item(product, null, null, null, null, unit);
		for(Product prod: container.getAllProducts())
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
		BaseProductContainer container =
				new ProductGroup("test", unit, new ThreeMonthSupply(1,
						UnitType.FLUID_OUNCES));
		Product product =
				new Product(new Date(), "test", new Barcode("400000000000"),
						new CountUnitSize(3), 3, null);
		ProductGroup group =
				new ProductGroup("test", container, new ThreeMonthSupply(3,
						UnitType.FLUID_OUNCES));
		group.addItem(new Item(product, new Barcode("400000000000"),
				new Date(), new Date(), new Date(), unit));
		assertFalse(container.ableToRemoveProductGroup(group));
	}

	@Test
	public void testGetAllItems()
	{
		StorageUnit unit = new StorageUnit("test");
		BaseProductContainer container =
				new ProductGroup("test", unit, new ThreeMonthSupply(1,
						UnitType.FLUID_OUNCES));

		assertFalse(container.getAllItems() == null);
	}

	@Test
	public void testGetAllProductGroups()
	{
		StorageUnit unit = new StorageUnit("test");
		BaseProductContainer container =
				new ProductGroup("test", unit, new ThreeMonthSupply(1,
						UnitType.FLUID_OUNCES));

		assertFalse(container.getAllProductGroups() == null);
	}

	@Test
	public void testGetAllProducts()
	{
		StorageUnit unit = new StorageUnit("test");
		BaseProductContainer container =
				new ProductGroup("test", unit, new ThreeMonthSupply(1,
						UnitType.FLUID_OUNCES));

		assertFalse(container.getAllProducts() == null);
	}
}
