/**
 * 
 */

package model.report;

/**
 * @author Michael Abstract class to represent a base Report.
 */
public abstract class Report
{
	// Variables
	private IReportBuilder reportBuilder = null;

	/**
	 * Constructor for abstract class.
	 */
	public Report(IReportBuilder builder)
	{
		this.reportBuilder = builder;
	}

	public abstract void createReport(String path);

}
