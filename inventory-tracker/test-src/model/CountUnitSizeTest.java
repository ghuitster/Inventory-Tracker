package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountUnitSizeTest
{

	@Test
	public void valueTest()
	{
		CountUnitSize countUnitSize = new CountUnitSize(5);
		assertTrue(countUnitSize.ableToSetUnitSize(5));
		assertFalse(countUnitSize.ableToSetUnitSize(-1));
		assertEquals(countUnitSize.getSize(), 5);
		countUnitSize.setSize(10);
		assertEquals(countUnitSize.getSize(), 10);
	}
	
	@Test
	public void equalsTest()
	{
		CountUnitSize countUnitSize = new CountUnitSize(5);
		assertTrue(countUnitSize.equals(countUnitSize));
		assertFalse(countUnitSize.equals(5));
		assertFalse(countUnitSize.equals(new String("This is going to return false")));
		CountUnitSize countUnitSize2 = new CountUnitSize(10);
		assertFalse(countUnitSize.equals(countUnitSize2));
		countUnitSize2.setAmount(5);
		assertTrue(countUnitSize.equals(countUnitSize2));
	}

}
