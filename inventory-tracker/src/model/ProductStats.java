
package model;

/**
 * Describes various statistics for a product
 * @author Brian
 * 
 */
public class ProductStats
{
	private IProduct product;

	private int currentSupply;
	private double averageSupply;
	private int minSupply;
	private int maxSupply;
	private int usedSupply;
	private int addedSupply;
	private double avgUsedAge;
	private int maxUsedAge;
	private double avgCurrentAge;
	private int maxCurrentAge;
	/**
	 * Creates a new instance of this class
	 * @pre none
	 * @post none
	 * @param product The product being described
	 */
	public ProductStats(IProduct product)
	{
		this.product = product;
	}

	/**
	 * Gets the added supply variable
	 * @pre none
	 * @post none
	 * @return The added supply variable
	 */
	public int getAddedSupply()
	{
		return addedSupply;
	}

	/**
	 * Gets the average supply variable
	 * @pre none
	 * @post none
	 * @return The average supply variable
	 */
	public double getAverageSupply()
	{
		return averageSupply;
	}

	/**
	 * Gets the average current age variable
	 * @pre none
	 * @post none
	 * @return The average current age variable
	 */
	public double getAvgCurrentAge()
	{
		return avgCurrentAge;
	}

	/**
	 * Gets the average used age variable
	 * @pre none
	 * @post none
	 * @return The average used age variable
	 */
	public double getAvgUsedAge()
	{
		return avgUsedAge;
	}

	/**
	 * Gets the current supply variable
	 * @pre none
	 * @post none
	 * @return The current supply variable
	 */
	public int getCurrentSupply()
	{
		return currentSupply;
	}

	/**
	 * Gets the max current age
	 * @pre none
	 * @post none
	 * @return The max current age
	 */
	public int getMaxCurrentAge()
	{
		return maxCurrentAge;
	}

	/**
	 * Gets the max supply variable
	 * @pre none
	 * @post none
	 * @return The max supply variable
	 */
	public int getMaxSupply()
	{
		return maxSupply;
	}

	/**
	 * Gets the max used age variable
	 * @pre none
	 * @post none
	 * @return The max used age variable
	 */
	public int getMaxUsedAge()
	{
		return maxUsedAge;
	}

	/**
	 * Gets the minimum supply variable
	 * @pre none
	 * @post none
	 * @return The minimum supply variable
	 */
	public int getMinSupply()
	{
		return minSupply;
	}

	/**
	 * Gets the product these stats are referring to
	 * @return The product these stats are referring to
	 */
	public IProduct getProduct()
	{
		return product;
	}

	/**
	 * Gets the used supply variable
	 * @pre none
	 * @post none
	 * @return The used supply variable
	 */
	public int getUsedSupply()
	{
		return usedSupply;
	}

	/**
	 * Sets the added supply variable
	 * @pre none
	 * @post The added supply variable has been set
	 * @param addedSupply The new value
	 */
	public void setAddedSupply(int addedSupply)
	{
		this.addedSupply = addedSupply;
	}

	/**
	 * Sets the average supply variable
	 * @param averageSupply The new value
	 * @pre none
	 * @post averageSupply has been set
	 */
	public void setAverageSupply(double averageSupply)
	{
		this.averageSupply = averageSupply;
	}

	/**
	 * Sets the average current age variable
	 * @pre none
	 * @post The average current age variable has been changed
	 * @param avgCurrentAge The new value
	 */
	public void setAvgCurrentAge(double avgCurrentAge)
	{
		this.avgCurrentAge = avgCurrentAge;
	}

	/**
	 * Sets the average used age variable
	 * @pre none
	 * @post The average used age variable has been set
	 * @param avgUsedAge The new value
	 */
	public void setAvgUsedAge(double avgUsedAge)
	{
		this.avgUsedAge = avgUsedAge;
	}

	/**
	 * sets the current supply variable
	 * @param currentSupply New value
	 * @pre none
	 * @post currentSupply has been set
	 */
	public void setCurrentSupply(int currentSupply)
	{
		this.currentSupply = currentSupply;
	}

	/**
	 * Sets the max current age
	 * @pre none
	 * @post The max current age has been set
	 * @param maxCurrentAge The new value
	 */
	public void setMaxCurrentAge(int maxCurrentAge)
	{
		this.maxCurrentAge = maxCurrentAge;
	}

	/**
	 * Sets the max supply variable
	 * @param maxSupply The new value
	 * @pre none
	 * @post The max supply variable has been set
	 */
	public void setMaxSupply(int maxSupply)
	{
		this.maxSupply = maxSupply;
	}

	/**
	 * Sets the max used age variable
	 * @pre none
	 * @post The max used age variable has been set
	 * @param maxUsedAge The new value
	 */
	public void setMaxUsedAge(int maxUsedAge)
	{
		this.maxUsedAge = maxUsedAge;
	}

	/**
	 * Sets the minimum supply variable
	 * @param minSupply The new value
	 * @pre none
	 * @post The minimum supply variable has been set
	 */
	public void setMinSupply(int minSupply)
	{
		this.minSupply = minSupply;
	}

	/**
	 * Sets the used supply variable
	 * @pre none
	 * @post used supply have been set
	 * @param usedSupply The new value
	 */
	public void setUsedSupply(int usedSupply)
	{
		this.usedSupply = usedSupply;
	}
}
