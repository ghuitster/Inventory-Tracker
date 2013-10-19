/**
 * 
 */
package gui.common;

import model.UnitType;

/**
 * @author chris_000
 *
 */
public abstract class SizeUnitUtils
{
	public static UnitType createUnitTypeFromSizeUnits(SizeUnits sizeUnits)
	{
		switch(sizeUnits)
		{
			case Pounds:
				return UnitType.POUNDS;
			case Ounces:
				return UnitType.OUNCES;
			case Grams:
				return UnitType.GRAMS;
			case Kilograms:
				return UnitType.KILOGRAMS;
			case Gallons:
				return UnitType.GALLONS;
			case Quarts:
				return UnitType.QUARTS;
			case Pints:
				return UnitType.PINTS;
			case FluidOunces:
				return UnitType.FLUID_OUNCES;
			case Liters:
				return UnitType.LITERS;
			case Count:
				return UnitType.COUNT;
			default:
				return null;
		}
	}
	
	public static SizeUnits createSizeUnitsFromUnitType(UnitType unitType)
	{
		switch(unitType)
		{
			case POUNDS:
				return SizeUnits.Pounds;
			case OUNCES:
				return SizeUnits.Ounces;
			case GRAMS:
				return SizeUnits.Grams;
			case KILOGRAMS:
				return SizeUnits.Kilograms;
			case GALLONS:
				return SizeUnits.Gallons;
			case QUARTS:
				return SizeUnits.Quarts;
			case PINTS:
				return SizeUnits.Pints;
			case FLUID_OUNCES:
				return SizeUnits.FluidOunces;
			case LITERS:
				return SizeUnits.Liters;
			case COUNT:
				return SizeUnits.Count;
			default:
				return null;
		}
	}
}
