
package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ThreeMonthSupplyTest
{

	@Test
	public void equalsTest()
	{
		ThreeMonthSupply threeMonthSupply =
				new ThreeMonthSupply(5, UnitType.STONE);
		assertTrue(threeMonthSupply.equals(threeMonthSupply));
		assertFalse(threeMonthSupply.equals(5));
		assertFalse(threeMonthSupply.equals(new String(
				"This is going to return false")));
		ThreeMonthSupply threeMonthSupply2 =
				new ThreeMonthSupply(5, UnitType.CHEVROLET);
		assertFalse(threeMonthSupply.equals(threeMonthSupply2));
		threeMonthSupply2 = new ThreeMonthSupply(10, UnitType.STONE);
		assertFalse(threeMonthSupply.equals(threeMonthSupply2));
		threeMonthSupply2.setAmount(5);
		assertTrue(threeMonthSupply.equals(threeMonthSupply2));
	}

	@Test
	public void valueTest()
	{
		ThreeMonthSupply threeMonthSupply =
				new ThreeMonthSupply(5, UnitType.STONE);
		assertTrue(threeMonthSupply.ableToSetAmount(5));
		assertFalse(threeMonthSupply.ableToSetAmount(-1));
		assertEquals(threeMonthSupply.getAmount(), 5, 0);
		threeMonthSupply.setAmount(10);
		assertEquals(threeMonthSupply.getAmount(), 10, 0);
	}
}
