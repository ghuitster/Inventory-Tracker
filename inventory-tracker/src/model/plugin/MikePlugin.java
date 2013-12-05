
package model.plugin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

public class MikePlugin extends Plugin
{
	// Variables
	private final String BASE_URL =
			"http://api.upcdatabase.org/json/16ae5fc3774baf93a452e21353a1fb75/";

	// Constructor

	// Methods

	@Override
	public String findProduct(String barcode)
	{
		URL url = null;
		HttpURLConnection connection = null;
		String productDescription = null;

		try
		{
			url = new URL(this.BASE_URL + barcode);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();

			JsonParserFactory jpf = JsonParserFactory.getInstance();
			JSONParser jParser = jpf.newJsonParser();

			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				String response = connection.getResponseMessage();

				Map jsonData = jParser.parseJson(response);

				System.out.println(jsonData.get("valid"));
			}

		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
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
