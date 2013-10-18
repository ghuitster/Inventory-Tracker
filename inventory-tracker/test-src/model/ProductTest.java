/**
 * 
 */

package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Michael
 * 
 */
public class ProductTest
{

	// Global Variables
	Date creDate = new Date();
	String desc = "Test Product No. 1";
	String desc2 = "Test Product No. 2";
	IBarcode bc = new Barcode("400000000001");
	UnitSize size = new UnitSize(1.0f, UnitType.CHEVROLET);
	int shelfLife = 5;
	CountThreeMonthSupply threeMonthSupply = new CountThreeMonthSupply(1);
	HashSet<ProductContainer> containers = new HashSet<ProductContainer>();

	BaseProduct prod1;
	BaseProduct prod2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		prod1 =
				new Product(creDate, desc, bc, size, shelfLife,
						threeMonthSupply, containers);
		prod2 =
				new Product(creDate, desc2, bc, size, shelfLife,
						threeMonthSupply, containers);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		prod1 = null;
		prod2 = null;
	}

	/**
	 * Test method for
	 * {@link model.Product#ableToAddContainer(model.StorageUnit)}.
	 */
	@Test
	public final void testAbleToAddContainer()
	{
		ProductContainer test = new StorageUnit("Storage Unit 1");
		prod1.addContainer(test);
		assertTrue(prod1.getContainers().contains(test));
	}

	/**
	 * Test method for
	 * {@link model.Product#ableToRemoveContainer(model.ProductContainer)}.
	 */
	@Test
	public final void testAbleToRemoveContainer()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#ableToSetBarcode(model.Barcode)}.
	 */
	@Test
	public final void testAbleToSetBarcode()
	{
		IBarcode bc2 = new Barcode("400000000002");
		assertFalse(prod1.getBarcode().equals(bc2));
		prod1.setBarcode(bc2);
		assertTrue(prod1.getBarcode().equals(bc2));
	}

	/**
	 * Test method for
	 * {@link model.Product#ableToSetDescription(java.lang.String)}.
	 */
	@Test
	public final void testAbleToSetDescription()
	{
		assertFalse(desc2.equals(prod1.getDescription().getDescription()));
		prod1.setDescription(new ProductDescription(desc2));
		assertTrue(desc2.equals(prod1.getDescription().getDescription()));
	}

	/**
	 * Test method for {@link model.Product#ableToSetShelfLife(int)}.
	 */
	@Test
	public final void testAbleToSetShelfLife()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#ableToSetSize(model.Amount)}.
	 */
	@Test
	public final void testAbleToSetSize()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link model.Product#ableToSetThreeMonthSupply(model.Amount)}.
	 */
	@Test
	public final void testAbleToSetThreeMonthSupply()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link model.Product#addContainer(model.ProductContainer)}.
	 */
	@Test
	public final void testAddContainer()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#getBarcode()}.
	 */
	@Test
	public final void testGetBarcode()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#getCreationDate()}.
	 */
	@Test
	public final void testGetCreationDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#getDescription()}.
	 */
	@Test
	public final void testGetDescription()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#getShelfLife()}.
	 */
	@Test
	public final void testGetShelfLife()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#getSize()}.
	 */
	@Test
	public final void testGetSize()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#getThreeMonthSupply()}.
	 */
	@Test
	public final void testGetThreeMonthSupply()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#hashCode()}.
	 */
	@Test
	public final void testHashCode()
	{
		assertFalse(prod1.hashCode() == prod2.hashCode());
		prod2.setDescription(new ProductDescription(desc2));
		assertTrue(prod1.hashCode() == prod2.hashCode());
	}

	/**
	 * Test method for
	 * {@link model.Product#Product(java.util.Date, java.lang.String, model.Barcode, model.UnitSize, int, model.Amount, java.util.Set)}
	 * .
	 */
	@Test
	public final void testProduct()
	{
		assertTrue(creDate.equals(prod1.getCreationDate()));
		assertTrue(desc.equals(prod1.getDescription()));
		assertTrue(bc.equals(prod1.getBarcode()));
		assertTrue(size.equals(prod1.getSize()));
		assertTrue(shelfLife == prod1.getShelfLife());
		assertTrue(threeMonthSupply.equals(prod1.getThreeMonthSupply()));
		assertTrue(containers.equals(prod1.getContainers()));
	}

	/**
	 * Test method for
	 * {@link model.Product#removeContainer(model.ProductContainer)}.
	 */
	@Test
	public final void testRemoveContainer()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#setBarcode(model.Barcode)}.
	 */
	@Test
	public final void testSetBarcode()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#setCreationDate(java.util.Date)}.
	 */
	@Test
	public final void testSetCreationDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#setDescription(java.lang.String)}.
	 */
	@Test
	public final void testSetDescription()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#setShelfLife(int)}.
	 */
	@Test
	public final void testSetShelfLife()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#setSize(model.UnitSize)}.
	 */
	@Test
	public final void testSetSize()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Product#setThreeMonthSupply(model.Amount)}.
	 */
	@Test
	public final void testSetThreeMonthSupply()
	{
		fail("Not yet implemented"); // TODO
	}
}
