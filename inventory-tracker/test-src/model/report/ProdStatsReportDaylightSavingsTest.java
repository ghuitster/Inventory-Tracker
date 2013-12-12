/**
 * 
 */

package model.report;

import static org.junit.Assert.fail;
import model.IInventory;
import model.IProduct;
import model.Inventory;
import model.Product;
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
public class ProdStatsReportDaylightSavingsTest
{
	// Variables for Everything
	private IInventory inventory = null;
	private final StorageUnit asu = new StorageUnit("a");
	private final StorageUnit bsu = new StorageUnit("b");
	private final StorageUnit csu = new StorageUnit("c");

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
		inventory.addStorageUnit(bsu);
		inventory.addStorageUnit(csu);

		IProduct testProduct = new Product(null, null, null, null, 0, null);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{}

	@Test
	public final void test()
	{
		fail("Not yet implemented"); // TODO
	}

}
