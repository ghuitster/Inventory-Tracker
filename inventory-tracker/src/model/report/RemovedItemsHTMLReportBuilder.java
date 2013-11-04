/**
 * 
 */

package model.report;

/**
 * @author Michael
 * 
 */
public class RemovedItemsHTMLReportBuilder extends RemovedItemsReport implements
		ReportBuilder
{

	/**
	 * Constructor for RemovedItemsHTMLReportBuilder
	 * @pre visitor passed in must not be == null
	 * @post none
	 */
	public RemovedItemsHTMLReportBuilder()
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
