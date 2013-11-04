/**
 * 
 */

package model.report;

/**
 * @author Michael
 * 
 */
public class ExpItemsHTMLReportBuilder extends ExpItemsReport implements
		ReportBuilder
{

	/**
	 * Constructor for ExpItemsHTMLReportBuilder
	 * @pre none
	 * @post none
	 */
	public ExpItemsHTMLReportBuilder()
	{}

	/**
	 * Method to construct HTML body for this report. Called from buildReport().
	 * @pre this.visitor must not be == null
	 * @post Valid HTML string containing HTML body info is returned to
	 *       buildReport method
	 */
	private String buildHTMLBody()
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
