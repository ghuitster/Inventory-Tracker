
package model;

/**
 * Class to represent a unit size where the unit type is not "count"
 * @author Michael
 * 
 */
public class UnitSize extends NonCountAmount
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7803041358588140597L;

	/**
	 * @pre size must be > 0.0f
	 * @pre unitType must != UnitType.COUNT
	 * @post size == passed in size
	 * @post unitType == passed in UnitType
	 * @param size
	 * @param unitType
	 */
	public UnitSize(float size, UnitType unitType)
	{
		super(size, unitType);
	}

	/**
	 * @pre size must be > 0
	 * @param size the size to test if we can set
	 */
	public boolean ableToSetSize(float size)
	{
		return super.ableToSetAmount(size);
	}

	/**
	 * @return the size
	 */
	public float getSize()
	{
		return amount;
	}

	/**
	 * @pre size must be > 0.0f
	 * @post size == passed in size
	 * @param size the size to set
	 */
	public void setSize(float size)
	{
		super.setAmount(size);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return amount + " " + unitType;
	}

}
