
package model;

/**
 * Describes the supply of a product
 * @author Brian
 * 
 */
public class ProductSupply
{
	private IProduct product;

	private Amount supply;

	public ProductSupply(IProduct product)
	{
		this.product = product;
	}

	public IProduct getProduct()
	{
		return product;
	}

	public Amount getSupply()
	{
		return supply;
	}

	public void setSupply(Amount supply)
	{
		this.supply = supply;
	}
}
