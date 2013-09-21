
package tools;

public class Importer
{

	public static void main(String[] args)
	{
		usage();
	}

	private static void print(String message)
	{
		System.out.println(message);
	}

	private static void usage()
	{
		print("USAGE: java tools.Importer [-sql] <data-file>");
	}

}
