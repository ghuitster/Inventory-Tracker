
package model.report;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.Inventory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductStatisticsFileTypeTest
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
	public void test()
	{
		ProdStatReport report =
				new ProdStatReport(Inventory.getInstance().getProductStats(1),
						new PDFReportBuilder(), 1);

		String reportDir =
				"." + File.separator + "report-valid-test" + File.separator;
		report.createReport(reportDir + "productStatsReportTest.pdf");

		try
		{
			BufferedReader reader =
					new BufferedReader(new FileReader(new File(reportDir
							+ "productStatsReportTest.pdf")));
			String line = reader.readLine();
			reader.close();
			assertFalse(line.toLowerCase().contains("html"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		report =
				new ProdStatReport(Inventory.getInstance().getProductStats(1),
						new HTMLReportBuilder(), 1);

		reportDir = "." + File.separator + "report-valid-test" + File.separator;
		report.createReport(reportDir + "productStatsReportTest.html");

		try
		{
			BufferedReader reader =
					new BufferedReader(new FileReader(new File(reportDir
							+ "productStatsReportTest.html")));
			String line = reader.readLine();
			reader.close();
			assertTrue(line.toLowerCase().contains("html"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
