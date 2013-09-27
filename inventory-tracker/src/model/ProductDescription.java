/**
 * 
 */

package model;

/**
 * Class to represent a Product description
 * @author Michael
 * 
 */
public class ProductDescription
{
	/**
	 * @precondition description != null
	 * @postcondition return true if description is valid
	 * @param description
	 * @return
	 */
	public static boolean isValid(String description)
	{
		return true;
	}

	// Variables
	private String description;

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
