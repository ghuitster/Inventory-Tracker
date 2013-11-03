/**
 * 
 */

package model.report;

/**
 * @author Michael
 * 
 */
public class ExpItemsPDFReportBuilder implements ReportBuilder
{
	// Variables
	private Visitor visitor = null;

	/**
	 * Constructor for ExpItemsPDFReportBuilder
	 * @pre visitor passed in must not be == null
	 * @post none
	 * @param visitor the visitor that will contain the data on the expired
	 *            items for this report
	 */
	public ExpItemsPDFReportBuilder(Visitor visitor)
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
