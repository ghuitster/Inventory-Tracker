
package model;

/**
 * Class to represent the Barcode for an Item
 * @author Michael
 * 
 */
public class Barcode
{
	// Variables
	private String number;

	/**
	 * The Barcode constructor
	 * @param number the unique number that will identify this Barcode
	 */
	public Barcode(String number)
	{
		this.number = number;
	}

	/**
	 * @param number the number to attempt to set
	 * @return whether or not the number can be set
	 */
	public boolean ableToSetNumber(String number)
	{
		return true;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(getClass() != obj.getClass())
		{
			return false;
		}
		Barcode other = (Barcode) obj;
		if(number == null)
		{
			if(other.number != null)
			{
				return false;
			}
		}
		else if(!number.equals(other.number))
		{
			return false;
		}
		return true;
	}

	// Methods
	/**
	 * @return the number
	 */
	public String getNumber()
	{
		return number;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	public static boolean isValid(String number)
	{
		boolean response = false;

		return response;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number)
	{
		this.number = number;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Barcode [number=" + number + "]";
	}

}
