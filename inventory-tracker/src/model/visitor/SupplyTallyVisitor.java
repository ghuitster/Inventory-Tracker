
package model.visitor;

import model.Amount;
import model.CountAmount;
import model.IItem;
import model.IProduct;
import model.IProductGroup;
import model.IStorageUnit;
import model.NonCountAmount;
import model.UnitType;
import model.exception.InvalidUnitTypeException;

import common.UnitUtils;

public class SupplyTallyVisitor implements IVisitor
{
	private int totalCount;
	private float pounds;
	private float gallons;

	public SupplyTallyVisitor()
	{
		totalCount = 0;
		pounds = 0;
		gallons = 0;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public float getVolume(UnitType unitType) throws InvalidUnitTypeException
	{
		return UnitUtils.convertFromGallons(gallons, unitType);
	}

	public float getWeight(UnitType unitType) throws InvalidUnitTypeException
	{
		return UnitUtils.convertFromPounds(pounds, unitType);
	}

	@Override
	public void visitItem(IItem item)
	{
		Amount size = item.getProduct().getSize();
		UnitType itemType = size.getUnitType();
		if(itemType == UnitType.COUNT)
		{
			CountAmount count = (CountAmount) size;
			this.totalCount += count.getAmount();
		}
		else
		{
			NonCountAmount count = (NonCountAmount) size;
			if(UnitUtils.UnitTypeIsWeight(itemType))
			{
				this.pounds +=
						UnitUtils.convertToPounds(count.getAmount(), itemType);
			}
			else if(UnitUtils.UnitTypeIsVolume(itemType))
			{
				this.gallons +=
						UnitUtils.convertToGallons(count.getAmount(), itemType);
			}
		}
	}

	@Override
	public void visitProduct(IProduct product)
	{}

	@Override
	public void visitProductGroup(IProductGroup group)
	{}

	@Override
	public void visitStorageUnit(IStorageUnit unit)
	{}
}
