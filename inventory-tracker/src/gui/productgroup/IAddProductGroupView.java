
package gui.productgroup;

import gui.common.IView;
import gui.common.SizeUnits;

/**
 * View interface for the add product group view.
 */
public interface IAddProductGroupView extends IView
{

	/**
	 * Sets the enable/disable state of the "OK" button.
	 * 
	 * @param value New enable/disable value
	 */
	void enableOK(boolean value);

	/**
	 * Sets the enable/disable state of the "Product Group Name" field.
	 * 
	 * @param value New enable/disable value
	 */
	void enableProductGroupName(boolean value);

	/**
	 * Sets the enable/disable state of the "3-Month Supply Unit" field.
	 * 
	 * @param value New enable/disable value
	 */
	void enableSupplyUnit(boolean value);

	/**
	 * Sets the enable/disable state of the "3-Month Supply Value" field.
	 * 
	 * @param value New enable/disable value
	 */
	void enableSupplyValue(boolean value);

	/**
	 * Returns the value of the "Product Group Name" field.
	 */
	String getProductGroupName();

	/**
	 * Returns the value of the "3-Month Supply Unit" field.
	 */
	SizeUnits getSupplyUnit();

	/**
	 * Returns the value of the "3-Month Supply Value" field.
	 */
	String getSupplyValue();

	/**
	 * Sets the value of the "Product Group Name" field.
	 * 
	 * @param value New "Product Group Name" value
	 */
	void setProductGroupName(String value);

	/**
	 * Sets the value of the "3-Month Supply Unit" field.
	 * 
	 * @param value New "3-Month Supply Unit" value
	 */
	void setSupplyUnit(SizeUnits value);

	/**
	 * Sets the value of the "3-Month Supply Value" field.
	 * 
	 * @param value New "3-Month Supply Value"
	 */
	void setSupplyValue(String value);

}
