/**
 * 
 */

package model.report;

/**
 * @author Michael
 * 
 */
public class NoticesHTMLReportBuilder extends NoticesReport implements ReportBuilder
{

	/**
	 * Constructor for NoticesHTMLReportBuilder
	 * @pre none
	 * @post none
	 */
	public NoticesHTMLReportBuilder()
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
