
package model;

import java.io.Serializable;

/**
 * Class to represent the Barcode for an Item
 * @author Michael
 * 
 */
public abstract class Barcode implements Serializable, IBarcode
{
	// Variables
	private static final long serialVersionUID = -4409370215399749663L;

	/**
	 * @pre number must not be null
	 * @param number string of the barcode number to validate
	 * @return true if valid number
	 */
	public abstract boolean isValid(String number);

	private String number;

	/**
	 * The Barcode constructor
	 * @param number the unique number that will identify this Barcode
	 */
	public Barcode(String number)
	{
		this.number = number;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IBarcode#ableToSetNumber(java.lang.String)
	 */
	@Override
	public boolean ableToSetNumber(String number)
	{
		boolean response = false;

		if(number != null)
		{
			response = this.isValid(number);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IBarcode#equals(java.lang.Object)
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IBarcode#getNumber()
	 */
	@Override
	public String getNumber()
	{
		return number;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IBarcode#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IBarcode#setNumber(java.lang.String)
	 */
	@Override
	public void setNumber(String number)
	{
		this.number = number;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IBarcode#toString()
	 */
	@Override
	public String toString()
	{
		return "Barcode [number=" + number + "]";
	}

}
