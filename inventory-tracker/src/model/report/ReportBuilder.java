
package model.report;

/**
 * This is the interface for the reportBuilder classes.
 */
public interface ReportBuilder
{
	/**
	 * A method that will build the report and save it as a file on disk,
	 * whether HTML or PDF.
	 */
	public void buildReport();
}
