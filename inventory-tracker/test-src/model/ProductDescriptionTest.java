
package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProductDescriptionTest
{
	@Test
	public final void testAbleToSetDescription()
	{
		String goodPD = "Test Product No. 1";
		String goodPD2 = "Test Product No. 2";

		IProductDescription pdGood = new ProductDescription(goodPD);
		assertFalse(goodPD2.equals(pdGood.getDescription()));
		pdGood.setDescription(goodPD2);
		assertTrue(goodPD2.equals(pdGood.getDescription()));
	}

	@Test
	public final void testEqualsObject()
	{
		String goodPD = "Test Product No. 1";
		String goodPD2 = "Test Product No. 2";

		IProductDescription pdGood = new ProductDescription(goodPD);
		IProductDescription pdGood2 = new ProductDescription(goodPD2);

		assertFalse(pdGood.equals(pdGood2));
		pdGood2.setDescription(goodPD);
		assertTrue(pdGood.equals(pdGood2));
	}

	@Test
	public final void testGetDescription()
	{
		String goodPD = "Test Product No. 1";
		String goodPD2 = "Test Product No. 2";
		IProductDescription pdGood = new ProductDescription(goodPD);

		assertFalse(goodPD2.equals(pdGood.getDescription()));
		assertTrue(goodPD.equals(pdGood.getDescription()));
	}

	@Test
	public final void testHashCode()
	{
		String goodPD = "Test Product No. 1";
		String goodPD2 = "Test Product No. 2";
		String test = goodPD.toString();

		IProductDescription pdGood = new ProductDescription(goodPD);
		IProductDescription pdGood2 = new ProductDescription(goodPD2);
		IProductDescription pdTest = new ProductDescription(test);

		assertFalse(pdTest.hashCode() == pdGood2.hashCode());
		assertTrue(pdTest.hashCode() == pdGood.hashCode());
	}

	@Test
	public final void testIsValid()
	{
		String goodPD = "Test Product No. 1";
		String badPD = "\t\t\t\n   \r\n\t";

		assertFalse(ProductDescription.isValid(badPD));
		assertTrue(ProductDescription.isValid(goodPD));
	}

	@Test
	public final void testProductDescription()
	{
		String goodPD = "Test Product No. 1";
		String badPD = "TestProductNo.1";

		IProductDescription pdGood = new ProductDescription(goodPD);
		assertFalse(badPD.equals(pdGood.getDescription()));
		assertTrue(goodPD.equals(pdGood.getDescription()));
	}

	@Test
	public final void testSetDescription()
	{
		String goodPD = "Test Product No. 1";
		String goodPD2 = "Test Product No. 2";
		IProductDescription pdGood = new ProductDescription(goodPD);

		assertFalse(goodPD2.equals(pdGood.getDescription()));
		pdGood.setDescription(goodPD2);
		assertTrue(goodPD2.equals(pdGood.getDescription()));
	}

	@Test
	public final void testToString()
	{
		String goodPD = "Test Product No. 1";
		String test = "ProductDescription [description=Test Product No. 1]";
		IProductDescription pdGood = new ProductDescription(goodPD);

		assertFalse(goodPD.equals(pdGood.toString()));
		assertTrue(test.equals(pdGood.toString()));
	}

}
