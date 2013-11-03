/**
 * 
 */

package model.report;

/**
 * @author Michael
 * 
 */
public class NoticesHTMLReportBuilder implements ReportBuilder
{
	// Variables
	private Visitor visitor = null;

	/**
	 * Constructor for NoticesHTMLReportBuilder
	 * @pre visitor passed in must not be == null
	 * @post none
	 * @param visitor the visitor that will contain the data on the notices for
	 *            this report.
	 */
	public NoticesHTMLReportBuilder(Visitor visitor)
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
