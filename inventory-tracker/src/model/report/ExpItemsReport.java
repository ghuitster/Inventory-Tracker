
package model.report;

import java.util.List;

import model.IItem;
import model.ProductGroup;

import common.util.DateUtils;

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
		String[] temp =
				{"Description", "Storage Unit", "Product Group", "Entry Date",
						"Expire Date", "Item Barcode"};
		this.builder.startTable(temp);

		for(IItem item: this.items)
		{
			this.builder.addTableRow(this.createRowArray(item));
		}

		this.builder.finishTable();

		this.builder.finishAndSave(path);
	}

	/**
	 * @param item
	 */
	private String[] createRowArray(IItem item)
	{
		String desc = item.getProduct().getDescription().getDescription();
		String storageUnit = item.getContainer().getStorageUnit().getName();
		String productGroup = "";
		if(item.getContainer() instanceof ProductGroup)
			productGroup = item.getContainer().getName();
		String entry = DateUtils.formatDate(item.getEntryDate());
		String exit = DateUtils.formatDate(item.getExpirationDate());
		String barcode = item.getBarcode().getNumber();

		String[] row = {desc, storageUnit, productGroup, entry, exit, barcode};
		return row;
	}
}
