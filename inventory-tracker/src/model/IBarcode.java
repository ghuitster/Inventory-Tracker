
package model;

public interface IBarcode
{

	/**
	 * @param number the number to attempt to set
	 * @return whether or not the number can be set
	 */
	public abstract boolean ableToSetNumber(String number);

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public abstract boolean equals(Object obj);

	// Methods
	/**
	 * @return the number
	 */
	public abstract String getNumber();

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public abstract int hashCode();

	/**
	 * @param number the number to set
	 */
	public abstract void setNumber(String number);

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();

}
