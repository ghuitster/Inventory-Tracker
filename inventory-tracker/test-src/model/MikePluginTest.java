
package model;

import static org.junit.Assert.assertEquals;
import model.plugin.MikePlugin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MikePluginTest
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{}

	@Before
	public void setUp() throws Exception
	{}

	@After
	public void tearDown() throws Exception
	{}

	@Test
	public final void testFindProduct()
	{
		MikePlugin mp = new MikePlugin();

		String test = mp.findProduct("0111222333446");

		assertEquals(test, "UPC Database Testing Code");
	}

}
