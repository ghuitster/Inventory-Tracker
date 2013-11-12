
package model.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.IProduct;
import model.IProductGroup;

public class NoticesReport extends Report
{
	// Variables
	private Map<IProductGroup, List<IProduct>> inconsistentGroups = null;

	public NoticesReport(Map<IProductGroup, List<IProduct>> inconsistentGroups,
			IReportBuilder builder)
	{
		super(builder);
		this.inconsistentGroups = inconsistentGroups;

	}

	/**
	 * Gets the inconsistent groups for this report, mapped to the offending
	 * products
	 */
	public Map<IProductGroup, List<IProduct>> getInconsistentGroups()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void createReport(String path)
	{
		this.builder.buildHead("Notices");
		this.builder.addSectionHeader("3-Month Supply Warnings");

		IProductGroup[] keys =
				(IProductGroup[]) this.inconsistentGroups.keySet().toArray();

		for(IProductGroup prodGroup: keys)
		{
			String paragraph =
					"Product group "
							+ prodGroup.getName()
							+ " has a 3-month supply ("
							+ prodGroup.getThreeMonthSupply().toString()
							+ ") that is inconsistent with the following products:";

			this.builder.addText(paragraph);

			List<IProduct> products = this.inconsistentGroups.get(prodGroup);
			ArrayList<String> prodStrings = new ArrayList<String>();
			for(IProduct product: products)
			{
				String prodString =
						product.getDescription().getDescription() + " (size: "
								+ product.getSize().toString() + ")";
				prodStrings.add(prodString);
			}

			this.builder.addBulletedList((String[]) prodStrings.toArray());
		}

		this.builder.finishAndSave(path);
	}
}
