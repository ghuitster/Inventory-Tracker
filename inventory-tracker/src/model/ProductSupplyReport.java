package model;

import java.util.List;

public class ProductSupplyReport
{
	
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

}
