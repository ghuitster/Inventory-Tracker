
package model;

/**
 * Describes various properties of removed items
 * @author Brian
 * 
 */
public class RemovedItems
{
	private IProduct product;

	private int count;
	private int supply;

	public RemovedItems(IProduct product)
	{
		this.product = product;
	}

	public int getCount()
	{
		return count;
	}

	public IProduct getProduct()
	{
		return product;
	}

	public int getSupply()
	{
		return supply;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public void setSupply(int supply)
	{
		this.supply = supply;
	}
}
