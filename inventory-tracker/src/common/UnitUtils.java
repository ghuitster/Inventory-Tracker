
package common;

import model.UnitType;
import model.exception.InvalidUnitTypeException;

public class UnitUtils
{
	public static float convertFromGallons(float gallons, UnitType unitType)
			throws InvalidUnitTypeException
	{
		switch(unitType)
		{
		case FLUID_OUNCES:
			return gallons * 128f;
		case GALLONS:
			return gallons;
		case LITERS:
			return gallons * 3.785f;
		case PINTS:
			return gallons * 8.0f;
		case QUARTS:
			return gallons * 4.0f;
		default:
			throw new InvalidUnitTypeException("unitType was not a volume");

		}
	}

	public static float convertFromPounds(float pounds, UnitType unitType)
			throws InvalidUnitTypeException
	{
		switch(unitType)
		{
		case GRAMS:
			return pounds * 453.6f;
		case KILOGRAMS:
			return pounds * 0.4536f;
		case OUNCES:
			return pounds * 16.0f;
		case POUNDS:
			return pounds;
		default:
			throw new InvalidUnitTypeException("unitType was not a weight");
		}
	}

	public static float convertToGallons(float volume, UnitType unitType)
	{
		switch(unitType)
		{
		case FLUID_OUNCES:
			return volume / 128f;
		case GALLONS:
			return volume;
		case LITERS:
			return volume / 3.785f;
		case PINTS:
			return volume / 8.0f;
		case QUARTS:
			return volume / 4.0f;
		default:
			return 0.0f;
		}
	}

	public static float convertToPounds(float volume, UnitType unitType)
	{
		switch(unitType)
		{
		case GRAMS:
			return volume / 453.6f;
		case KILOGRAMS:
			return volume / 0.4536f;
		case OUNCES:
			return volume / 16.0f;
		case POUNDS:
			return volume;
		default:
			return 0.0f;
		}
	}

	public static boolean UnitTypeIsVolume(UnitType unitType)
	{
		return unitType == UnitType.FLUID_OUNCES
				|| unitType == UnitType.GALLONS || unitType == UnitType.LITERS
				|| unitType == UnitType.PINTS || unitType == UnitType.QUARTS;
	}

	public static boolean UnitTypeIsWeight(UnitType unitType)
	{
		return unitType == UnitType.GRAMS || unitType == UnitType.KILOGRAMS
				|| unitType == UnitType.OUNCES || unitType == UnitType.POUNDS;
	}
}
