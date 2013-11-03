/**
 * 
 */

package model.report;

/**
 * @author Michael
 * 
 */
public class RemovedItemsPDFReportBuilder implements ReportBuilder
{

	// Variables
	private Visitor visitor = null;

	/**
	 * Constructor for RemovedItemsPDFReportBuilder
	 * @pre visitor passed in must not be == null
	 * @post none
	 * @param visitor the visitor that will contain the data on the removed
	 *            items for this report
	 */
	public RemovedItemsPDFReportBuilder(Visitor visitor)
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
