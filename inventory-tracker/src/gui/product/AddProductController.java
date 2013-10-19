
package gui.product;

import model.Amount;
import model.CountThreeMonthSupply;
import model.IBarcode;
import model.IProduct;
import model.Product;
import model.ProductBarcode;
import gui.batches.AddItemBatchController;
import gui.batches.IAddItemBatchController;
import gui.common.Controller;
import gui.common.IView;
import gui.common.SizeUnits;

import java.util.Date;

import common.util.DateUtils;

/**
 * Controller class for the add item view.
 */
public class AddProductController extends Controller implements
		IAddProductController
{
	private String descript = "";
	private IBarcode barcode = null;
	private Amount size = null;
	private int sizeValue = 0;
	private SizeUnits sizeUnits = SizeUnits.Count;
	private int shelflife = 0;
	private CountThreeMonthSupply cThreeMonthSupply = null;
	private boolean submit = false;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the add product view
	 * @param barcode Barcode for the product being added
	 */
	public AddProductController(IView view, String barcode)
	{
		super(view);
		
		this.barcode = new ProductBarcode(barcode);
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
		barcode = new ProductBarcode(this.getView().getBarcode());
		IProduct myProduct = new Product(DateUtils.currentDate(), this.descript, this.barcode, this.size, this.shelflife, this.cThreeMonthSupply);
		AddItemBatchController.product = myParent;
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
		if(getView().getSizeUnit() == SizeUnits.Count)
		{
			getView().enableSizeValue(false);
			getView().setSizeValue("1");
		}
		else
		{
			getView().enableSizeValue(true);
			getView().setSizeValue("0");
		}
		getView().enableBarcode(false);
		
		if(submit)
		{
			getView().enableOK(submit);
		}
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
		this.getView().setBarcode(this.barcode.toString());
		this.getView().setDescription(this.descript);
		this.getView().setShelfLife("" + this.shelflife);
		this.getView().setSizeUnit(this.sizeUnits);
		this.getView().setSizeValue("" + this.sizeValue);
		this.getView().setSupply("" + this.cThreeMonthSupply.getAmount());
	}

	/**
	 * This method is called when any of the fields in the add product view is
	 * changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		this.cThreeMonthSupply = new CountThreeMonthSupply(Integer.parseInt(getView().getSupply()));
		this.descript = getView().getDescription();
		this.sizeValue = Integer.parseInt(getView().getSizeValue());
		this.sizeUnits = getView().getSizeUnit();
		
		this.shouldOKBeEnabled();
		this.enableComponents();
	}

	private void shouldOKBeEnabled()
	{
		if(this.descript.isEmpty())
			submit = false;
		else if(this.sizeUnits != SizeUnits.Count && sizeValue == 0)
			submit = false;
		else
			submit = true;
	}
}
