
package model;

/**
 * Describes the supply of a product group
 * @author Brian
 * 
 */
public class ProductGroupSupply
{
	private IProductGroup productGroup;

	private Amount supply;

	public ProductGroupSupply(IProductGroup productGroup)
	{
		this.productGroup = productGroup;
	}

	public IProductGroup getProductGroup()
	{
		return productGroup;
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
