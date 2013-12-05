
package model.plugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DavidPluginTest
{
	@Test
	public void test()
	{
		DavidPlugin plugin = new DavidPlugin();

		String name = plugin.findProduct("123456789012");
		assertEquals(name, "THE MORAL MINORITY: MORAL MINORITY");

		name = plugin.findProduct("123456789011");
		assertNull(name);
	}
}
