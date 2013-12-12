
package gui.reports.productstats;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gui.common.FileFormat;
import gui.common.IController;
import gui.common.IView;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductStasticsControllerInputTests
{
	private IView view;
	private IController controller;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{}

	@Before
	public void setUp() throws Exception
	{
		view = new FakeProductStatisticsView(null, null);
		controller = new ProductStatsReportController(view);
	}

	@After
	public void tearDown() throws Exception
	{
		view = null;
		controller = null;
	}

	@Test
	public void testCloseTo100()
	{
		((FakeProductStatisticsView) view).setFormat(FileFormat.HTML);
		((FakeProductStatisticsView) view).setMonths("100");
		((ProductStatsReportController) controller).valuesChanged();
		assertTrue(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("99");
		((ProductStatsReportController) controller).valuesChanged();
		assertTrue(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("101");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setFormat(FileFormat.PDF);
		((FakeProductStatisticsView) view).setMonths("100");
		((ProductStatsReportController) controller).valuesChanged();
		assertTrue(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("99");
		((ProductStatsReportController) controller).valuesChanged();
		assertTrue(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("101");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());
	}

	@Test
	public void testCloseTo0()
	{
		((FakeProductStatisticsView) view).setFormat(FileFormat.HTML);
		((FakeProductStatisticsView) view).setMonths("-1");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("0");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("1");
		((ProductStatsReportController) controller).valuesChanged();
		assertTrue(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setFormat(FileFormat.PDF);
		((FakeProductStatisticsView) view).setMonths("-1");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("0");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("1");
		((ProductStatsReportController) controller).valuesChanged();
		assertTrue(((FakeProductStatisticsView) view).getOKEnabledStatus());
	}

	@Test
	public void testNonIntegers()
	{
		((FakeProductStatisticsView) view).setFormat(FileFormat.HTML);
		((FakeProductStatisticsView) view).setMonths("99.9");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("0.1");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("50.5");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setFormat(FileFormat.PDF);
		((FakeProductStatisticsView) view).setMonths("99.9");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("0.1");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("50.5");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());
	}

	@Test
	public void testNonDigits()
	{
		((FakeProductStatisticsView) view).setFormat(FileFormat.HTML);
		((FakeProductStatisticsView) view).setMonths("asdfghjkl");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("!@#$%^&*(");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("ΓΔΘΞΠΣΦΨΩ");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setFormat(FileFormat.PDF);
		((FakeProductStatisticsView) view).setMonths("asdfghjkl");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("!@#$%^&*(");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());

		((FakeProductStatisticsView) view).setMonths("ΓΔΘΞΠΣΦΨΩ");
		((ProductStatsReportController) controller).valuesChanged();
		assertFalse(((FakeProductStatisticsView) view).getOKEnabledStatus());
	}
}
