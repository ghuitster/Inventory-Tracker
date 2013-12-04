package model.plugin;

public class basePlugin extends Plugin
{

	@Override
	public String findProduct(String barcode)
	{
		if(this.nextPlugin != null)
			return this.nextPlugin.findProduct(barcode);
		return null;
	}

}
