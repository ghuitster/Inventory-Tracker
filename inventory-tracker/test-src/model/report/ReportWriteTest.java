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
		clearDir(reportDir);
		report.createReport(reportDir + "productStatsReportTest.html");
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
