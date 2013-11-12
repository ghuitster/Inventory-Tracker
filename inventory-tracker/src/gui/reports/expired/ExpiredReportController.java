
package gui.reports.expired;

import java.util.List;

import model.IItem;
import model.Inventory;
import model.report.ExpItemsReport;
import model.report.HTMLReportBuilder;
import model.report.IReportBuilder;
import model.report.PDFReportBuilder;
import model.report.Report;
import gui.common.Controller;
import gui.common.FileFormat;
import gui.common.IView;

/**
 * Controller class for the expired items report view.
 */
public class ExpiredReportController extends Controller implements
		IExpiredReportController
{

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the expired items report view
	 */
	public ExpiredReportController(IView view)
	{
		super(view);

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the expired
	 * items report view.
	 */
	@Override
	public void display()
	{
		IReportBuilder builder = null;
		if(this.getView().getFormat() == FileFormat.PDF)
		{
			builder = new PDFReportBuilder();
		}
		else
		{
			builder = new HTMLReportBuilder();
		}
		
		List<IItem> expired = Inventory.getInstance().getExpiredItems();
		
		Report report = new ExpItemsReport(expired, builder);
		
		report.createReport();
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
	{}

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IExpiredReportView getView()
	{
		return (IExpiredReportView) super.getView();
	}

	//
	// IExpiredReportController overrides
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
	{}

	/**
	 * This method is called when any of the fields in the expired items report
	 * view is changed by the user.
	 */
	@Override
	public void valuesChanged()
	{}

}
