
package model;

public interface IProductGroup
{

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public abstract boolean equals(Object obj);

	/**
	 * @return the container
	 */
	public abstract BaseProductContainer getContainer();

	/**
	 * @return the threeMonthSupply
	 */
	public abstract ThreeMonthSupply getThreeMonthSupply();

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public abstract int hashCode();

	/**
	 * @pre container != null
	 * @post my.container == passed in ProductContainer
	 * @param container the ProductContainer to set
	 */
	public abstract void setContainer(BaseProductContainer container);

	/**
	 * @pre threeMonthSupply must be a valid ThreeMonthSupply and not null
	 * @post my.threeMonthSupply == passed in ThreeMonthSupply
	 * @param threeMonthSupply the ThreeMonthSupply to set
	 */
	public abstract void setThreeMonthSupply(ThreeMonthSupply threeMonthSupply);

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();

}
