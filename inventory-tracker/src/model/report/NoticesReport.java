
package model.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	@Override
	public void createReport(String path)
	{
		this.builder.setPath(path);
		this.builder.buildHead("Notices");

		System.out.println(this.inconsistentGroups.isEmpty());

		if(!this.inconsistentGroups.isEmpty())
		{
			this.builder.addSectionHeader("3-Month Supply Warnings");
			Set<IProductGroup> keySet = this.inconsistentGroups.keySet();

			// IProductGroup[] keys =
			// (ProductGroup[]) this.inconsistentGroups.keySet().toArray();

			for(IProductGroup prodGroup: keySet)
			{
				String paragraph =
						"Product group "
								+ prodGroup.getName()
								+ " has a 3-month supply ("
								+ prodGroup.getThreeMonthSupply().toString()
								+ ") that is inconsistent with the following products:";

				this.builder.addText(paragraph);

				List<IProduct> products =
						this.inconsistentGroups.get(prodGroup);
				ArrayList<String> prodStrings = new ArrayList<String>();
				for(IProduct product: products)
				{
					String prodString =
							product.getDescription().getDescription()
									+ " (size: " + product.getSize().toString()
									+ ")";
					prodStrings.add(prodString);
				}

				String[] temp = new String[prodStrings.size()];
				for(int i = 0; i < temp.length; i++)
				{
					temp[i] = prodStrings.get(i);
				}

				this.builder.addBulletedList(temp);
			}
		}
		else
		{
			this.builder.addText("There are no notices at this time.");
		}

		this.builder.finishAndSave();
	}

	/**
	 * Gets the inconsistent groups for this report, mapped to the offending
	 * products
	 */
	public Map<IProductGroup, List<IProduct>> getInconsistentGroups()
	{
		throw new UnsupportedOperationException();
	}
}
