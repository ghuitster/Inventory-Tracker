/**
 * 
 */

package model.report;

/**
 * @author Michael
 * 
 */
public class NMonthSupplyPDFReportBuilder implements ReportBuilder
{
	// Variables
	private Visitor visitor = null;

	/**
	 * Constructor for NMonthSupplyPDFReportBuilder
	 * @pre visitor passed in must not be == null
	 * @post none
	 * @param visitor the visitor that will contain the data on the n-month's
	 *            supply for the items for this report
	 */
	public NMonthSupplyPDFReportBuilder(Visitor visitor)
	{
		this.visitor = visitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.ReportBuilder#buildReport()
	 */
	@Override
	public void buildReport()
	{

	}

}
