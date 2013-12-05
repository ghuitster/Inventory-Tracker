
package model.plugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class MikePluginTest
{
	@Test
	public final void testFindProduct()
	{
		MikePlugin mp = new MikePlugin();

		String test = mp.findProduct("0111222333446");

		assertEquals(test, "UPC Database Testing Code");

		test = mp.findProduct("0111222333447");

		assertEquals(test, null);

		test = mp.findProduct("noProductHere");

		assertNull(test);
	}

}
