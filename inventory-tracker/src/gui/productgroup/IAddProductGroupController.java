
package gui.productgroup;

import gui.common.IController;

/**
 * Controller interface for add product group view.
 */
public interface IAddProductGroupController extends IController
{

	/**
	 * This method is called when the user clicks the "OK" button in the add
	 * product group view.
	 */
	void addProductGroup();

	/**
	 * This method is called when any of the fields in the add product group
	 * view is changed by the user.
	 */
	void valuesChanged();

}
