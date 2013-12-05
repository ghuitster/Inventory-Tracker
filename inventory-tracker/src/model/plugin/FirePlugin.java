
package model.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirePlugin extends Plugin
{

	@Override
	public String findProduct(String barcode)
	{
		String temp =
				"http://us.api.invisiblehand.co.uk/v1/products?app_id=e0ae8abf&app_key=22037da7bfd4271f4cd774b585275790&upc="
						+ barcode;
		BufferedReader rd;
		String results = "";
		try
		{
			HttpURLConnection conn = this.makeHTTPConnection(temp);
			System.out.println(conn.getResponseCode() + " barcode: " + barcode);
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				rd =
						new BufferedReader(new InputStreamReader(
								conn.getInputStream()));
				String line;
				while((line = rd.readLine()) != null)
				{
					results += line;
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		String description = this.getDescriptionFromJSON(results);
		if(description == null && this.nextPlugin != null)
			return this.nextPlugin.findProduct(barcode);
		else
			return description;
	}

	private String getDescriptionFromJSON(String results)
	{
		String compare = "\"title\":\"";
		String temp = "";
		if(results.toLowerCase().contains(compare))
		{
			int startIndex = results.toLowerCase().indexOf("\"title\":\"");
			int endIndex = results.toLowerCase().indexOf("\",", startIndex);
			temp = results.substring(startIndex + compare.length(), endIndex);
		}
		if(temp.isEmpty())
			return null;
		return temp;
	}

	private HttpURLConnection makeHTTPConnection(String request)
			throws IOException
	{
		URL url = new URL(request);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		return conn;
	}

}
