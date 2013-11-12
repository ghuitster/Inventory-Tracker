
package gui.reports.supply;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.Inventory;
import model.ProductSupplyReport;
import model.report.HTMLReportBuilder;
import model.report.IReportBuilder;
import model.report.NMonthSupplyReport;
import model.report.PDFReportBuilder;
import model.report.Report;
import gui.common.Controller;
import gui.common.FileFormat;
import gui.common.IView;

/**
 * Controller class for the N-month supply report view.
 */
public class SupplyReportController extends Controller implements
		ISupplyReportController
{
	private int month;
	private boolean submit;

	 /**
	 * Constructor.
	 * 
	 * @param view Reference to the N-month supply report view
	 */
	public SupplyReportController(IView view)
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
	 * This method is called when the user clicks the "OK" button in the N-month
	 * supply report view.
	 */
	@Override
	public void display()
	{
		IReportBuilder builder = null;
		String fileType = "";
		if(this.getView().getFormat() == FileFormat.PDF)
		{
			builder = new PDFReportBuilder();
			fileType = ".pdf";
		}
		else
		{
			builder = new HTMLReportBuilder();
			fileType = ".html";
		}
		
		ProductSupplyReport productSupplyReport = Inventory.getInstance().getLowSupplies(this.month);
		
		Report report = new NMonthSupplyReport(productSupplyReport, builder);
		
		report.createReport(this.makePath(fileType));
	}
	
	private String makePath(String fileType)
	{
		  String timeStamp =
				  new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
				  .getInstance().getTime());
		  String filename =
				  "Reports" + File.separator + "NMonthSupplyReport-" + timeStamp
				  + fileType;
		return filename;
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
	protected ISupplyReportView getView()
	{
		return (ISupplyReportView) super.getView();
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
	{
		this.getView().setMonths("" + this.month);
	}

	/**
	 * This method is called when any of the fields in the N-month supply report
	 * view is changed by the user.
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
