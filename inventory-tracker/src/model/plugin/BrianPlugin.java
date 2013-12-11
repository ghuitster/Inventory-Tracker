
package model.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BrianPlugin extends Plugin
{
	@Override
	public String findProduct(String barcode)
	{
		String productDescription = null;

		try
		{
			URL destination =
					new URL("http://www.searchupc.com/handlers/upcsearch.ashx?"
							+ "request_type=3&access_token=B4C153DB-8929-4655-"
							+ "8377-DE75D2B3E637&upc=" + barcode);
			HttpURLConnection connection =
					(HttpURLConnection) destination.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();

			BufferedReader input =
					new BufferedReader(new InputStreamReader(
							connection.getInputStream()));

			String line;
			String response = "";

			while((line = input.readLine()) != null)
				response += line;

			if(!response.isEmpty())
			{
				int indexOfDesc = response.indexOf("productname\":\"") + 14;
				if(indexOfDesc >= 14)
				{
					int descEnd = response.indexOf("\"", indexOfDesc);
					productDescription =
							response.substring(indexOfDesc, descEnd);
					if(productDescription.trim().equals(""))
						productDescription = null;
				}
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		if(productDescription == null && this.nextPlugin != null)
			return this.nextPlugin.findProduct(barcode);
		else
			return productDescription;
	}
}
