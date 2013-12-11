
package gui.product;

import gui.batches.AddItemBatchController;
import gui.common.Controller;
import gui.common.IView;
import gui.common.SizeUnitUtils;
import gui.common.SizeUnits;
import model.Amount;
import model.CountThreeMonthSupply;
import model.CountUnitSize;
import model.IBarcode;
import model.IProduct;
import model.Inventory;
import model.Product;
import model.ProductBarcode;
import model.UnitSize;
import model.UnitType;

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
	private SizeUnits oldSizeUnits = sizeUnits;
	private int shelfLife = 0;
	private CountThreeMonthSupply cThreeMonthSupply =
			new CountThreeMonthSupply(0);
	private boolean amount = true;
	private boolean threeMonthSupply = true;
	private boolean shelfLifeValid = true;
	private boolean descriptNotEmpty = false;
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
		this.checkPluginsForProductInfo();
	}

	private void checkPluginsForProductInfo()
	{
		this.getView().enableDescription(false);
		this.getView().setDescription("Identifying Product - Please Wait");
		this.descript = Inventory.getInstance().findProductInfo(barcode.getNumber());
		if(!this.descript.isEmpty())
		{
			this.descriptNotEmpty = true;
		}
		this.getView().enableDescription(true);
		this.getView().setDescription(this.descript);
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
		IProduct myProduct =
				new Product(DateUtils.currentDate(), this.descript,
						this.barcode, this.size, this.shelfLife,
						this.cThreeMonthSupply);
		AddItemBatchController.product = myProduct;
	}

	private void createUnitType()
	{
		this.unitType =
				SizeUnitUtils.createUnitTypeFromSizeUnits(this.sizeUnits);
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
		this.getView().setShelfLife("" + this.shelfLife);
		this.getView().setSizeUnit(this.sizeUnits);
		this.getView().setSizeValue("" + this.sizeValue);
		this.getView().setSupply("" + this.cThreeMonthSupply.getAmount());
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
	 * This method is called when any of the fields in the add product view is
	 * changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		this.setThreeMonthSupply();
		this.setDescript();
		this.descript = getView().getDescription();
		this.oldSizeUnits = this.sizeUnits;
		this.sizeUnits = getView().getSizeUnit();

		if(this.sizeUnits == SizeUnits.Count
				&& this.oldSizeUnits != SizeUnits.Count)
		{
			this.sizeValue = 1;
			getView().setSizeValue("1");
		}
		else if(this.sizeUnits != SizeUnits.Count
				&& this.oldSizeUnits == SizeUnits.Count)
		{
			this.sizeValue = 0;
			getView().setSizeValue("0");
		}

		this.setShelfLife();
		this.setAmount();

		this.enableComponents();
	}
}
