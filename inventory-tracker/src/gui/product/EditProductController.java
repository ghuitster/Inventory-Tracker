package gui.product;

import gui.common.*;

/**
 * Controller class for the edit product view.
 */
public class EditProductController extends Controller 
										implements IEditProductController {
	
	/**
	 * Constructor.
	 * 
	 * @param view Reference to the edit product view
	 * @param target Product being edited
	 */
	public EditProductController(IView view, ProductData target) {
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
	protected IEditProductView getView() {
		return (IEditProductView)super.getView();
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
	// IEditProductController overrides
	//

	/**
	 * This method is called when any of the fields in the
	 * edit product view is changed by the user.
	 */
	@Override
	public void valuesChanged() {
	}
	
	/**
	 * This method is called when the user clicks the "OK"
	 * button in the edit product view.
	 */
	@Override
	public void editProduct() {
	}

}

