
package model.report;

import java.util.List;

import model.IItem;

public class ExpItemsReport extends Report
{
	// Variables
	private List<IItem> items = null;

	// Constructor
	public ExpItemsReport(List<IItem> items, IReportBuilder builder)
	{
		super(builder);
		this.items = items;
	}

	@Override
	public void createReport(String path)
	{
		this.builder.buildHead("Expired Items");
	}
}
