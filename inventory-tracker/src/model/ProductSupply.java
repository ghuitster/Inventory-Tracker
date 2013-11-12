
package model;

/**
 * Describes the supply of a product
 * @author Brian
 * 
 */
public class ProductSupply
{
	private IProduct product;

	private CountAmount supply;

	public ProductSupply(IProduct product)
	{
		this.product = product;
	}

	public IProduct getProduct()
	{
		return product;
	}

	public CountAmount getSupply()
	{
		return supply;
	}

	public void setSupply(CountAmount supply)
	{
		this.supply = supply;
	}
}
