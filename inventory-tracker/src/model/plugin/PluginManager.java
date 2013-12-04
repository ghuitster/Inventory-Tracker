
package model.plugin;

public class PluginManager
{
	private Plugin firstPlugin = null;
	private String configFilePath = null;
	/**
	 * constructor
	 */
	public PluginManager(String filePath)
	{
		this.configFilePath = filePath;
		this.loadConfig();
	}

	/**
	 * @pre the plugins are loaded in
	 * @post returns the first plugin
	 * @return the first plugin in the chain
	 */
	public Plugin getFirstPlugin()
	{
		return this.firstPlugin;
	}

	/**
	 * @pre must have reference to valid config file
	 * @post config will load the plugins
	 */
	private void loadConfig()
	{
		
	}
}
