
package gui.reports.productstats;

import java.util.List;

import model.Inventory;
import model.ProductStats;
import model.report.HTMLReportBuilder;
import model.report.IReportBuilder;
import model.report.PDFReportBuilder;
import model.report.ProdStatReport;
import model.report.Report;
import gui.common.Controller;
import gui.common.FileFormat;
import gui.common.IView;

/**
 * Controller class for the product statistics report view.
 */
public class ProductStatsReportController extends Controller implements
		IProductStatsReportController
{
	private int month;
	private boolean submit;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the item statistics report view
	 */
	public ProductStatsReportController(IView view)
	{
		super(view);
		
		this.month = 3;
		this.submit = true;

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the product
	 * statistics report view.
	 */
	@Override
	public void display()
	{
		IReportBuilder builder = null;
		if(this.getView().getFormat() == FileFormat.PDF)
			builder = new PDFReportBuilder();
		else
			builder = new HTMLReportBuilder();
		
		List<ProductStats> productStats = Inventory.getInstance().getProductStats(this.month);
		
		Report report = new ProdStatReport(productStats, builder, this.month);
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
	{
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
	protected IProductStatsReportView getView()
	{
		return (IProductStatsReportView) super.getView();
	}

	//
	// IProductStatsReportController overrides
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
		this.getView().setMonths("" + this.month);
	}

	/**
	 * This method is called when any of the fields in the product statistics
	 * report view is changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		if(!this.getView().getMonths().startsWith("-"))
		{
			try
			{
				this.month = Integer.parseInt(this.getView().getMonths());
				this.submit = true;
			}
			catch(NumberFormatException e)
			{
				this.submit = false;
			}
		}
		else
			this.submit = true;
		
		this.enableComponents();
	}

}
