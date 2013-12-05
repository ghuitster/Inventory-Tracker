
package model.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PluginManager
{
	// Variables
	private final Plugin firstPlugin = new BasePlugin();
	private final String configFilePath = "Plugin.conf";

	/**
	 * constructor
	 */
	public PluginManager()
	{
		System.out.println("using config file: " + this.configFilePath);
		this.loadConfig(this.configFilePath);
	}

	// Methods
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
	 * @param filePath
	 * @pre must have reference to valid config file
	 * @post config will load the plugins
	 */
	private void loadConfig(String filePath)
	{
		List<Plugin> pList = new ArrayList<Plugin>();
		try
		{
			Scanner scan = new Scanner(new File(filePath));
			while(scan.hasNextLine())
			{
				String temp = scan.nextLine();
				Class c = null;
				try
				{
					c = Class.forName(temp);
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}

				Plugin p = null;
				try
				{
					p = (Plugin) c.newInstance();
				}
				catch(InstantiationException e)
				{
					e.printStackTrace();
				}
				catch(IllegalAccessException e)
				{
					e.printStackTrace();
				}
				pList.add(p);
			}

			scan.close();
			this.firstPlugin.setNext(pList.get(0));
			for(int i = 1; i < pList.size(); i++)
			{
				pList.get(i - 1).setNext(pList.get(i));
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
