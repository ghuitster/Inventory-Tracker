
package gui.reports.notices;

import gui.common.IController;

/**
 * Controller interface for the notices report view.
 */
public interface INoticesReportController extends IController
{

	/**
	 * This method is called when the user clicks the "OK" button in the notices
	 * report view.
	 */
	void display();

	/**
	 * This method is called when any of the fields in the notices report view
	 * is changed by the user.
	 */
	void valuesChanged();

}
