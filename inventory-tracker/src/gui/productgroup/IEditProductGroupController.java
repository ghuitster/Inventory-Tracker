
package gui.productgroup;

import gui.common.IController;

/**
 * Controller interface for edit product group view.
 */
public interface IEditProductGroupController extends IController
{

	/**
	 * This method is called when the user clicks the "OK" button in the edit
	 * product group view.
	 */
	void editProductGroup();

	/**
	 * This method is called when any of the fields in the edit product group
	 * view is changed by the user.
	 */
	void valuesChanged();

}
