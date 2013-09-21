
package gui.item;

import gui.common.IView;

import java.util.Date;

/**
 * View interface for the edit item view.
 */
public interface IEditItemView extends IView
{

	/**
	 * Sets the enable/disable state of the "Barcode" field.
	 * 
	 * @param value New enable/disable value
	 */
	void enableBarcode(boolean value);

	/**
	 * Sets the enable/disable state of the "Description" field.
	 * 
	 * @param value New enable/disable value
	 */
	void enableDescription(boolean value);

	/**
	 * Sets the enable/disable state of the "Entry Date" field.
	 * 
	 * @param value New enable/disable value
	 */
	void enableEntryDate(boolean value);

	/**
	 * Sets the enable/disable state of the "OK" button.
	 * 
	 * @param value New enable/disable value
	 */
	void enableOK(boolean value);

	/**
	 * Returns the value of the "Barcode" field.
	 */
	String getBarcode();

	/**
	 * Returns the value of the "Description" field.
	 */
	String getDescription();

	/**
	 * Returns the value of the "Entry Date" field, or null if the field's value
	 * is invalid.
	 */
	Date getEntryDate();

	/**
	 * Sets the value of the "Barcode" field.
	 * 
	 * @param value New "Barcode" value
	 */
	void setBarcode(String value);

	/**
	 * Sets the value of the "Description" field.
	 * 
	 * @param value New "Description" value
	 */
	void setDescription(String value);

	/**
	 * Sets the value of the "Entry Date" field.
	 * 
	 * @param value New "Entry Date" value
	 */
	void setEntryDate(Date value);

}
