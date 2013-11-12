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
	protected IReportBuilder builder = null;

	/**
	 * Constructor for abstract class.
	 */
	public Report(IReportBuilder builder)
	{
		this.builder = builder;
	}

	public abstract void createReport();

}
