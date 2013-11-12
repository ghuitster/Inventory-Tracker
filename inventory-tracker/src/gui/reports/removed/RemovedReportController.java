
package gui.reports.removed;

import gui.common.Controller;
import gui.common.FileFormat;
import gui.common.IView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import model.Inventory;
import model.RemovedItems;
import model.report.HTMLReportBuilder;
import model.report.IReportBuilder;
import model.report.PDFReportBuilder;
import model.report.RemovedItemsReport;
import model.report.Report;

/**
 * Controller class for the removed items report view.
 */
public class RemovedReportController extends Controller implements
		IRemovedReportController
{

	private boolean sinceLastReport;
	private boolean sinceDate;
	private boolean sinceDateValue;
	private boolean submit = false;
	private Date reportFromLastdate = null;
	private Date date;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the removed items report view
	 */
	public RemovedReportController(IView view)
	{
		super(view);

		this.reportFromLastdate =
				Inventory.getInstance().getLastRemovedItemReportTime();

		if(this.reportFromLastdate == null)
		{
			this.sinceLastReport = false;
			this.sinceDate = true;
			this.sinceDateValue = true;
		}
		else
		{
			this.sinceLastReport = true;
			this.sinceDate = true;
			this.sinceDateValue = false;
		}

		this.submit = true;

		construct();
	}

	//
	// Controller overrides
	//

	/**
	 * This method is called when the user clicks the "OK" button in the removed
	 * items report view.
	 */
	@Override
	public void display()
	{
		Set<RemovedItems> removedItems =
				Inventory.getInstance().getRemovedItems(date);
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
		List<RemovedItems> temp = new ArrayList<RemovedItems>(removedItems);
		Report report = new RemovedItemsReport(temp, this.date, builder);

		report.createReport(this.makePath(fileType));
	}

	private String makePath(String fileType)
	{
		String timeStamp =
				new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
						.getInstance().getTime());
		String filename =
				"Reports" + File.separator + "RemovedItemsReport-" + timeStamp
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
		this.getView().enableFormat(true);
		this.getView().enableOK(submit);
		this.getView().enableSinceLast(sinceLastReport);
		this.getView().enableSinceDate(sinceDate);
		this.getView().enableSinceDateValue(sinceDateValue);
	}

	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IRemovedReportView getView()
	{
		return (IRemovedReportView) super.getView();
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
		this.getView().setSinceLast(this.sinceLastReport);
		this.getView().setSinceDate(!this.sinceLastReport);
		if(this.sinceLastReport)
		{
			this.getView().setSinceLastValue(this.reportFromLastdate);
			this.date = this.reportFromLastdate;
		}
		else
		{
			this.date = this.getView().getSinceDateValue();
		}
	}

	/**
	 * This method is called when any of the fields in the removed items report
	 * view is changed by the user.
	 */
	@Override
	public void valuesChanged()
	{
		if(this.getView().getSinceDate())
			this.sinceDateValue = true;
		else
			this.sinceDateValue = false;
		if(this.getView().getSinceLast())
		{
			this.date = this.reportFromLastdate;
		}
		else
		{
			this.date = this.getView().getSinceDateValue();
		}

		this.enableComponents();
	}

}
