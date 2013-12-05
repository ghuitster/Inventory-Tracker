
package model.plugin;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Scanner;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

public class FirePlugin extends Plugin
{

	@Override
	public String findProduct(String barcode)
	{
		String temp = "http://us.api.invisiblehand.co.uk/v1/products?app_id=e0ae8abf&app_key=22037da7bfd4271f4cd774b585275790&upc=" + barcode;
		BufferedReader rd;
		String results = "";
		try
		{
			HttpURLConnection conn = this.makeHTTPConnection(temp);
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = rd.readLine()) != null)
			{
				results += line;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println(results);
		String description = this.getDescriptionFromJSON(results);
		if(description == null && this.nextPlugin != null)
			return this.nextPlugin.findProduct(barcode); 
		else
			return description;
	}
	
	private String getDescriptionFromJSON(String results)
	{
		int index = results.indexOf("total_results");
		String temp = results.substring(index);
		Scanner scan = new Scanner(temp);
		scan.useDelimiter(",");
		String total_results = scan.next();
//		JsonParserFactory factory = JsonParserFactory.getInstance();
//		JSONParser parser = factory.newJsonParser();
//		Map<String, String> jsonData = parser.parseJson(results);
//		System.out.println(jsonData.toString());
//		int numberOfResults = Integer.parseInt(jsonData.get("total_results"));
//		System.out.println(numberOfResults);
//		if(numberOfResults > 0)
//			return jsonData.get("title");
//		return null;
		return null;
	}

	private HttpURLConnection makeHTTPConnection(String request) throws IOException
	{
		URL url = new URL(request);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		return conn;
	}

}
