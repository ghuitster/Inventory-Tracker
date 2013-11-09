/**
 * 
 */
package model.report;

/**
 * @author Michael
 *
 */
public class PDFReportBuilder implements IReportBuilder
{

	/**
	 * 
	 */
	public PDFReportBuilder()
	{
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see model.report.IReportBuilder#buildHead(java.lang.String)
	 */
	@Override
	public void buildHead(String title)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.report.IReportBuilder#addSectionHeader(java.lang.String)
	 */
	@Override
	public void addSectionHeader(String title)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.report.IReportBuilder#addText(java.lang.String)
	 */
	@Override
	public void addText(String text)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.report.IReportBuilder#startTable(java.lang.String[])
	 */
	@Override
	public void startTable(String[] columnHeaders)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.report.IReportBuilder#addTableRow(java.lang.String[])
	 */
	@Override
	public void addTableRow(String[] cells)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.report.IReportBuilder#addBulletedList(java.lang.String[])
	 */
	@Override
	public void addBulletedList(String[] items)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.report.IReportBuilder#finishTable()
	 */
	@Override
	public void finishTable()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.report.IReportBuilder#finishAndSave(java.lang.String)
	 */
	@Override
	public void finishAndSave(String path)
	{
		// TODO Auto-generated method stub

	}

}
