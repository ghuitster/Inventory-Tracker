
package model;

/**
 * A defined set of units for defining unit size and three month supply in the
 * Inventory Tracker.
 * @author Michael
 */
public enum UnitType
{
	// Enumeration Values
	COUNT("count"), POUNDS("pounds"), OUNCES("ounces"), GRAMS("grams"),
	KILOGRAMS("kilograms"), GALLONS("gallons"), QUARTS("quarts"),
	PINTS("pints"), FLUID_OUNCES("fluid ounces"), LITERS("liters"),
	ELEPHANT_WEIGHT("EL"), STONE("st"), CHEVROLET("chevy");

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
	@Override
	public String toString()
	{
		return this.value;
	}
}
