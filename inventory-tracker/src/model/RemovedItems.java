package model;

/**
 * Describes various properties of removed items
 * @author Brian
 *
 */
public class RemovedItems
{
	public RemovedItems(IProduct product)
	{
		this.product = product;
	}
	
	private IProduct product;
	private int count;
	private int supply;
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public int getSupply()
	{
		return supply;
	}
	public void setSupply(int supply)
	{
		this.supply = supply;
	}
	public IProduct getProduct()
	{
		return product;
	}
}
