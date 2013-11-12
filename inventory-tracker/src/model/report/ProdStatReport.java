
package model.report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import model.ProductStats;

public class ProdStatReport extends Report
{
	// Variables
	private final List<ProductStats> productStats;
	private final int monthsToShow;

	// Constructor
	public ProdStatReport(List<ProductStats> productStats,
			IReportBuilder builder, int monthsToShow)
	{
		super(builder);
		this.productStats = productStats;
		this.monthsToShow = monthsToShow;
	}

	// Methods
	/**
	 * Gets the product statistics for this report
	 */
	public List<ProductStats> getProductStats()
	{
		return productStats;
	}

	@Override
	public void createReport()
	{
		builder.buildHead("Product Report (" + monthsToShow + " Months)");
		String[] columns =
				{"Description", "Barcode", "Size", "3-Month Supply",
						"Supply: Cur/Avg", "Supply: Min/Max",
						"Supply: Used/Added", "Shelf Life",
						"Used Age: Avg/Max", "Cur Age: Avg/Max"};

		builder.startTable(columns);

		for(ProductStats stats: productStats)
			builder.addTableRow(createRow(stats));

		builder.finishTable();
		String timeStamp =
				new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
						.getInstance().getTime());
		String filename =
				"Reports" + File.separator + "ProductStatisticsReport-"
						+ timeStamp + ".pdf";

		builder.finishAndSave(filename);
	}

	private String[] createRow(ProductStats stats)
	{
		String desc = stats.getProduct().getDescription().getDescription();
		String barcode = stats.getProduct().getBarcode().getNumber();
		String size = stats.getProduct().getSize().toString();
		String supply = stats.getProduct().getThreeMonthSupply().toString();
		String supplyCurAndAverage =
				stats.getCurrentSupply() + " / " + stats.getAverageSupply();
		String supplyMinAndMax =
				stats.getMinSupply() + " / " + stats.getMaxSupply();
		String supplyUsedAndAdded =
				stats.getUsedSupply() + " / " + stats.getAddedSupply();
		String shelfLife = stats.getProduct().getShelfLife() + " months";
		String usedAgeAvgAndMax =
				stats.getAvgUsedAge() + " days / " + stats.getMaxUsedAge()
						+ " days";
		String curAgeAvgAndMax =
				stats.getAvgCurrentAge() + " days / "
						+ stats.getMaxCurrentAge() + " days";

		String[] row =
				{desc, barcode, size, supply, supplyCurAndAverage,
						supplyMinAndMax, supplyUsedAndAdded, shelfLife,
						shelfLife, usedAgeAvgAndMax, curAgeAvgAndMax};
		return row;
	}
}
