
package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Set;

import model.exception.InvalidNameException;

import org.junit.Test;

public class InventoryTest
{

	private StorageUnit unit1;
	private StorageUnit unit2;
	private Product prod;

	@Test
	public void constructorTest()
	{
		Inventory.setInstance(null);
		assertTrue(Inventory.getInstance() != null);
		assertEquals(0, Inventory.getInstance().getAllStorageUnits().size());
		assertEquals(0, Inventory.getInstance().getAllProducts().size());
//		assertEquals(0, Inventory.getInstance().getItemExpirations().size());
//		assertEquals(0, Inventory.getInstance().getNMonthSupplyGroupMap()
//				.size());
//		assertEquals(0, Inventory.getInstance().getNMonthSupplyMap().size());
//		assertEquals(0, Inventory.getInstance().getRemovedItems().size());
	}

	@Test
	public void persistenceTest() throws Exception
	{
		populateInventory();
		Inventory.getInstance().getPersistence().saveData();
		Inventory.getInstance().removeAllStorageUnits();
		Inventory.getInstance().getPersistence().loadData();
		assertEquals(2, Inventory.getInstance().getAllStorageUnits().size());
		assertEquals(1, Inventory.getInstance().getAllProducts().size());
		assertTrue(Inventory.getInstance().getAllProducts().iterator().next()
				.getDescription().equals(new ProductDescription("asdf")));
	}

	private void populateInventory() throws InvalidNameException
	{
		Inventory.getInstance().removeAllStorageUnits();
		unit1 = new StorageUnit("unit1");
		ProductGroup pg1 =
				new ProductGroup("pg1", new ThreeMonthSupply(1.0f,
						UnitType.CHEVROLET));
		prod =
				new Product(new Date(), "asdf", new ProductBarcode("1"),
						new CountUnitSize(1), 1, new CountThreeMonthSupply(1));
		pg1.addProduct(prod);
		unit1.addProductGroup(pg1);
		Inventory.getInstance().addStorageUnit(unit1);
		unit2 = new StorageUnit("unit2");
		Inventory.getInstance().addStorageUnit(unit2);
	}

	@Test
	public void productListTest() throws Exception
	{
		populateInventory();
		Set<IProduct> products = Inventory.getInstance().getAllProducts();
		assertEquals(1, products.size());
		assertTrue(products.contains(prod));
	}

	@Test
	public void removeTest() throws Exception
	{
		populateInventory();
		Set<IStorageUnit> units = Inventory.getInstance().getAllStorageUnits();
		assertEquals(2, units.size());
		Inventory.getInstance().removeStorageUnit(unit1);
		units = Inventory.getInstance().getAllStorageUnits();
		assertEquals(1, units.size());
		Inventory.getInstance().removeAllStorageUnits();
		units = Inventory.getInstance().getAllStorageUnits();
		assertEquals(0, units.size());
	}

	@Test
	public void storageUnitTest() throws Exception
	{
		Inventory.getInstance().removeAllStorageUnits();
		assertEquals(0, Inventory.getInstance().getAllStorageUnits().size());
		populateInventory();
		Set<IStorageUnit> units = Inventory.getInstance().getAllStorageUnits();
		assertEquals(2, units.size());
		assertTrue(units.contains(unit1));
		assertTrue(units.contains(unit2));
	}

}
