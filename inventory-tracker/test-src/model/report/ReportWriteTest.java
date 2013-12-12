package model.report;

import static org.junit.Assert.*;

import java.io.File;

import model.Inventory;

import org.junit.Test;

public class ReportWriteTest
{

	@Test
	public void test()
	{
		ProdStatReport report = new ProdStatReport
				(Inventory.getInstance().getProductStats(1), 
						new HTMLReportBuilder(), 1);
		
		String reportDir = "." + File.separator + "report-test" + 
				File.separator;
		File directory = new File(reportDir);
		directory.mkdirs();
		directory.setWritable(false);
		clearDir(reportDir);
		report.createReport(reportDir + "productStatsReportTest.html");
		assertTrue(directory.listFiles().length == 0);
		directory.setWritable(true);
		report.createReport(reportDir + "productStatsReportTest.html");
		assertTrue(directory.listFiles().length == 1);
		
	}

	private void clearDir(String reportDir)
	{
		File directory = new File(reportDir);
		if(directory.exists())
		{
			File[] files = directory.listFiles();
			for (File file : files)
			{
			   file.delete();
			}
		}
	}

}
