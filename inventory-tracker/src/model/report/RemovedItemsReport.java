
package model.report;

import java.util.List;

import model.RemovedItems;

public class RemovedItemsReport extends Report
{
	// Variables
	private List<RemovedItems> removedItems = null;

	// Constructor
	public RemovedItemsReport(List<RemovedItems> items, IReportBuilder builder)
	{
		super(builder);
		this.removedItems = items;;
	}

	// Methods

	/**
	 * Gets the removed items for this report
	 */
	public List<RemovedItems> getremovedItems()
	{
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void createReport(String path)
	{
		// TODO Auto-generated method stub

	}
}
