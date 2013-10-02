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
public class ProductDescription implements Serializable
{
	// Variables
	private static final long serialVersionUID = -4601997220157737995L;

	private String description;

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

	/**
	 * @precondition descriptoin != null
	 * 
	 */
	public ProductDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @precondition description != null
	 * @param description the description to check for validity
	 * @return
	 */
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

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @see java.lang.Object#hashCode()
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

	/**
	 * @precondition description != null
	 * @postcondition description == passed in description
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductDescription [description=" + description + "]";
	}

}
