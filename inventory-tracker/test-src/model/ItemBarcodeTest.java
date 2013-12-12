
package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Michael
 * 
 */
public class ItemBarcodeTest
{
	/**
	 * Test method for {@link model.Barcode#ableToSetNumber(java.lang.String)}.
	 */
	@Test
	public final void testAbleToSetNumber()
	{
		String goodBCNum = "400000000001";
		String badBCNum = "012345678912";
		IBarcode goodBC = new ItemBarcode(goodBCNum);

		assertFalse(goodBC.ableToSetNumber(badBCNum));
		assertTrue(goodBC.ableToSetNumber(goodBCNum));
	}

	/**
	 * Test method for {@link model.Barcode#Barcode(java.lang.String)}.
	 */
	@Test
	public final void testBarcode()
	{
		String goodBCNum = "400000000001";
		String goodBCNum2 = "400000000002";
		IBarcode goodBC = new ItemBarcode(goodBCNum);

		assertTrue(goodBCNum.equals(goodBC.getNumber()));
		assertFalse(goodBCNum2.equals(goodBC.getNumber()));

		goodBC.setNumber(goodBCNum2);
		assertTrue(goodBCNum2.equals(goodBC.getNumber()));
		assertFalse(goodBCNum.equals(goodBC.getNumber()));
	}

	/**
	 * Test method for {@link model.Barcode#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject()
	{
		String bcOneNum = "400000000001";
		String bcTwoNum = "400000000002";
		IBarcode one = new ItemBarcode(bcOneNum);
		IBarcode two = new ItemBarcode(bcTwoNum);

		assertFalse(one.equals(two));
		two.setNumber(bcOneNum);
		assertTrue(one.equals(two));
	}

	/**
	 * Test method for {@link model.Barcode#getNumber()}.
	 */
	@Test
	public final void testGetNumber()
	{
		String bcNum = "400000000001";
		String empty = "";
		IBarcode test = new ItemBarcode(bcNum);

		assertFalse(empty.equals(test.getNumber()));
		assertTrue(bcNum.equals(test.getNumber()));
	}

	/**
	 * Test method for {@link model.Barcode#hashCode()}.
	 */
	@Test
	public final void testHashCode()
	{
		IBarcode goodBC = new ItemBarcode("400000000001");
		IBarcode badBC = new ItemBarcode("012345678912");
		IBarcode testBC = new ItemBarcode("400000000001");

		assertTrue(goodBC.hashCode() == testBC.hashCode());
		assertFalse(badBC.hashCode() == testBC.hashCode());
	}

	/**
	 * Test method for {@link model.Barcode#isValid(java.lang.String)}.
	 */
	@Test
	public final void testIsValid()
	{
		String goodNum = "400000000001";
		String badNum1 = "012345678912";
		String badNum2 = "42345678901234567890";

		assertFalse(ItemBarcode.isValid(badNum1));
		assertFalse(ItemBarcode.isValid(badNum2));
		assertTrue(ItemBarcode.isValid(goodNum));
	}

	/**
	 * Test method for {@link model.Barcode#setNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetNumber()
	{
		String goodBCNum1 = "400000000001";
		String goodBCNum2 = "400000000002";
		IBarcode goodBC = new ItemBarcode(goodBCNum1);

		assertFalse(goodBCNum2.equals(goodBC.getNumber()));
		goodBC.setNumber(goodBCNum2);
		assertTrue(goodBCNum2.equals(goodBC.getNumber()));
	}

	/**
	 * Test method for {@link model.Barcode#toString()}.
	 */
	@Test
	public final void testToString()
	{
		String goodBCNum = "400000000001";
		IBarcode goodBC = new ItemBarcode(goodBCNum);

		assertTrue(goodBC.toString().contains(goodBCNum));
	}

}
