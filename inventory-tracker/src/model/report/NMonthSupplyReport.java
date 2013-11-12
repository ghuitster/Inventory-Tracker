
package model.report;

import java.util.List;

import model.ProductGroupSupply;
import model.ProductSupply;

public class NMonthSupplyReport extends Report
{
	// Variables
	private List<ProductSupply> productSupplyInfo = null;
	private List<ProductGroupSupply> productGroupSupplyInfo = null;

	// Constructor
	public NMonthSupplyReport(List<ProductSupply> productSupplies,
			List<ProductGroupSupply> productGroupSupplies,
			IReportBuilder builder)
	{
		super(builder);
		this.productSupplyInfo = productSupplies;
		this.productGroupSupplyInfo = productGroupSupplies;
	}

	// Methods
	/**
	 * Gets the product group information for this report
	 */
	public List<ProductGroupSupply> getProductGroupSupplyInfo()
	{
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets the product information for this report
	 */
	public List<ProductSupply> getProductSupplyInfo()
	{
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void createReport()
	{
		// TODO Auto-generated method stub

	}
}
