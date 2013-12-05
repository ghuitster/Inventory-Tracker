package model.plugin;

import static org.junit.Assert.*;

import org.junit.Test;

public class FirePluginTest
{

	@Test
	public void test()
	{
		FirePlugin plugin = new FirePlugin();
		
		String name = plugin.findProduct("051000148704");
		
		assertEquals(name, null);
		
		name = plugin.findProduct("123456789012");
		
		assertEquals(name, "HP TFT7600 G2  KVM console  17.3");
		
		name = plugin .findProduct("2135");
		
		assertEquals(name, null);
	}

}
