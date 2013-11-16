
package gui.item;

import gui.common.Controller;
import gui.common.IView;

import java.util.Date;

import model.IItem;

import common.util.DateUtils;

/**
 * Controller class for the edit item view.
 */
public class EditItemController extends Controller implements
		IEditItemController
{

	private boolean submit = false;
	private IItem item = null;
	private Date entryDate = null;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to edit item view
	 * @param target Item that is being edited
	 */
	public EditItemController(IView view, ItemData target)
	{
		super(view);
		this.item = (IItem) target.getTag();
		this.entryDate = this.item.getEntryDate();
		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the edit
	 * item view.
	 */
	@Override
	public void editItem()
	{
		this.item.setEntryDate(this.entryDate);
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
		this.getView().enableBarcode(false);
		this.getView().enableDescription(false);
		this.getView().enableEntryDate(true);
		this.getView().enableOK(this.submit);
	}

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IEditItemView getView()
	{
		return (IEditItemView) super.getView();
	}

	//
	// IEditItemController overrides
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
		this.getView().setBarcode(this.item.getBarcode().toString());
		this.getView().setDescription(
				this.item.getProduct().getDescription().getDescription());
		this.getView().setEntryDate(this.entryDate);
		this.submit = true;
		this.enableComponents();
	}

	private boolean valiDate(Date date)
	{
		date = DateUtils.removeTimeFromDate(date);
		boolean response = false;

		if((date != null)
				&& (!date.before(DateUtils.removeTimeFromDate(DateUtils
						.earliestDate())))
				&& (!date.after(DateUtils.removeTimeFromDate(DateUtils
						.currentDate()))))
		{
			response = true;
		}

		return response;
	}

	/**
	 * This method is called when any of the fields in the edit item view is
	 * changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		Date requestedDate = this.getView().getEntryDate();

		if(requestedDate == null)
		{
			this.submit = false;
		}
		else
		{
			boolean valid = this.valiDate(requestedDate);
			if(valid)
			{
				this.entryDate = requestedDate;
				this.submit = true;
			}
			else
			{
				this.submit = false;
			}
		}
		this.enableComponents();
	}

}
