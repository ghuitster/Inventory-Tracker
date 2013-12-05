
package model.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DavidPlugin extends Plugin
{
	@Override
	public String findProduct(String barcode)
	{
		String productDescription = null;

		try
		{
			URL destination =
					new URL("http://www.upcdatabase.com/item/" + barcode);
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

			if(response.toLowerCase().contains("description</td><td></td><td>"))
			{
				int startingPoint =
						response.toLowerCase().indexOf(
								"description</td><td></td><td>");

				startingPoint += "description</td><td></td><td>".length();

				int endingPoint =
						response.toLowerCase().indexOf("</td></tr>",
								startingPoint);

				productDescription =
						response.substring(startingPoint, endingPoint);
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
