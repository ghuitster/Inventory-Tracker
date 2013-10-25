
package gui.product;

import model.Amount;
import model.CountThreeMonthSupply;
import model.CountUnitSize;
import model.IBarcode;
import model.IProduct;
import model.ProductBarcode;
import model.ProductDescription;
import model.UnitSize;
import model.UnitType;
import gui.common.Controller;
import gui.common.IView;
import gui.common.SizeUnitUtils;
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
	private boolean submit = true;
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
		this.cThreeMonthSupply = (CountThreeMonthSupply) this.product.getThreeMonthSupply();
		if(this.sizeUnits == SizeUnits.Count)
			this.sizeValue = ((CountUnitSize)product.getSize()).getAmount();
		else
			this.sizeValue = ((UnitSize)product.getSize()).getAmount();
		this.descript = this.product.getDescription().getDescription();
		construct();
	}

	private void createSizeUnitsFromUnitType()
	{
		UnitType unitType = this.product.getSize().getUnitType();
		this.sizeUnits = SizeUnitUtils.createSizeUnitsFromUnitType(unitType);
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
		this.unitType = SizeUnitUtils.createUnitTypeFromSizeUnits(this.sizeUnits);
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
			int temp = (int)this.sizeValue;
			getView().setSizeValue("" + temp);
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
		if(sizeUnits == SizeUnits.Count)
			this.getView().setSizeValue("" + (int)this.sizeValue);
		else
			this.getView().setSizeValue("" + this.sizeValue);
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
				this.getView().setSupply("" + this.cThreeMonthSupply.getAmount());
			}
		}
		this.descript = getView().getDescription();
		if(this.sizeUnits != SizeUnits.Count && getView().getSizeUnit() == SizeUnits.Count)
		{
			this.sizeValue = 1;
			int temp = (int)this.sizeValue;
			this.getView().setSizeValue("" + temp);
		}
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
					int temp = (int)this.sizeValue;
					getView().setSizeValue("" + temp);
					this.sizeValue = temp;
				}
			else
			{
				try
				{
					this.sizeValue = Float.parseFloat(getView().getSizeValue());
				}
				catch(NumberFormatException e)
				{
					this.getView().displayErrorMessage("Digits only, no characters");
					this.getView().setSizeValue("" + this.sizeValue);
				}
			}
		}
		
		this.shouldOKBeEnabled();
		this.enableComponents();
	}

	private void shouldOKBeEnabled()
	{
		if(this.descript.isEmpty())
			submit = false;
		else if(sizeValue <= 0 || this.shelflife < 0 || this.cThreeMonthSupply.getAmount() < 0)
			submit = false;
		else if(getView().getSupply().isEmpty() || getView().getSupply().startsWith("-"))
			submit = false;
		else if(getView().getShelfLife().isEmpty() || getView().getShelfLife().startsWith("-"))
			submit = false;
		else if(getView().getSizeValue().isEmpty() || getView().getSizeValue().startsWith("-"))
			submit = false;
		else
			submit = true;
	}
}
