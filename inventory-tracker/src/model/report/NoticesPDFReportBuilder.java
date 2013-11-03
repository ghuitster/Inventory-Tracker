/**
 * 
 */

package model.report;

/**
 * @author Michael
 * 
 */
public class NoticesPDFReportBuilder implements ReportBuilder
{
	// Variables
	private Visitor visitor = null;

	/**
	 * Constructor for NoticesPDFReportBuilder
	 * @pre visitor passed in must not be == null
	 * @post none
	 * @param visitor the visitor that will contain the data on the notices for
	 *            this report
	 */
	public NoticesPDFReportBuilder(Visitor visitor)
	{
		this.visitor = visitor
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
