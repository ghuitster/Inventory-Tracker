
package gui.product;

import model.Amount;
import model.CountThreeMonthSupply;
import model.CountUnitSize;
import model.IBarcode;
import model.IProduct;
import model.Product;
import model.ProductBarcode;
import model.UnitSize;
import model.UnitType;
import gui.batches.AddItemBatchController;
import gui.common.Controller;
import gui.common.IView;
import gui.common.SizeUnits;

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
	private float sizeValue = 0;
	private SizeUnits sizeUnits = SizeUnits.Count;
	private int shelflife = 0;
	private CountThreeMonthSupply cThreeMonthSupply = new CountThreeMonthSupply(0);
	private boolean submit = false;
	private UnitType unitType = UnitType.COUNT;

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
		createUnitType();
		if(this.unitType == UnitType.COUNT)
			this.size = new CountUnitSize((int) this.sizeValue);
		else
			this.size = new UnitSize(this.sizeValue, this.unitType);
		IProduct myProduct = new Product(DateUtils.currentDate(), this.descript
				, this.barcode, this.size, this.shelflife, this.cThreeMonthSupply);
		AddItemBatchController.product = myProduct;
	}

	private void createUnitType()
	{
		switch(sizeUnits)
		{
			case Pounds:
				this.unitType = UnitType.POUNDS;
				break;
			case Ounces:
				this.unitType = UnitType.OUNCES;
				break;
			case Grams:
				this.unitType = UnitType.GRAMS;
				break;
			case Kilograms:
				this.unitType = UnitType.KILOGRAMS;
				break;
			case Gallons:
				this.unitType = UnitType.GALLONS;
				break;
			case Quarts:
				this.unitType = UnitType.QUARTS;
				break;
			case Pints:
				this.unitType = UnitType.PINTS;
				break;
			case FluidOunces:
				this.unitType = UnitType.FLUID_OUNCES;
				break;
			case Liters:
				this.unitType = UnitType.LITERS;
				break;
			case Count:
				this.unitType = UnitType.COUNT;
		}
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
			this.sizeValue = 1;
		}
		else
		{
			getView().enableSizeValue(true);
		}
		getView().enableBarcode(false);
		
		getView().enableOK(submit);
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
		if(!getView().getSupply().isEmpty() && !getView().getSupply().startsWith("-"))
		{
			try
			{
				this.cThreeMonthSupply = new CountThreeMonthSupply(Integer.parseInt
					(getView().getSupply()));
			}
			catch(NumberFormatException e)
			{
				this.getView().displayErrorMessage("The three month supply must"
					+ " be a whole number");
				this.getView().setSupply("" + 0);
			}
		}
		this.descript = getView().getDescription();
		this.sizeUnits = getView().getSizeUnit();
		if(!getView().getShelfLife().isEmpty() && !getView().getShelfLife().startsWith("-"))
		{
			try
			{
				this.shelflife = Integer.parseInt(getView().getShelfLife());
			}
			catch(NumberFormatException e)
			{
				this.getView().displayErrorMessage("Shelflife must be a whole number");
				this.getView().setShelfLife("" + this.shelflife);
			}
		}
		if(!getView().getSizeValue().isEmpty() && !getView().getSizeValue().startsWith("-"))
		{
			if(this.sizeUnits == SizeUnits.Count)
				try
				{
					this.sizeValue = Integer.parseInt(getView().getSizeValue());
				}
				catch(NumberFormatException e)
				{
					getView().displayErrorMessage("For unit size type Count, the "
							+ "value must be a whole number");
				}
			else
			{
				this.sizeValue = Float.parseFloat(getView().getSizeValue());
			}
		}
		
		this.shouldOKBeEnabled();
		this.enableComponents();
	}

	private void shouldOKBeEnabled()
	{
		if(this.descript.isEmpty())
			this.submit = false;
		else if(sizeValue <= 0 || this.shelflife < 0 || this.cThreeMonthSupply.getAmount() < 0)
			this.submit = false;
		else if(getView().getSupply().isEmpty() || getView().getSupply().startsWith("-"))
			this.submit = false;
		else if(getView().getShelfLife().isEmpty() || getView().getShelfLife().startsWith("-"))
			this.submit = false;
		else if(getView().getSizeValue().isEmpty() || getView().getSizeValue().startsWith("-"))
			this.submit = false;
		else
			this.submit = true;
	}
}
