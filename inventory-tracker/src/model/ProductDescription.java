/**
 * 
 */

package model;

import java.io.Serializable;

/**
 * Class to represent a Product description
 * @author Michael
 * 
 */
public class ProductDescription implements Serializable, IProductDescription
{
	// Variables
	private static final long serialVersionUID = -4601997220157737995L;

	/**
	 * @pre description != null
	 * @post return true if description is valid
	 * @param description
	 * @return
	 */
	public static boolean isValid(String description)
	{
		boolean response = false;

		if(!description.isEmpty() && !description.trim().isEmpty())
			response = true;

		return response;
	}

	private String description;

	/**
	 * @pre descriptoin != null
	 * 
	 */
	public ProductDescription(String description)
	{
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductDescription#ableToSetDescription(java.lang.String)
	 */
	@Override
	public boolean ableToSetDescription(String description)
	{
		boolean response = true;

		if(description == null)
			response = false;
		else if(description.isEmpty())
			response = false;
		else if(!description.matches("\\S"))
			response = false;

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductDescription#equals(java.lang.Object)
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
		ProductDescription other = (ProductDescription) obj;
		if(description == null)
		{
			if(other.description != null)
			{
				return false;
			}
		}
		else if(!description.equals(other.description))
		{
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductDescription#getDescription()
	 */
	@Override
	public String getDescription()
	{
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductDescription#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result =
				prime * result
						+ ((description == null) ? 0 : description.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductDescription#toString()
	 */
	@Override
	public String toString()
	{
		return description;
	}
	
	@Override
	public int getId()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
