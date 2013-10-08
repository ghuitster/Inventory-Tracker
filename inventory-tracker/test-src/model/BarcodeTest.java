
package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Michael
 * 
 */
public class BarcodeTest
{
	/**
	 * Test method for {@link model.Barcode#ableToSetNumber(java.lang.String)}.
	 */
	@Test
	public final void testAbleToSetNumber()
	{
		String goodBCNum = "400000000001";
		String badBCNum = "012345678912";
		Barcode goodBC = new Barcode(goodBCNum);

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
		Barcode goodBC = new Barcode(goodBCNum);

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
		Barcode one = new Barcode(bcOneNum);
		Barcode two = new Barcode(bcTwoNum);

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
		Barcode test = new Barcode(bcNum);

		assertFalse(empty.equals(test.getNumber()));
		assertTrue(bcNum.equals(test.getNumber()));
	}

	/**
	 * Test method for {@link model.Barcode#hashCode()}.
	 */
	@Test
	public final void testHashCode()
	{
		Barcode goodBC = new Barcode("400000000001");
		Barcode badBC = new Barcode("012345678912");
		Barcode testBC = new Barcode("400000000001");

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

		assertFalse(Barcode.isValid(badNum1));
		assertFalse(Barcode.isValid(badNum2));
		assertTrue(Barcode.isValid(goodNum));
	}

	/**
	 * Test method for {@link model.Barcode#setNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetNumber()
	{
		String goodBCNum1 = "400000000001";
		String goodBCNum2 = "400000000002";
		Barcode goodBC = new Barcode(goodBCNum1);

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
		String test = "Barcode [number=400000000001]";
		Barcode goodBC = new Barcode(goodBCNum);

		assertFalse(goodBCNum.equals(goodBC.toString()));
		assertTrue(test.equals(goodBC.toString()));
	}

}
