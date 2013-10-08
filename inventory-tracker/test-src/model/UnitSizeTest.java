
package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UnitSizeTest
{

	@Test
	public void equalsTest()
	{
		UnitSize unitSize = new UnitSize(5, UnitType.CHEVROLET);
		assertTrue(unitSize.equals(unitSize));
		assertFalse(unitSize.equals(5));
		assertFalse(unitSize
				.equals(new String("This is going to return false")));
		UnitSize unitSize2 = new UnitSize(5, UnitType.ELEPHANT_WEIGHT);
		assertFalse(unitSize.equals(unitSize2));
		unitSize2 = new UnitSize(10, UnitType.CHEVROLET);
		assertFalse(unitSize.equals(unitSize2));
		unitSize2.setAmount(5);
		assertTrue(unitSize.equals(unitSize2));
	}

	@Test
	public void valueTest()
	{
		UnitSize countUnitSize = new UnitSize(5, UnitType.ELEPHANT_WEIGHT);
		assertTrue(countUnitSize.ableToSetSize(5));
		assertFalse(countUnitSize.ableToSetSize(-1));
		assertEquals(countUnitSize.getSize(), 5, 0);
		countUnitSize.setSize(10);
		assertEquals(countUnitSize.getSize(), 10, 0);
	}
}
