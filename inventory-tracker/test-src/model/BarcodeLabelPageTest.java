/**
 * 
 */

package model;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Michael
 * 
 */
public class BarcodeLabelPageTest
{
	// Variables
	ArrayList<IItem> items = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		this.items = new ArrayList<IItem>();

		IBarcode prodBarcode = new ProductBarcode("012345678900");
		Amount size = new UnitSize(15, UnitType.CHEVROLET);
		CountThreeMonthSupply threeMonthSupply = new CountThreeMonthSupply(36);
		IProduct prod =
				new Product(new Date(), "Test Product #0123456789",
						prodBarcode, size, 12, threeMonthSupply);

		for(int i = 0; i < 200; i++)
		{
			String codeNumber = "400000000000";
			IBarcode itemBarcode = new ItemBarcode(codeNumber + i);
			IItem item = new Item(prod, null, null, null);
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{}

	/**
	 * Test method for
	 * {@link model.BarcodeLabelPage#BarcodeLabelPage(java.util.List)}.
	 */
	@Test
	public final void testBarcodeLabelPage()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.BarcodeLabelPage#createPDF()}.
	 */
	@Test
	public final void testCreatePDF()
	{
		fail("Not yet implemented"); // TODO
	}

}
