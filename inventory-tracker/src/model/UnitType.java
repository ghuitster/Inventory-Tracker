/**
 * 
 */
package model;

/**
 * A defined set of units for defining unit size and three month supply in the Inventory Tracker.
 * @author Michael
 */
public enum UnitType
{
	// Enumeration Values
	COUNT ("count"),
	POUNDS ("lbs"),
	OUNCES ("oz"),
	GRAMS ("g"),
	KILOGRAMS ("kg"),
	GALLONS ("gal"),
	QUARTS ("qt"),
	PINTS ("pt"),
	FLUID_OUNCES ("fl oz"),
	LITERS ("L"),
	ELEPHANT_WEIGHT ("EL"),
	STONE ("st"),
	CHEVROLET("chevy");
	
	// Variables
	private final String value;
	
	// Constructor
	private UnitType (String val)
	{
		this.value = val;
	}
	
	//Methods
	public String toString()
	{
		return this.value;
	}
}
