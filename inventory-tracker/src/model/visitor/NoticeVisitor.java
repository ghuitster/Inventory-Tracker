
package model.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IItem;
import model.IProduct;
import model.IProductGroup;
import model.IStorageUnit;
import model.UnitType;

import common.UnitUtils;

/**
 * Visitor which searches for product groups with inconsistent products
 * @author Brian
 * 
 */
public class NoticeVisitor implements IVisitor
{

	private Map<IProductGroup, List<IProduct>> result;

	public NoticeVisitor()
	{
		result = new HashMap<IProductGroup, List<IProduct>>();
	}

	/**
	 * Returns the result of the traversal
	 * @pre The traversal has been run
	 * @post none
	 * @return
	 */
	public Map<IProductGroup, List<IProduct>> getResult()
	{
		return result;
	}

	/**
	 * Does nothing
	 * @pre none
	 * @post none
	 */
	@Override
	public void visitItem(IItem item)
	{}

	/**
	 * Does nothing
	 * @pre none
	 * @post none
	 */
	@Override
	public void visitProduct(IProduct product)
	{}

	/**
	 * Checks if the product group's three month supply is consistent with its
	 * products
	 * @pre group is not null
	 * @post none
	 * @param group The group to check
	 */
	@Override
	public void visitProductGroup(IProductGroup group)
	{
		for(IProduct product: group.getAllProducts())
		{
			UnitType productUnit = product.getThreeMonthSupply().getUnitType();
			UnitType groupUnit = group.getThreeMonthSupply().getUnitType();

			if((productUnit == UnitType.COUNT && groupUnit != UnitType.COUNT)
					|| (UnitUtils.UnitTypeIsVolume(productUnit) && !UnitUtils
							.UnitTypeIsVolume(groupUnit))
					|| (UnitUtils.UnitTypeIsWeight(productUnit) && !UnitUtils
							.UnitTypeIsWeight(groupUnit)))
			{
				List<IProduct> productList;
				if(result.containsKey(group))
					productList = result.get(group);
				else
				{
					productList = new ArrayList<IProduct>();
					result.put(group, productList);
				}
				productList.add(product);
			}
		}
	}

	/**
	 * Does nothing
	 * @pre none
	 * @post none
	 */
	@Override
	public void visitStorageUnit(IStorageUnit unit)
	{}

}
