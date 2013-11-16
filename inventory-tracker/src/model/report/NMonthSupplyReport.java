
package model.report;

import java.util.List;

import model.CountAmount;
import model.NonCountAmount;
import model.ProductGroupSupply;
import model.ProductSupply;
import model.ProductSupplyReport;

public class NMonthSupplyReport extends Report
{
	// Variables
	private List<ProductSupply> productSupplyInfo = null;
	private List<ProductGroupSupply> productGroupSupplyInfo = null;
	private ProductSupplyReport productSupplyReport = null;

	// Constructor
	public NMonthSupplyReport(ProductSupplyReport productSupplyReport,
			IReportBuilder builder)
	{
		super(builder);
		this.productSupplyReport = productSupplyReport;
		this.productSupplyInfo = this.productSupplyReport.getProductSupplies();
		this.productGroupSupplyInfo =
				this.productSupplyReport.getGroupSupplies();
	}

	@Override
	public void createReport(String path)
	{
		this.builder.setPath(path);
		String title =
				this.productSupplyReport.getMonths() + "-Month Supply Report";
		this.builder.buildHead(title);

		this.builder.addSectionHeader("Products");

		String[] columns = new String[4];
		columns[0] = "Description";
		columns[1] = "Barcode";
		columns[2] = this.productSupplyReport.getMonths() + "-Month Supply";
		columns[3] = "Current Supply";
		this.builder.startTable(columns);

		for(ProductSupply prodSupply: this.productSupplyInfo)
		{
			System.out.println(prodSupply.getProduct().getDescription()
					.getDescription());
			String product =
					prodSupply.getProduct().getDescription().getDescription();
			String barcode = prodSupply.getProduct().getBarcode().getNumber();
			double nSupply =
					(this.productSupplyReport.getMonths()
							* prodSupply.getProduct().getThreeMonthSupply()
									.getAmount() / 3.0);
			String nMonthsSupply =
					""
							+ Math.round(nSupply)
							+ " "
							+ prodSupply.getProduct().getThreeMonthSupply()
									.getUnitType().toString();
			String currentSupply = prodSupply.getSupply().toString();

			String[] cells = {product, barcode, nMonthsSupply, currentSupply};

			this.builder.addTableRow(cells);
		}

		this.builder.finishTable();

		this.builder.addSectionHeader("Product Groups");
		columns[1] = "Storage Unit";
		this.builder.startTable(columns);

		for(ProductGroupSupply prodGroupSupply: this.productGroupSupplyInfo)
		{
			String productGroup = prodGroupSupply.getProductGroup().getName();
			String stoUnit =
					prodGroupSupply.getProductGroup().getStorageUnit()
							.getName();

			String nMonthsSupply = "";

			if(prodGroupSupply.getProductGroup().getThreeMonthSupply() instanceof NonCountAmount)
			{
				NonCountAmount supply =
						(NonCountAmount) prodGroupSupply.getProductGroup()
								.getThreeMonthSupply();
				double nSupply =
						(this.productSupplyReport.getMonths()
								* supply.getAmount() / 3.0);

				nMonthsSupply =
						"" + Math.round(nSupply) + " "
								+ supply.getUnitType().toString();
			}
			else
			{
				CountAmount supply =
						(CountAmount) prodGroupSupply.getProductGroup()
								.getThreeMonthSupply();
				double nSupply =
						(this.productSupplyReport.getMonths()
								* supply.getAmount() / 3.0);

				nMonthsSupply = "" + Math.round(nSupply) + " count";
			}

			String currentSupply = prodGroupSupply.getSupply().toString();

			String[] cells =
					{productGroup, stoUnit, nMonthsSupply, currentSupply};
			this.builder.addTableRow(cells);
		}

		this.builder.finishTable();

		this.builder.finishAndSave();
	}

	// Methods
	/**
	 * Gets the product group information for this report
	 */
	public List<ProductGroupSupply> getProductGroupSupplyInfo()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets the product information for this report
	 */
	public List<ProductSupply> getProductSupplyInfo()
	{
		throw new UnsupportedOperationException();
	}
}
