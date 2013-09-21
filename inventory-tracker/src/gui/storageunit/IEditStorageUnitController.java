
package gui.storageunit;

import gui.common.IController;

/**
 * Controller interface for edit storage unit view.
 */
public interface IEditStorageUnitController extends IController
{

	/**
	 * This method is called when the user clicks the "OK" button in the edit
	 * storage unit view.
	 */
	void editStorageUnit();

	/**
	 * This method is called when any of the fields in the edit storage unit
	 * view is changed by the user.
	 */
	void valuesChanged();

}
