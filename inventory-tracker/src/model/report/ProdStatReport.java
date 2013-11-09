
package model.report;

import java.util.List;

import model.ProductStats;

public class ProdStatReport extends Report
{
	// Variables
	private final List<ProductStats> productStats;

	// Constructor
	public ProdStatReport(List<ProductStats> productStats,
			IReportBuilder builder)
	{
		super(builder);
		this.productStats = productStats;
		// TODO Auto-generated constructor stub
	}

	// Methods
	/**
	 * Gets the product statistics for this report
	 */
	public List<ProductStats> getProductStats()
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
