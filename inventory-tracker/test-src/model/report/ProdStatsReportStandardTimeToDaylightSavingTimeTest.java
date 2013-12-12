/**
 * 
 */

package model.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import model.CountThreeMonthSupply;
import model.CountUnitSize;
import model.IInventory;
import model.IItem;
import model.IProduct;
import model.Inventory;
import model.Item;
import model.ItemBarcode;
import model.Product;
import model.ProductBarcode;
import model.ProductStats;
import model.StorageUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Michael
 * 
 */
public class ProdStatsReportStandardTimeToDaylightSavingTimeTest
{
	// Variables for Everything
	private IInventory inventory = null;
	private final StorageUnit asu = new StorageUnit("a");
	IProduct beforeProduct = null;
	IProduct onProduct = null;
	IProduct afterProduct = null;
	IItem beforeItem = null;
	IItem onItem = null;
	IItem afterItem = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		inventory = Inventory.getInstance();
		inventory.addStorageUnit(asu);

		Date beforeDate = new Date(2013, 3, 9, 12, 0, 0);
		Date onDate = new Date(2013, 3, 10, 2, 0, 0);
		Date afterDate = new Date(2013, 3, 11, 12, 0, 0);

		beforeProduct =
				new Product(beforeDate, "beforeProduct", new ProductBarcode(
						"010101010101"), new CountUnitSize(3), 0,
						new CountThreeMonthSupply(0));
		onProduct =
				new Product(onDate, "onProduct", new ProductBarcode(
						"010101010102"), new CountUnitSize(3), 0,
						new CountThreeMonthSupply(0));
		afterProduct =
				new Product(afterDate, "afterProduct", new ProductBarcode(
						"010101010103"), new CountUnitSize(3), 0,
						new CountThreeMonthSupply(0));

		beforeItem =
				new Item(beforeProduct, new ItemBarcode(""
						+ Inventory.getInstance().getUniqueBarCode()),
						beforeDate, null);

		onItem =
				new Item(onProduct, new ItemBarcode(""
						+ Inventory.getInstance().getUniqueBarCode()), onDate,
						null);

		afterItem =
				new Item(afterProduct, new ItemBarcode(""
						+ Inventory.getInstance().getUniqueBarCode()),
						afterDate, null);

		asu.addItem(beforeItem);
		asu.addItem(onItem);
		asu.addItem(afterItem);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		asu.removeItem(beforeItem);
		asu.removeItem(onItem);
		asu.removeItem(afterItem);
		asu.removeProduct(beforeProduct);
		asu.removeProduct(onProduct);
		asu.removeProduct(afterProduct);
		inventory.removeStorageUnit(asu);
		beforeProduct = null;
		onProduct = null;
		afterProduct = null;
		beforeItem = null;
		onItem = null;
		afterItem = null;
	}

	@Test
	public final void transitionInMiddleTest()
	{
		Date reportDate = new Date(2013, 4, 10, 12, 0, 0);

		List<ProductStats> prodStatList =
				inventory.getProductStats(reportDate, 2);

		assertTrue(prodStatList.size() == 3);

		ProductStats beforeProdStats = prodStatList.get(1);
		ProductStats onProdStats = prodStatList.get(2);
		ProductStats afterProdStats = prodStatList.get(0);

		assertEquals(beforeProdStats.getProduct().getBarcode(),
				beforeProduct.getBarcode());

		assertEquals(onProdStats.getProduct().getBarcode(),
				onProduct.getBarcode());

		assertEquals(afterProdStats.getProduct().getBarcode(),
				afterProduct.getBarcode());

	}

	@Test
	public final void transitionAtBeginningTest()
	{
		Date reportDate = new Date(2013, 4, 10, 0, 0, 0);

		List<ProductStats> prodStatList =
				inventory.getProductStats(reportDate, 1);

		assertTrue(prodStatList.size() == 3);

		ProductStats beforeProdStats = prodStatList.get(1);
		ProductStats onProdStats = prodStatList.get(2);
		ProductStats afterProdStats = prodStatList.get(0);

		assertEquals(beforeProdStats.getProduct().getBarcode(),
				beforeProduct.getBarcode());

		assertEquals(onProdStats.getProduct().getBarcode(),
				onProduct.getBarcode());

		assertEquals(afterProdStats.getProduct().getBarcode(),
				afterProduct.getBarcode());
	}

	@Test
	public final void transitionAtEndTest()
	{
		Date reportDate = new Date(2013, 3, 10, 23, 59, 59);

		List<ProductStats> prodStatList =
				inventory.getProductStats(reportDate, 1);

		assertTrue(prodStatList.size() == 3);

		ProductStats beforeProdStats = prodStatList.get(1);
		ProductStats onProdStats = prodStatList.get(2);
		ProductStats afterProdStats = prodStatList.get(0);

		assertEquals(beforeProdStats.getProduct().getBarcode(),
				beforeProduct.getBarcode());

		assertEquals(onProdStats.getProduct().getBarcode(),
				onProduct.getBarcode());

		assertEquals(afterProdStats.getProduct().getBarcode(),
				afterProduct.getBarcode());
	}

}
