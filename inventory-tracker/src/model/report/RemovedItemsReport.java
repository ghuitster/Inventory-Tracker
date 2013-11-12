
package model.report;

import java.util.Date;
import java.util.List;

import model.RemovedItems;

import common.util.DateUtils;

public class RemovedItemsReport extends Report
{
	// Variables
	private List<RemovedItems> removedItems = null;
	private String dateString = null;

	// Constructor
	public RemovedItemsReport(List<RemovedItems> items, Date reportDate,
			IReportBuilder builder)
	{
		super(builder);
		this.dateString = DateUtils.formatDateTime(reportDate);
		this.removedItems = items;
	}

	// Methods

	/**
	 * Gets the removed items for this report
	 */
	public List<RemovedItems> getremovedItems()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void createReport(String path)
	{

	}
}
