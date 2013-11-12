package model;

import java.util.List;

public class ProductSupplyReport
{
	private int months;
	public ProductSupplyReport(int months)
	{
		this.months = months;
	}
	
	public List<ProductGroupSupply> getGroupSupplies()
	{
		return groupSupplies;
	}
	public void setGroupSupplies(List<ProductGroupSupply> groupSupplies)
	{
		this.groupSupplies = groupSupplies;
	}
	public List<ProductSupply> getProductSupplies()
	{
		return productSupplies;
	}
	public void setProductSupplies(List<ProductSupply> productSupplies)
	{
		this.productSupplies = productSupplies;
	}
	List<ProductGroupSupply> groupSupplies;
	List<ProductSupply> productSupplies;

	
	public int getMonths()
	{
		return months;
	}
}
