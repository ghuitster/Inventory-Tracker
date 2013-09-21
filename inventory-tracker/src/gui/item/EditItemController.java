
package gui.item;

import gui.common.Controller;
import gui.common.IView;

/**
 * Controller class for the edit item view.
 */
public class EditItemController extends Controller implements
		IEditItemController
{

	/**
	 * Constructor.
	 * 
	 * @param view Reference to edit item view
	 * @param target Item that is being edited
	 */
	public EditItemController(IView view, ItemData target)
	{
		super(view);

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the edit
	 * item view.
	 */
	@Override
	public void editItem()
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
	protected IEditItemView getView()
	{
		return (IEditItemView) super.getView();
	}

	//
	// IEditItemController overrides
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
	 * This method is called when any of the fields in the edit item view is
	 * changed by the user.
	 */
	@Override
	public void valuesChanged()
	{}

}
