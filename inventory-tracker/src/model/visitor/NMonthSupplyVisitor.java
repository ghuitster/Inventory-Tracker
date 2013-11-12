package model.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import common.UnitUtils;

import model.CountThreeMonthSupply;
import model.IItem;
import model.IProduct;
import model.IProductGroup;
import model.IStorageUnit;
import model.Inventory;
import model.ProductGroupSupply;
import model.ProductSupply;
import model.UnitType;

public class NMonthSupplyVisitor implements IVisitor
{
	public NMonthSupplyVisitor(int months)
	{
		groupSupplies = new ArrayList<ProductGroupSupply>();
		productSupplies = new ArrayList<ProductSupply>();
		
		this.months = months;
	}
	
	private List<ProductGroupSupply> groupSupplies;
	private List<ProductSupply> productSupplies;
	
	public List<ProductGroupSupply> getGroupSupplies()
	{
		return groupSupplies;
	}
	public List<ProductSupply> getProductSupplies()
	{
		return productSupplies;
	}
	
	private int months;
	
	public int getMonths()
	{
		return months;
	}
	
	@Override
	public void visitItem(IItem item)
	{ }
	
	private boolean productAlreadyVisited(IProduct product)
	{
		for(ProductSupply supply : productSupplies)
		{
			if(supply.getProduct() == product)
				return true;
		}
		return false;
	}
	
	@Override
	public void visitProduct(IProduct product)
	{
		if(!productAlreadyVisited(product))
		{
			SortedSet<IItem> items = Inventory.getInstance().getAllItems(product);

			float nMonthSupply = product.getThreeMonthSupply().getAmount() * (months * .33333f);
			
			if(nMonthSupply > items.size())
			{
				ProductSupply supply = new ProductSupply(product);
				supply.setSupply(new CountThreeMonthSupply(items.size()));
				productSupplies.add(supply);
			}
		}
	}
	
	@Override
	public void visitProductGroup(IProductGroup group)
	{
		SupplyTallyVisitor visitor = new SupplyTallyVisitor();
		group.traverse(visitor);
		UnitType unitType = group.getThreeMonthSupply().getUnitType();
		if(unitType == UnitType.COUNT)
		{
			
		}
		else if(UnitUtils.UnitTypeIsWeight(unitType))
		{
			
		}
		else if(UnitUtils.UnitTypeIsVolume(unitType))
		{
			
		}
	}
	
	@Override
	public void visitStorageUnit(IStorageUnit unit)
	{ }
	
	
}
