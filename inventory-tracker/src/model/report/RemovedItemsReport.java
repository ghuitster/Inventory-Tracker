
package model.report;

import java.util.Arrays;
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
		this.builder.buildHead("Items Removed Since " + this.dateString);
		String[] columns =
				{"Description", "Size", "Product Barcode", "Removed",
						"Current Supply"};
		this.builder.startTable(columns);

		System.out.println("About to add rows to table.");
		for(RemovedItems items: this.removedItems)
		{
			String[] row = this.createRow(items);
			System.out.println("Trying to add row: " + row);
			this.builder.addTableRow(row);
		}

		this.builder.finishTable();

		this.builder.finishAndSave(path);
	}

	private String[] createRow(RemovedItems items)
	{
		String desc = items.getProduct().getDescription().getDescription();
		String size = items.getProduct().getSize().toString();
		String barcode = items.getProduct().getBarcode().getNumber();
		String numRemoved = items.getCount() + "";
		String supply = items.getSupply() + "";

		String[] row = {desc, size, barcode, numRemoved, supply};
		System.out.println("About to add " + Arrays.toString(row)
				+ " to the table");
		return row;
	}
}
