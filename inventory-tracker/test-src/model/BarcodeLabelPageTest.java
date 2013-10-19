/**
 * 
 */

package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;
import common.util.DateUtils;

/**
 * @author Michael
 * 
 */
public class BarcodeLabelPageTest
{
	// Variables
	ArrayList<IItem> items = null;
	BarcodeLabelPage labelPage = null;

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
				new Product(new Date(), "Test Product #01234567890123456789",
						prodBarcode, size, 12, threeMonthSupply);

		for(int i = 0; i < 200; i++)
		{
			String codeNumber = "400000000";
			if(i < 10)
			{
				codeNumber = codeNumber + "00" + i;
			}
			else if(i > 9 && i < 100)
			{
				codeNumber = codeNumber + "0" + i;
			}
			else
			{
				codeNumber = codeNumber + i;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.set(2017, Calendar.NOVEMBER, 30);
			Date expirationDate = calendar.getTime();

			IBarcode itemBarcode = new ItemBarcode(codeNumber);
			IItem item =
					new Item(prod, itemBarcode, DateUtils.currentDate(),
							expirationDate);
			this.items.add(item);
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		this.items = null;
	}

	/**
	 * Test method for
	 * {@link model.BarcodeLabelPage#BarcodeLabelPage(java.util.List)}.
	 */
	@Test
	public final void testBarcodeLabelPage()
	{
		assertFalse(this.labelPage != null);
		this.labelPage = new BarcodeLabelPage(this.items);
		assertTrue(this.labelPage != null);
	}

	/**
	 * Test method for {@link model.BarcodeLabelPage#createPDF()}.
	 */
	@Test
	public final void testCreatePDF()
	{
		this.labelPage = new BarcodeLabelPage(this.items);

		try
		{
			this.labelPage.createPDF();
		}
		catch(IOException | DocumentException e)
		{
			e.printStackTrace();
		}
	}

}
