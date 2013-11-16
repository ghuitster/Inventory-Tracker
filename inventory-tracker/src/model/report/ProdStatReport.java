
package model.report;

import java.text.DecimalFormat;
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

	@Override
	public void createReport(String path)
	{
		this.builder.setPath(path);
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

		builder.finishAndSave();
	}

	private String[] createRow(ProductStats stats)
	{
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);

		String desc = stats.getProduct().getDescription().getDescription();
		String barcode = stats.getProduct().getBarcode().getNumber();
		String size = stats.getProduct().getSize().toString();
		String supply =
				df.format((stats.getProduct().getThreeMonthSupply().getAmount()
						* monthsToShow / 3f))
						+ " "
						+ stats.getProduct().getThreeMonthSupply()
								.getUnitType();
		String supplyCurAndAverage =
				df.format(stats.getCurrentSupply()) + " / "
						+ df.format(stats.getAverageSupply());
		String supplyMinAndMax =
				df.format(stats.getMinSupply()) + " / "
						+ df.format(stats.getMaxSupply());
		String supplyUsedAndAdded =
				df.format(stats.getUsedSupply()) + " / "
						+ df.format(stats.getAddedSupply());
		String shelfLife = stats.getProduct().getShelfLife() + " months";
		String usedAgeAvgAndMax =
				stats.getMaxUsedAge() > 0 ? df.format(stats.getAvgUsedAge())
						+ " days / " + df.format(stats.getMaxUsedAge())
						+ " days" : "";
		String curAgeAvgAndMax =
				df.format(stats.getAvgCurrentAge()) + " days / "
						+ df.format(stats.getMaxCurrentAge()) + " days";

		String[] row =
				{desc, barcode, size, supply, supplyCurAndAverage,
						supplyMinAndMax, supplyUsedAndAdded, shelfLife,
						usedAgeAvgAndMax, curAgeAvgAndMax};
		return row;
	}

	// Methods
	/**
	 * Gets the product statistics for this report
	 */
	public List<ProductStats> getProductStats()
	{
		return productStats;
	}
}
