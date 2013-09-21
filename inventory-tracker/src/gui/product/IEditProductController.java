
package gui.product;

import gui.common.IController;

/**
 * Controller interface for edit product view.
 */
public interface IEditProductController extends IController
{

	/**
	 * This method is called when the user clicks the "OK" button in the edit
	 * product view.
	 */
	void editProduct();

	/**
	 * This method is called when any of the fields in the edit product view is
	 * changed by the user.
	 */
	void valuesChanged();

}
