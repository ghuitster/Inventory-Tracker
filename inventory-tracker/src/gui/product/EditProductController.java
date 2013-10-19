
package gui.product;

import common.util.DateUtils;
import model.Amount;
import model.CountThreeMonthSupply;
import model.CountUnitSize;
import model.IBarcode;
import model.IProduct;
import model.IProductDescription;
import model.Product;
import model.ProductBarcode;
import model.ProductDescription;
import model.UnitSize;
import model.UnitType;
import gui.common.Controller;
import gui.common.IView;
import gui.common.SizeUnits;

/**
 * Controller class for the edit product view.
 */
public class EditProductController extends Controller implements
		IEditProductController
{

	private IProduct product;
	private String descript = "";
	private IBarcode barcode = null;
	private Amount size = null;
	private float sizeValue = 0;
	private SizeUnits sizeUnits = SizeUnits.Count;
	private int shelflife = 0;
	private CountThreeMonthSupply cThreeMonthSupply = null;
	private boolean submit = false;
	private UnitType unitType = UnitType.COUNT;
	/**
	 * Constructor.
	 * 
	 * @param view Reference to the edit product view
	 * @param target Product being edited
	 */
	public EditProductController(IView view, ProductData target)
	{
		super(view);

		product = (IProduct) target.getTag();
		createSizeUnitsFromUnitType();
		construct();
	}

	private void createSizeUnitsFromUnitType()
	{
		UnitType unitType = this.product.getSize().getUnitType();
		switch(unitType)
		{
			case POUNDS:
				this.sizeUnits = SizeUnits.Pounds;
				break;
			case OUNCES:
				this.sizeUnits = SizeUnits.Ounces;
				break;
			case GRAMS:
				this.sizeUnits = SizeUnits.Grams;
				break;
			case KILOGRAMS:
				this.sizeUnits = SizeUnits.Kilograms;
				break;
			case GALLONS:
				this.sizeUnits = SizeUnits.Gallons;
				break;
			case QUARTS:
				this.sizeUnits = SizeUnits.Quarts;
				break;
			case PINTS:
				this.sizeUnits = SizeUnits.Pints;
				break;
			case FLUID_OUNCES:
				this.sizeUnits = SizeUnits.FluidOunces;
				break;
			case LITERS:
				this.sizeUnits = SizeUnits.Liters;
				break;
			case COUNT:
				this.sizeUnits = SizeUnits.Count;
			default:
				break;
		}
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the edit
	 * product view.
	 */
	@Override
	public void editProduct()
	{
		barcode = new ProductBarcode(this.getView().getBarcode());
		createUnitType();
		if(this.unitType == UnitType.COUNT)
			this.size = new CountUnitSize((int) this.sizeValue);
		else
			this.size = new UnitSize(this.sizeValue, this.unitType);
		product.setBarcode(this.barcode);
		product.setDescription(new ProductDescription(this.descript));
		product.setShelfLife(this.shelflife);
		product.setSize(this.size);
		product.setThreeMonthSupply(cThreeMonthSupply);
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
	protected IEditProductView getView()
	{
		return (IEditProductView) super.getView();
	}

	//
	// IEditProductController overrides
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
		this.getView().setBarcode(this.product.getBarcode().toString());
		this.getView().setDescription(this.product.getDescription()
				.getDescription());
		this.getView().setShelfLife("" + this.product.getShelfLife());
		this.getView().setSizeValue("" + this.product.getSize());
		this.getView().setSupply("" + ((CountThreeMonthSupply)
				this.product.getThreeMonthSupply()).getAmount());
		
		this.getView().setSizeUnit(this.sizeUnits);
	}

	/**
	 * This method is called when any of the fields in the edit product view is
	 * changed by the user.
	 */
	@Override
	public void valuesChanged()
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
		}
		this.descript = getView().getDescription();
		this.sizeUnits = getView().getSizeUnit();
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
