package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountThreeMonthSupplyTest
{

	@Test
	public void valueTest()
	{
		CountThreeMonthSupply countThreeMonthSupply = new CountThreeMonthSupply(5);
		assertTrue(countThreeMonthSupply.ableToSetAmount(5));
		assertFalse(countThreeMonthSupply.ableToSetAmount(-1));
		assertEquals(countThreeMonthSupply.getAmount(), 5);
		countThreeMonthSupply.setAmount(10);
		assertEquals(countThreeMonthSupply.getAmount(), 10);
	}
	
	@Test
	public void equalsTest()
	{
		CountThreeMonthSupply countThreeMonthSupply = new CountThreeMonthSupply(5);
		assertTrue(countThreeMonthSupply.equals(countThreeMonthSupply));
		assertFalse(countThreeMonthSupply.equals(5));
		assertFalse(countThreeMonthSupply.equals(new String("This is going to return false")));
		CountThreeMonthSupply countThreeMonthSupply2 = new CountThreeMonthSupply(10);
		assertFalse(countThreeMonthSupply.equals(countThreeMonthSupply2));
		countThreeMonthSupply2.setAmount(5);
		assertTrue(countThreeMonthSupply.equals(countThreeMonthSupply2));
	}
}
