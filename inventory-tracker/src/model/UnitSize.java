package model;

/**
 * Class to represent a unit size where the unit type is not "count"
 * @author Michael
 * 
 */
public class UnitSize extends NonCountAmount
{

	/**
	 * @precondition size must be > 0.0f
	 * @precondition unitType must != UnitType.COUNT
	 * @postcondition size == passed in size
	 * @postcondition unitType == passed in UnitType
	 * @param size
	 * @param unitType
	 */
	public UnitSize(float size, UnitType unitType)
	{
		super(size, unitType);
	}

	/**
	 * @precondition size must be > 0
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
	 * @precondition size must be > 0.0f
	 * @postcondition size == passed in size
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
		return "NewUnitSize [size=" + amount + ", unitType=" + unitType + "]";
	}

}
