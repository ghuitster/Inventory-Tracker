package model;

/**
 * Describes the supply of a product group
 * @author Brian
 *
 */
public class ProductGroupSupply
{
	public ProductGroupSupply(IProductGroup productGroup)
	{
		this.productGroup = productGroup;
	}
	
	private IProductGroup productGroup;
	
	private Amount supply;

	public Amount getSupply()
	{
		return supply;
	}

	public void setSupply(Amount supply)
	{
		this.supply = supply;
	}

	public IProductGroup getProductGroup()
	{
		return productGroup;
	}
}
