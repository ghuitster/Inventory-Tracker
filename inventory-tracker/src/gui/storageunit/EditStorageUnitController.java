
package gui.storageunit;

import gui.common.Controller;
import gui.common.IView;
import gui.inventory.ProductContainerData;

/**
 * Controller class for the edit storage unit view.
 */
public class EditStorageUnitController extends Controller implements
		IEditStorageUnitController
{

	/**
	 * Constructor.
	 * 
	 * @param view Reference to edit storage unit view
	 * @param target The storage unit being edited
	 */
	public EditStorageUnitController(IView view, ProductContainerData target)
	{
		super(view);

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the edit
	 * storage unit view.
	 */
	@Override
	public void editStorageUnit()
	{}

	/**
	 * Sets the enable/disable state of all components in the controller's view.
	 * A component should be enabled only if the user is currently allowed to
	 * interact with that component.
	 * 
	 * {@pre None}
	 * 
	 * {@post The enable/disable state of all components in the controller's
	 * view have been set appropriately.}
	 */
	@Override
	protected void enableComponents()
	{}

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IEditStorageUnitView getView()
	{
		return (IEditStorageUnitView) super.getView();
	}

	//
	// IEditStorageUnitController overrides
	//

	/**
	 * Loads data into the controller's view.
	 * 
	 * {@pre None}
	 * 
	 * {@post The controller has loaded data into its view}
	 */
	@Override
	protected void loadValues()
	{}

	/**
	 * This method is called when any of the fields in the edit storage unit
	 * view is changed by the user.
	 */
	@Override
	public void valuesChanged()
	{}

}
