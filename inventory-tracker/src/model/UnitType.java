
package model;

/**
 * A defined set of units for defining unit size and three month supply in the
 * Inventory Tracker.
 * @author Michael
 */
public enum UnitType
{
	// Enumeration Values
	COUNT("count"), POUNDS("lbs"), OUNCES("oz"), GRAMS("g"), KILOGRAMS("kg"),
	GALLONS("gal"), QUARTS("qt"), PINTS("pt"), FLUID_OUNCES("fl oz"), LITERS(
			"L"), ELEPHANT_WEIGHT("EL"), STONE("st"), CHEVROLET("chevy");

	// Variables
	private final String value;

	// Constructor
	/**
	 * The UnitType Enumeration constructor
	 * @param val the enumeration value to pass in.
	 */
	private UnitType(String val)
	{
		this.value = val;
	}

	// Methods
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return this.value;
	}
}
