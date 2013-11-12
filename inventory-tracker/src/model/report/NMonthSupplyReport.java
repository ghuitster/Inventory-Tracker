
package model.report;

import java.util.List;

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

	@Override
	public void createReport()
	{
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
			String product =
					prodSupply.getProduct().getDescription().getDescription();
			String barcode = prodSupply.getProduct().getBarcode().getNumber();
		}
	}
}
