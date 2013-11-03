package model;

/**
 * Describes the supply of a product
 * @author Brian
 *
 */
public class ProductSupply
{
	public ProductSupply(IProduct product)
	{
		this.product = product;
	}
	
	private IProduct product;
	
	private Amount supply;

	public Amount getSupply()
	{
		return supply;
	}

	public void setSupply(Amount supply)
	{
		this.supply = supply;
	}

	public IProduct getProduct()
	{
		return product;
	}
}
