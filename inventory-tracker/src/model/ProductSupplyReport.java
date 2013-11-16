
package model;

import java.util.List;

public class ProductSupplyReport
{
	private int months;

	List<ProductGroupSupply> groupSupplies;

	List<ProductSupply> productSupplies;

	public ProductSupplyReport(int months)
	{
		this.months = months;
	}

	public List<ProductGroupSupply> getGroupSupplies()
	{
		return groupSupplies;
	}

	public int getMonths()
	{
		return months;
	}

	public List<ProductSupply> getProductSupplies()
	{
		return productSupplies;
	}
	public void setGroupSupplies(List<ProductGroupSupply> groupSupplies)
	{
		this.groupSupplies = groupSupplies;
	}

	public void setProductSupplies(List<ProductSupply> productSupplies)
	{
		this.productSupplies = productSupplies;
	}
}
