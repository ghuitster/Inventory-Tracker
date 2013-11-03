/**
 * 
 */

package model.report;

/**
 * @author Michael
 * 
 */
public class NMonthSupplyHTMLReportBuilder implements ReportBuilder
{
	// Variables
	private Visitor visitor = null;

	/**
	 * Constructor for NMonthSupplyHTMLReportBuilder
	 * @pre visitor passed in must not be == null
	 * @post none
	 * @param visitor the visitor that will contain the data on the n-month's
	 *            supply for the items for this report
	 */
	public NMonthSupplyHTMLReportBuilder(Visitor visitor)
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

	/**
	 * Method to construct HTML header for this report. Called from
	 * buildReport().
	 * @pre this.visitor must not be == null
	 * @post Valid HTML string containing HTML header info is returned to
	 *       buildReport method
	 */
	private String buildHTMLHead()
	{
		return "";
	}

	/**
	 * Method to construct HTML body for this report. Called from buildReport().
	 * @pre this.visitor must not be == null
	 * @post Valid HTML string containing HTML body info is returned to
	 *       buildReport method
	 */
	private String buildHTMLBody()
	{
		return "";
	}

}
