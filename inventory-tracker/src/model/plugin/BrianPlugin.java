
package model.plugin;

public class BrianPlugin extends Plugin
{
	@Override
	public String findProduct(String barcode)
	{
		String productDescription = null;

		if(productDescription == null && this.nextPlugin != null)
			return this.nextPlugin.findProduct(barcode);
		else
			return productDescription;
	}
}
