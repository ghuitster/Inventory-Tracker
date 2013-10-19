
package model;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ProductGroupTest
{

	@Test
	public void addChildTest()
	{
		ProductGroup group = createTestGroup();
		assertEquals(0, group.getAllProductGroups().size());
		ProductGroup child =
				new ProductGroup("Child group", new ThreeMonthSupply(1.0f, UnitType.CHEVROLET));
		group.addProductGroup(child);
		assertEquals(1, group.getAllProductGroups().size());
		assertEquals(child, group.getAllProductGroups().iterator().next());

	}

	@Test
	public void addItemTest()
	{
		ProductGroup group = createTestGroup();
		assertEquals(0, group.getAllItems().size());
		Item item = populateTestGroup(group);
		group.addItem(item);
		assertEquals(1, group.getAllItems().size());
		assertEquals(item, group.getAllItems().iterator().next());

	}

	@Test
	public void addProductTest()
	{
		ProductGroup group = createTestGroup();
		assertEquals(0, group.getAllProducts().size());
		IItem item = populateTestGroup(group);
		group.addProduct(item.getProduct());
		assertEquals(1, group.getAllProducts().size());
		assertEquals(item.getProduct(), group.getAllProducts().iterator()
				.next());

	}

	@Test
	public void constructorTest()
	{
		ProductGroup group = createTestGroup();
		assertEquals(0, group.getAllItems().size());
		assertEquals(0, group.getAllProducts().size());
		assertEquals(0, group.getAllProductGroups().size());
	}

	private ProductGroup createTestGroup()
	{
		StorageUnit unit = new StorageUnit("Test Storage Unit");
		ProductGroup group =
				new ProductGroup("Test Group", new ThreeMonthSupply(1.0f, UnitType.CHEVROLET));
		return group;
	}

	private Item populateTestGroup(ProductGroup group)
	{
		Product prod =
				new Product(new Date(), "product", new ProductBarcode("2"),
						new CountUnitSize(1), 1, new CountThreeMonthSupply(1));
		Item item =
				new Item(prod, new ProductBarcode("3"), new Date(), new Date());
		group.addItem(item);
		group.addProduct(prod);
		return item;
	}

}
