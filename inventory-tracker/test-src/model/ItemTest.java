/**
 * 
 */
package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * @author Michael
 *
 */
public class ItemTest
{
	/**
	 * Test method for {@link model.Item#Item(model.Product, model.Barcode, java.util.Date, java.util.Date, java.util.Date, model.StorageUnit)}.
	 */
	/*@Test
	public final void testItem()
	{
		Date creDate = new Date();
		String desc = "Test Product No. 1";
		String desc2 = "Test Product No. 2";
		Barcode bc = new Barcode("400000000001");
		UnitSize size = new UnitSize(1.0f, UnitType.CHEVROLET);
		int shelfLife = 5;
		CountThreeMonthSupply threeMonthSupply = new CountThreeMonthSupply(1);
		HashSet<ProductContainer> containers = new HashSet<ProductContainer>();
		Item itm = new Item(new Product(creDate, desc, bc, size, shelfLife, threeMonthSupply, containers), new Barcode("400000000000"), new Date(), new Date(), new Date(), new ProductContainer());
	}*/

	/**
	 * Test method for {@link model.Item#ableToSetBarcode(model.Barcode)}.
	 */
	@Test
	public final void testAbleToSetBarcode()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#ableToSetContainer(model.StorageUnit)}.
	 */
	@Test
	public final void testAbleToSetContainer()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#ableToSetExitTime(java.util.Date)}.
	 */
	@Test
	public final void testAbleToSetExitTime()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#ableToSetExpirationDate(java.util.Date)}.
	 */
	@Test
	public final void testAbleToSetExpirationDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#ableToSetProduct(model.Product)}.
	 */
	@Test
	public final void testAbleToSetProduct()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#getBarcode()}.
	 */
	@Test
	public final void testGetBarcode()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#getContainer()}.
	 */
	@Test
	public final void testGetContainer()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#getEntryDate()}.
	 */
	@Test
	public final void testGetEntryDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#getExitTime()}.
	 */
	@Test
	public final void testGetExitTime()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#getExpirationDate()}.
	 */
	@Test
	public final void testGetExpirationDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#getProduct()}.
	 */
	@Test
	public final void testGetProduct()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#setBarcode(model.Barcode)}.
	 */
	@Test
	public final void testSetBarcode()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#setContainer(model.ProductContainer)}.
	 */
	@Test
	public final void testSetContainer()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#setExitTime(java.util.Date)}.
	 */
	@Test
	public final void testSetExitTime()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#setExpirationDate(java.util.Date)}.
	 */
	@Test
	public final void testSetExpirationDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Item#setProduct(model.Product)}.
	 */
	@Test
	public final void testSetProduct()
	{
		fail("Not yet implemented"); // TODO
	}

}
