
package gui.product;

import model.Amount;
import model.CountThreeMonthSupply;
import model.IBarcode;
import model.IProduct;
import model.Product;
import gui.common.Controller;
import gui.common.IView;
import gui.common.SizeUnits;

import java.util.Date;

/**
 * Controller class for the add item view.
 */
public class AddProductController extends Controller implements
		IAddProductController
{
	private String descript = "";
	private IBarcode barcode = null;
	private Amount size = null;
	private int shelflife = 0;
	private CountThreeMonthSupply cThreeMonthSupply = null;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the add product view
	 * @param barcode Barcode for the product being added
	 */
	public AddProductController(IView view, String barcode)
	{
		super(view);

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the add
	 * product view.
	 */
	@Override
	public void addProduct()
	{
		//TODO
		Date creationDate = new Date();
		IProduct product = new Product(creationDate,this.descript, this.barcode, this.size, this.shelflife, this.cThreeMonthSupply);
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
	{
//		getView().
		if(getView().getSizeUnit() == SizeUnits.Count)
			getView().enableSizeValue(false);
	}

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IAddProductView getView()
	{
		return (IAddProductView) super.getView();
	}

	//
	// IAddProductController overrides
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
	{
		//TODO
	}

	/**
	 * This method is called when any of the fields in the add product view is
	 * changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		//TODO
	}

}
