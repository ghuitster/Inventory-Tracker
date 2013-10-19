
package gui.productgroup;

import model.Amount;
import model.CountThreeMonthSupply;
import model.IProductContainer;
import model.IProductGroup;
import model.ProductGroup;
import model.ThreeMonthSupply;
import model.UnitType;
import gui.common.Controller;
import gui.common.IView;
import gui.common.SizeUnits;
import gui.inventory.ProductContainerData;

/**
 * Controller class for the add product group view.
 */
public class AddProductGroupController extends Controller implements
		IAddProductGroupController
{
	
	private IProductContainer PC;
	private IProductGroup PG = null;
	private boolean submit = false;
	private SizeUnits sizeUnits = SizeUnits.Count;
	private String name = "";
	private float value = 0;
	private UnitType unitType = UnitType.COUNT;
	private Amount threeMonthSupply = null;
	/**
	 * Constructor.
	 * 
	 * @param view Reference to add product group view
	 * @param container Product container to which the new product group is
	 *            being added
	 */
	public AddProductGroupController(IView view, ProductContainerData container)
	{
		super(view);
		
		this.PC = (IProductContainer) container.getTag();

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the add
	 * product group view.
	 */
	@Override
	public void addProductGroup()
	{
		createUnitType();
		if(unitType == UnitType.COUNT)
			this.threeMonthSupply = new CountThreeMonthSupply((int)this.value);
		else
			this.threeMonthSupply = new ThreeMonthSupply(this.value, unitType);
		PG = new ProductGroup(this.name, this.threeMonthSupply);
		if(PC.ableToAddProductGroup(PG))
		{
			PC.addProductGroup(PG);
			PG.setContainer(PC);
		}
		else
		{
			this.getView().displayErrorMessage("This Product Group can not be added");
		}
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
		this.getView().enableOK(submit);
	}

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IAddProductGroupView getView()
	{
		return (IAddProductGroupView) super.getView();
	}

	//
	// IAddProductGroupController overrides
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
		this.getView().setProductGroupName(this.name);
		if(sizeUnits == SizeUnits.Count)
			this.getView().setSupplyValue("" + (int)value);
		else
			this.getView().setSupplyValue("" + value);
		this.getView().setSupplyUnit(this.sizeUnits);
	}

	/**
	 * This method is called when any of the fields in the add product group
	 * view is changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		this.name = this.getView().getProductGroupName();
		this.sizeUnits = this.getView().getSupplyUnit();
		if(!getView().getSupplyValue().isEmpty() && !getView().getSupplyValue().startsWith("-"))
		{
			if(this.sizeUnits == SizeUnits.Count)
				try
				{
					this.value = Integer.parseInt(getView().getSupplyValue());
				}
				catch(NumberFormatException e)
				{
					getView().displayErrorMessage("For unit size type Count, the "
						+ "value must be a whole number");
					int temp = (int)this.value;
					getView().setSupplyValue("" + value);
					this.value = temp;
				}
			else
			{
				try
				{
					this.value = Float.parseFloat(getView().getSupplyValue());
				}
				catch(NumberFormatException e)
				{
					getView().displayErrorMessage("Digits only, no characters");
					getView().setSupplyValue("" + value);
				}
			}
		}
		
		this.shouldOKBeEnabled();
		this.enableComponents();
	}

	private void shouldOKBeEnabled()
	{
		if(name.isEmpty())
			submit = false;
		else if(this.getView().getSupplyValue().isEmpty() || getView().getSupplyValue().startsWith("-"))
			submit = false;
		else 
			submit = true;
	}
}
