
package gui.storageunit;

import model.IProductContainer;
import model.IProductGroup;
import model.IStorageUnit;
import model.Inventory;
import model.ProductGroup;
import model.StorageUnit;
import gui.common.Controller;
import gui.common.IView;

/**
 * Controller class for the add storage unit view.
 */
public class AddStorageUnitController extends Controller implements
		IAddStorageUnitController
{
	
	private IProductContainer workingContainer;
	
	/**
	 * Constructor.
	 * 
	 * @param view Reference to add storage unit view
	 */
	public AddStorageUnitController(IView view)
	{
		super(view);

		construct();
		
		workingContainer = new StorageUnit("");
		getView().enableOK(false);
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the add
	 * storage unit view.
	 */
	@Override
	public void addStorageUnit()
	{
		try
		{
			Inventory.getInstance().addStorageUnit((IStorageUnit)workingContainer);
		}
		catch (Exception e) 
		{ }
	}

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
	protected IAddStorageUnitView getView()
	{
		return (IAddStorageUnitView) super.getView();
	}

	//
	// IAddStorageUnitController overrides
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
	 * This method is called when any of the fields in the add storage unit view
	 * is changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		workingContainer.setName(getView().getStorageUnitName());
		
		getView().enableOK(Inventory.getInstance()
				.ableToAddStorageUnit((IStorageUnit)workingContainer));
	}
}
