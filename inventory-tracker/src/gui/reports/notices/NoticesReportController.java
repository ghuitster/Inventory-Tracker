
package gui.reports.notices;

import java.util.List;
import java.util.Map;

import model.IProduct;
import model.IProductContainer;
import model.IProductGroup;
import model.Inventory;
import model.report.HTMLReportBuilder;
import model.report.IReportBuilder;
import model.report.NoticesReport;
import model.report.PDFReportBuilder;
import model.report.Report;
import gui.common.Controller;
import gui.common.FileFormat;
import gui.common.IView;

/**
 * Controller class for the notices report view.
 */
public class NoticesReportController extends Controller implements
		INoticesReportController
{

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the notices report view
	 */
	public NoticesReportController(IView view)
	{
		super(view);

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the notices
	 * report view.
	 */
	@Override
	public void display()
	{
		IReportBuilder builder = null;
		if(this.getView().getFormat() == FileFormat.PDF)
			builder = new PDFReportBuilder();
		else
			builder = new HTMLReportBuilder();
		Map<IProductGroup, List<IProduct>> inconsistentGroups =
				Inventory.getInstance().getInconsistencies();
		
		Report report = new NoticesReport(inconsistentGroups, builder);
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
	protected INoticesReportView getView()
	{
		return (INoticesReportView) super.getView();
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
	 * This method is called when any of the fields in the notices report view
	 * is changed by the user.
	 */
	@Override
	public void valuesChanged()
	{}

}
