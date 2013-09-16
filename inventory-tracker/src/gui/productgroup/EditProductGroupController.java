package gui.productgroup;

import gui.common.*;
import gui.inventory.*;

/**
 * Controller class for the edit product group view.
 */
public class EditProductGroupController extends Controller 
										implements IEditProductGroupController {
	
	/**
	 * Constructor.
	 * 
	 * @param view Reference to edit product group view
	 * @param target Product group being edited
	 */
	public EditProductGroupController(IView view, ProductContainerData target) {
		super(view);

		construct();
	}

	//
	// Controller overrides
	//
	
	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IEditProductGroupView getView() {
		return (IEditProductGroupView)super.getView();
	}

	/**
	 * Sets the enable/disable state of all components in the controller's view.
	 * A component should be enabled only if the user is currently
	 * allowed to interact with that component.
	 * 
	 * {@pre None}
	 * 
	 * {@post The enable/disable state of all components in the controller's view
	 * have been set appropriately.}
	 */
	@Override
	protected void enableComponents() {
	}

	/**
	 * Loads data into the controller's view.
	 * 
	 *  {@pre None}
	 *  
	 *  {@post The controller has loaded data into its view}
	 */
	@Override
	protected void loadValues() {
	}

	//
	// IEditProductGroupController overrides
	//

	/**
	 * This method is called when any of the fields in the
	 * edit product group view is changed by the user.
	 */
	@Override
	public void valuesChanged() {
	}
	
	/**
	 * This method is called when the user clicks the "OK"
	 * button in the edit product group view.
	 */
	@Override
	public void editProductGroup() {
	}

}

