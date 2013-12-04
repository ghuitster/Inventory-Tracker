
package model.plugin;

public abstract class Plugin
{
	protected Plugin nextPlugin;

	/**
	 * 
	 * @param barcode
	 * @return list of String in the order in the view
	 * @pre barcode is not null
	 * @post null if no product is found or a list of the data
	 */
	public abstract String findProduct(String barcode);

	/**
	 * @pre plugin is not null
	 * @post plugin is stored
	 * @param plugin
	 */
	public void setNext(Plugin plugin)
	{
		this.nextPlugin = plugin;
	}
}
