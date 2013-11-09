
package model.report;

import java.util.List;
import java.util.Map;

import model.IProduct;
import model.IProductContainer;

public class NoticesReport extends Report
{
	// Variables
	private Map<IProductContainer, List<IProduct>> inconsistentGroups = null;

	public NoticesReport(
			Map<IProductContainer, List<IProduct>> inconsistentGroups,
			IReportBuilder builder)
	{
		super(builder);
		this.inconsistentGroups = inconsistentGroups;

		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the inconsistent groups for this report, mapped to the offending
	 * products
	 */
	public Map<IProductContainer, List<IProduct>> getInconsistentGroups()
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
