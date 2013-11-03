package model;

/**
 * Describes various statistics for a product
 * @author Brian
 *
 */
public class ProductStats
{
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
	
	
	public int getCurrentSupply()
	{
		return currentSupply;
	}
	public void setCurrentSupply(int currentSupply)
	{
		this.currentSupply = currentSupply;
	}
	public double getAverageSupply()
	{
		return averageSupply;
	}
	public void setAverageSupply(double averageSupply)
	{
		this.averageSupply = averageSupply;
	}
	public int getMinSupply()
	{
		return minSupply;
	}
	public void setMinSupply(int minSupply)
	{
		this.minSupply = minSupply;
	}
	public int getMaxSupply()
	{
		return maxSupply;
	}
	public void setMaxSupply(int maxSupply)
	{
		this.maxSupply = maxSupply;
	}
	public int getUsedSupply()
	{
		return usedSupply;
	}
	public void setUsedSupply(int usedSupply)
	{
		this.usedSupply = usedSupply;
	}
	public int getAddedSupply()
	{
		return addedSupply;
	}
	public void setAddedSupply(int addedSupply)
	{
		this.addedSupply = addedSupply;
	}
	public double getAvgUsedAge()
	{
		return avgUsedAge;
	}
	public void setAvgUsedAge(double avgUsedAge)
	{
		this.avgUsedAge = avgUsedAge;
	}
	public int getMaxUsedAge()
	{
		return maxUsedAge;
	}
	public void setMaxUsedAge(int maxUsedAge)
	{
		this.maxUsedAge = maxUsedAge;
	}
	public double getAvgCurrentAge()
	{
		return avgCurrentAge;
	}
	public void setAvgCurrentAge(double avgCurrentAge)
	{
		this.avgCurrentAge = avgCurrentAge;
	}
	public int getMaxCurrentAge()
	{
		return maxCurrentAge;
	}
	public void setMaxCurrentAge(int maxCurrentAge)
	{
		this.maxCurrentAge = maxCurrentAge;
	}
	public IProduct getProduct()
	{
		return product;
	}
}
