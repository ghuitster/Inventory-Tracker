/**
 * 
 */

package model;

import java.io.Serializable;

/**
 * An abstract class to build the abstraction layers for CountThreeMonthSupply,
 * ThreeMonthSupply, CountUnitSize, and UnitSize
 * @author Michael
 * 
 */
public abstract class Amount implements Serializable
{
	// Variables
	protected UnitType unitType;

	/**
	 * @pre unitType must be of type UnitType
	 * @pre unitType == passed in unitType
	 * @param unitType
	 */
	public Amount(UnitType unitType)
	{
		this.unitType = unitType;
	}

	// Methods
	/**
	 * @return the unitType
	 */
	public abstract UnitType getUnitType();
}
