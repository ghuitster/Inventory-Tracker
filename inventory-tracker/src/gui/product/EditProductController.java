
package gui.product;

import gui.common.Controller;
import gui.common.IView;
import gui.common.SizeUnitUtils;
import gui.common.SizeUnits;
import model.Amount;
import model.CountThreeMonthSupply;
import model.CountUnitSize;
import model.IBarcode;
import model.IProduct;
import model.ProductBarcode;
import model.ProductDescription;
import model.UnitSize;
import model.UnitType;

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
	private int shelfLife = 0;
	private CountThreeMonthSupply cThreeMonthSupply = null;
	private boolean amount = true;
	private boolean threeMonthSupply = true;
	private boolean shelfLifeValid = true;
	private boolean descriptNotEmpty = true;
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
		this.cThreeMonthSupply = this.product.getThreeMonthSupply();
		if(this.sizeUnits == SizeUnits.Count)
			this.sizeValue = ((CountUnitSize) product.getSize()).getAmount();
		else
			this.sizeValue = ((UnitSize) product.getSize()).getAmount();
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

	private void createUnitType()
	{
		this.unitType =
				SizeUnitUtils.createUnitTypeFromSizeUnits(this.sizeUnits);
	}

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
		product.setShelfLife(this.shelfLife);
		product.setSize(this.size);
		product.setThreeMonthSupply(cThreeMonthSupply);
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
			int temp = (int) this.sizeValue;
			getView().setSizeValue("" + temp);
		}
		else
		{
			getView().enableSizeValue(true);
		}
		getView().enableBarcode(false);

		getView().enableOK(this.shouldOKBeEnabled());
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
		this.getView().setDescription(
				this.product.getDescription().getDescription());
		this.getView().setShelfLife("" + this.product.getShelfLife());
		if(sizeUnits == SizeUnits.Count)
			this.getView().setSizeValue("" + (int) this.sizeValue);
		else
			this.getView().setSizeValue("" + this.sizeValue);
		this.getView().setSupply(
				"" + this.product.getThreeMonthSupply().getAmount());

		this.getView().setSizeUnit(this.sizeUnits);
	}

	private void setAmount()
	{
		if(!getView().getSizeValue().isEmpty()
				&& !getView().getSizeValue().startsWith("-"))
		{
			if(this.sizeUnits == SizeUnits.Count)
				try
				{
					this.sizeValue = Float.parseFloat(getView().getSizeValue());
					if(this.sizeValue % 1 == 0)
					{
						int temp = (int) this.sizeValue;
						this.sizeValue = temp;
						this.amount = true;
					}
					else
					{
						this.amount = false;
					}
				}
				catch(NumberFormatException e)
				{
					this.amount = false;
				}
			else
			{
				try
				{
					this.sizeValue = Float.parseFloat(getView().getSizeValue());
					this.amount = true;
				}
				catch(NumberFormatException e)
				{
					this.amount = false;
				}
			}
		}
		else
		{
			this.amount = false;
		}
	}

	private void setDescript()
	{
		this.descript = getView().getDescription();
		if(this.descript.isEmpty())
			this.descriptNotEmpty = false;
		else
			this.descriptNotEmpty = true;
	}

	private void setShelfLife()
	{
		if(!getView().getShelfLife().isEmpty()
				&& !getView().getShelfLife().startsWith("-"))
		{
			try
			{
				int temp = Integer.parseInt(getView().getShelfLife());
				if(temp % 1 == 0)
				{
					this.shelfLife = temp;
					this.shelfLifeValid = true;
				}
				else
				{
					this.shelfLifeValid = false;
				}
			}
			catch(NumberFormatException e)
			{
				this.shelfLifeValid = false;
			}
		}
		else
		{
			this.shelfLifeValid = false;
		}
	}

	private void setThreeMonthSupply()
	{
		if(!getView().getSupply().isEmpty()
				&& !getView().getSupply().startsWith("-"))
		{
			try
			{
				float temp = Float.parseFloat(getView().getSupply());
				if(temp % 1 == 0)
				{
					this.cThreeMonthSupply =
							new CountThreeMonthSupply((int) temp);
					this.threeMonthSupply = true;
				}
				else
				{
					this.threeMonthSupply = false;
				}
			}
			catch(NumberFormatException e)
			{
				this.threeMonthSupply = false;
			}
		}
		else
		{
			this.threeMonthSupply = false;
		}
	}

	private boolean shouldOKBeEnabled()
	{
		return this.amount && this.shelfLifeValid && this.threeMonthSupply
				&& this.descriptNotEmpty;
	}

	/**
	 * This method is called when any of the fields in the edit product view is
	 * changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		this.setThreeMonthSupply();
		this.setDescript();
		if(this.sizeUnits != SizeUnits.Count
				&& getView().getSizeUnit() == SizeUnits.Count)
		{
			this.sizeValue = 1;
			int temp = (int) this.sizeValue;
			this.getView().setSizeValue("" + temp);
		}
		this.sizeUnits = getView().getSizeUnit();
		this.setShelfLife();
		this.setAmount();

		this.enableComponents();
	}
}
