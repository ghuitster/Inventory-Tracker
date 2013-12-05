
package model.plugin;

import java.io.*;
import java.net.*;

public class FirePlugin extends Plugin
{

	@Override
	public String findProduct(String barcode)
	{
		String temp = "http://us.api.invisiblehand.co.uk/v1/products?app_id=e0ae8abf&app_key=22037da7bfd4271f4cd774b585275790&upc=" + barcode;
		BufferedReader rd;
		try
		{
			HttpURLConnection conn = this.makeHTTPConnection(temp);
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
