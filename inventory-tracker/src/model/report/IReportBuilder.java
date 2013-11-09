/**
 * 
 */

package model.report;

/**
 * @author Michael Interface that ReportBuilder's must implement to work with
 *         Inventory Tracker correctly
 */
public interface IReportBuilder
{

	/**
	 * Method to build the report header (including the title).
	 * @param title the title of the report to include in the header.
	 * 
	 */
	public void buildHead(String title);

	/**
	 * Method to build a section header, with title.
	 * @param title the title for the section header.
	 */
	public void addSectionHeader(String title);

	/**
	 * Method to add text to a section or row.
	 * @param text the text to be added.
	 */
	public void addText(String text);

	/**
	 * Method to start creating the report table.
	 * @param columnHeaders the headers for the different sections (ie columns)
	 */
	public void startTable(String[] columnHeaders);

	/**
	 * Method to add a row to the report table. The Array of items should
	 * coincide with the headers added in startTable().
	 * @param cells the information to be added to the table row.
	 */
	public void addTableRow(String[] cells);

	/**
	 * Method to add a bulleted list to the report (for the Notices report
	 * specifically).
	 * @param items the bullets for the list.
	 */
	public void addBulletedList(String[] items);

	/**
	 * Method to finish the table and close it out.
	 */
	public void finishTable();

	/**
	 * Method to finish the report document, and save it to disk before
	 * displaying the report.
	 * @param path the filename and path where the report should be saved.
	 */
	public void finishAndSave(String path);
}
