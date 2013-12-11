
package gui.productgroup;

import gui.common.Controller;
import gui.common.IView;
import gui.common.SizeUnitUtils;
import gui.common.SizeUnits;
import gui.inventory.ProductContainerData;
import model.Amount;
import model.CountThreeMonthSupply;
import model.IProductContainer;
import model.IProductGroup;
import model.ProductGroup;
import model.ThreeMonthSupply;
import model.UnitType;

/**
 * Controller class for the add product group view.
 */
public class AddProductGroupController extends Controller implements
		IAddProductGroupController
{

	private final IProductContainer PC;
	private IProductGroup PG = null;
	private boolean amountHasBeenSet = false;
	private boolean validProductGroup = false;
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
			this.threeMonthSupply = new CountThreeMonthSupply((int) this.value);
		else
			this.threeMonthSupply = new ThreeMonthSupply(this.value, unitType);
		PG = new ProductGroup(this.name, this.threeMonthSupply);
		if(PC.ableToAddProductGroup(PG))
		{
			System.out.println("Apparently We can add this product group.");
			PC.addProductGroup(PG);
			PG.setContainer(PC);
		}
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
		this.getView().enableOK(this.shouldOKBeEnabled());
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
			this.getView().setSupplyValue("" + (int) value);
		else
			this.getView().setSupplyValue("" + value);
		this.getView().setSupplyUnit(this.sizeUnits);
	}

	private void setAmount()
	{
		if(this.sizeUnits == SizeUnits.Count)
			try
			{
				float temp = Float.parseFloat(getView().getSupplyValue());
				if(temp % 1 == 0)
				{
					this.value = (int) temp;
					this.amountHasBeenSet = true;
				}
				else
				{
					this.amountHasBeenSet = false;
				}
			}
			catch(NumberFormatException e)
			{
				this.amountHasBeenSet = false;
			}
		else
		{
			try
			{
				this.value = Float.parseFloat(getView().getSupplyValue());
				this.amountHasBeenSet = true;
			}
			catch(NumberFormatException e)
			{
				this.amountHasBeenSet = false;
			}
		}
	}

	private boolean shouldOKBeEnabled()
	{
		return this.validProductGroup && this.amountHasBeenSet;
	}

	/**
	 * This method is called when any of the fields in the add product group
	 * view is changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		this.name = this.getView().getProductGroupName();
		if(!this.name.isEmpty()
				&& this.PC.ableToAddProductGroup(new ProductGroup(this.name,
						new CountThreeMonthSupply(0))))
			this.validProductGroup = true;
		else
			this.validProductGroup = false;
		this.sizeUnits = this.getView().getSupplyUnit();
		if(!getView().getSupplyValue().isEmpty()
				&& !getView().getSupplyValue().startsWith("-"))
		{
			this.setAmount();
		}
		else
		{
			this.amountHasBeenSet = false;
		}

		this.enableComponents();
	}
}
