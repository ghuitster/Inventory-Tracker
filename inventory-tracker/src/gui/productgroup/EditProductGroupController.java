
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
import model.ThreeMonthSupply;
import model.UnitType;

/**
 * Controller class for the edit product group view.
 */
public class EditProductGroupController extends Controller implements
		IEditProductGroupController
{

	private IProductGroup PG;
	private SizeUnits sizeUnits = SizeUnits.Count;
	private boolean submit = true;
	private String name = "";
	private float value;
	private UnitType unitType = UnitType.COUNT;
	private Amount threeMonthSupply;
	private IProductContainer container;
	private boolean PGNameChanged = false;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to edit product group view
	 * @param target Product group being edited
	 */
	public EditProductGroupController(IView view, ProductContainerData target)
	{
		super(view);

		PG = (IProductGroup) target.getTag();
		this.container = PG.getContainer();
		this.createSizeUnitsFromUnitType();
		this.name = PG.getName();
		if(this.sizeUnits == SizeUnits.Count)
			this.value =
					((CountThreeMonthSupply) PG.getThreeMonthSupply())
							.getAmount();
		else
			this.value =
					((ThreeMonthSupply) PG.getThreeMonthSupply()).getAmount();

		construct();
	}

	private void createSizeUnitsFromUnitType()
	{
		UnitType unitType = this.PG.getThreeMonthSupply().getUnitType();
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
	 * product group view.
	 */
	@Override
	public void editProductGroup()
	{
		createUnitType();
		if(unitType == UnitType.COUNT)
			this.threeMonthSupply = new CountThreeMonthSupply((int) this.value);
		else
			this.threeMonthSupply = new ThreeMonthSupply(this.value, unitType);
		PG.setName(this.name);
		PG.setThreeMonthSupply(this.threeMonthSupply);
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
	protected IEditProductGroupView getView()
	{
		return (IEditProductGroupView) super.getView();
	}

	//
	// IEditProductGroupController overrides
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
		if(this.sizeUnits == SizeUnits.Count)
		{
			int temp = (int) value;
			this.getView().setSupplyValue("" + temp);
		}
		else
		{
			this.getView().setSupplyValue("" + value);
		}
		this.getView().setSupplyUnit(this.sizeUnits);
	}

	private void shouldOKBeEnabled()
	{
		if(name.isEmpty())
			submit = false;
		else if(this.getView().getSupplyValue().isEmpty()
				|| getView().getSupplyValue().startsWith("-"))
			submit = false;
		else if(!this.container.ableToAddProductGroupNamed(getView()
				.getProductGroupName()) && this.PGNameChanged)
			submit = false;
		else
			submit = true;
	}

	/**
	 * This method is called when any of the fields in the edit product group
	 * view is changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		if(!this.name.equals(this.getView().getProductGroupName()))
			this.PGNameChanged = true;
		this.name = this.getView().getProductGroupName();
		this.sizeUnits = this.getView().getSupplyUnit();
		if(!getView().getSupplyValue().isEmpty()
				&& !getView().getSupplyValue().startsWith("-"))
			if(this.sizeUnits == SizeUnits.Count)
				try
				{
					this.value = Integer.parseInt(getView().getSupplyValue());
				}
				catch(NumberFormatException e)
				{
					getView().displayErrorMessage(
							"For unit size type Count, the "
									+ "value must be a whole number");
					int temp = (int) value;
					getView().setSupplyValue("" + temp);
				}
			else
			{
				this.value = Float.parseFloat(getView().getSupplyValue());
			}

		this.shouldOKBeEnabled();
		this.enableComponents();
	}
}
