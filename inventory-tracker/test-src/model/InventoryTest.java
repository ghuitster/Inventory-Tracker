package model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
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
		assertTrue(Inventory.getInstance() != null);
		assertEquals(Inventory.getInstance().getAllStorageUnits().size(), 0);
		assertEquals(Inventory.getInstance().getAllProducts().size(), 0);
		assertEquals(Inventory.getInstance().getItemExpirations().size(), 0);
		assertEquals(Inventory.getInstance().getNMonthSupplyGroupMap().size(), 0);
		assertEquals(Inventory.getInstance().getNMonthSupplyMap().size(), 0);
		assertEquals(Inventory.getInstance().getRemovedItems().size(), 0);
	}
	
	@Test
	public void storageUnitTest() throws Exception
	{
		assertEquals(Inventory.getInstance().getAllStorageUnits().size(), 0);
		populateInventory();
		Set<StorageUnit> units = Inventory.getInstance().getAllStorageUnits(); 
		assertEquals(units.size(), 2);
		assertTrue(units.contains(unit1));
		assertTrue(units.contains(unit2));
	}
	
	@Test
	public void productListTest() throws Exception
	{
		populateInventory();
		Set<Product> products = Inventory.getInstance().getAllProducts();
		assertTrue(products.contains(prod));
	}

	private void populateInventory() throws InvalidNameException
	{
		Inventory.getInstance().removeAllStorageUnits();
		unit1 = new StorageUnit("unit1");
		ProductGroup pg1 = new ProductGroup("pg1", unit1, new ThreeMonthSupply(1.0f, UnitType.CHEVROLET));
		HashSet<ProductContainer> set = new HashSet<ProductContainer>();
		set.add(pg1);
		prod = new Product(new Date(), "asdf", new Barcode("1"), new CountUnitSize(1), 1, new CountThreeMonthSupply(1), set);
		pg1.addProduct(prod);
		unit1.addProductGroup(pg1);
		Inventory.getInstance().addStorageUnit(unit1);
		unit2 = new StorageUnit("unit2");
		Inventory.getInstance().addStorageUnit(unit2);
	}
	
	@Test
	public void removeTest() throws Exception
	{
		populateInventory();
		Set<StorageUnit> units = Inventory.getInstance().getAllStorageUnits(); 
		assertEquals(units.size(), 2);
		Inventory.getInstance().removeStorageUnit(unit1);
		units = Inventory.getInstance().getAllStorageUnits();
		assertEquals(units.size(), 1);
		Inventory.getInstance().removeAllStorageUnits();
		units = Inventory.getInstance().getAllStorageUnits();
		assertEquals(units.size(), 0);
	}
	
	@Test
	public void persistenceTest() throws Exception
	{
		populateInventory();
		Inventory.getInstance().getPersistence().saveData();
		Inventory.getInstance().removeAllStorageUnits();
		Inventory.getInstance().getPersistence().loadData();
		assertEquals(Inventory.getInstance().getAllStorageUnits().size(), 2);
		assertEquals(Inventory.getInstance().getAllProducts().size(), 1);
		assertTrue(Inventory.getInstance().getAllProducts().iterator().next().getDescription().equals("asdf"));
	}
	
	@Test
	public void reportDeletedItemTest() throws Exception
	{
		populateInventory();
		assertEquals(Inventory.getInstance().getRemovedItems().size(), 0);
		Inventory.getInstance().reportRemovedItem(new Item(prod, new Barcode("1"), new Date(), new Date(), new Date(), unit1));
		assertEquals(Inventory.getInstance().getRemovedItems().size(), 1);
	}

}
