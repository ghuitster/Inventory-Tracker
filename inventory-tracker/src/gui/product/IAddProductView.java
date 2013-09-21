
package gui.product;

import gui.common.IView;
import gui.common.SizeUnits;

/**
 * View interface for the add product view.
 */
public interface IAddProductView extends IView
{

	/**
	 * Sets the enable/disable state of the "Product Barcode" field.
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
	 * Sets the enable/disable state of the "OK" button.
	 * 
	 * @param value New enable/disable value
	 */
	void enableOK(boolean value);

	/**
	 * Sets the enable/disable state of the "Shelf Life" field.
	 * 
	 * @param value New enable/disable value
	 */
	void enableShelfLife(boolean value);

	/**
	 * Sets the enable/disable state of the "Size Unit" field.
	 * 
	 * @param value New enable/disable value
	 */
	void enableSizeUnit(boolean value);

	/**
	 * Sets the enable/disable state of the "Size Value" field.
	 * 
	 * @param value New enable/disable value
	 */
	void enableSizeValue(boolean value);

	/**
	 * Sets the enable/disable state of the "3-Month Supply" field.
	 * 
	 * @param value New enable/disable value
	 */
	void enableSupply(boolean value);

	/**
	 * Returns the value of the "Product Barcode" field.
	 */
	String getBarcode();

	/**
	 * Returns the value of the "Description" field.
	 */
	String getDescription();

	/**
	 * Returns the value of the "Shelf Life" field.
	 */
	String getShelfLife();

	/**
	 * Returns the value of the "Size Unit" field.
	 */
	SizeUnits getSizeUnit();

	/**
	 * Returns the value of the "Size Value" field.
	 */
	String getSizeValue();

	/**
	 * Returns the value of the "3-Month Supply" field.
	 */
	String getSupply();

	/**
	 * Sets the value of the "Product Barcode" field.
	 * 
	 * @param value New "Product Barcode" value
	 */
	void setBarcode(String value);

	/**
	 * Sets the value of the "Description" field.
	 * 
	 * @param value New "Description" value
	 */
	void setDescription(String value);

	/**
	 * Sets the value of the "Shelf Life" field.
	 * 
	 * @param value New "Shelf Life" value
	 */
	void setShelfLife(String value);

	/**
	 * Sets the value of the "Size Unit" field.
	 * 
	 * @param value New "Size Unit" value
	 */
	void setSizeUnit(SizeUnits value);

	/**
	 * Sets the value of the "Size Value" field.
	 * 
	 * @param value New "Size Value"
	 */
	void setSizeValue(String value);

	/**
	 * Sets the value of the "3-Month Supply" field.
	 * 
	 * @param value New "3-Month Supply" value
	 */
	void setSupply(String value);

}
