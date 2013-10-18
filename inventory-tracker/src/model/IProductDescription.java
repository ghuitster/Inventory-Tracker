
package model;

public interface IProductDescription
{

	/**
	 * @pre description != null
	 * @param description the description to check for validity
	 * @return
	 */
	public abstract boolean ableToSetDescription(String description);

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public abstract boolean equals(Object obj);

	/**
	 * @return the description
	 */
	public abstract String getDescription();

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public abstract int hashCode();

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();

}
