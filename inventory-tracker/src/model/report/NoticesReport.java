
package model.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.IProduct;
import model.IProductContainer;
import model.IProductGroup;

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

	}

	/**
	 * Gets the inconsistent groups for this report, mapped to the offending
	 * products
	 */
	public Map<IProductContainer, List<IProduct>> getInconsistentGroups()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void createReport(String path)
	{
		this.builder.buildHead("Notices");
		this.builder.addSectionHeader("3-Month Supply Warnings");

		IProductContainer[] keys =
				(IProductContainer[]) this.inconsistentGroups.keySet()
						.toArray();

		for(IProductContainer key: keys)
		{
			IProductGroup prodGroup = null;
			if(key instanceof IProductGroup)
			{
				prodGroup = (IProductGroup) key;
			}
			else
				continue;

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
