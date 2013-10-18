
package gui.main;

import model.*;
import model.exception.SerializerException;
import gui.common.Controller;

/**
 * Controller class for the main view. The main view allows the user to print
 * reports and exit the application.
 */
public class MainController extends Controller implements IMainController
{

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the main view
	 */
	public MainController(IMainView view)
	{
		super(view);

		construct();
	}

	/**
	 * Returns true if and only if the "Exit" menu item should be enabled.
	 */
	@Override
	public boolean canExit()
	{
		return true;
	}

	//
	// IMainController overrides
	//

	/**
	 * Returns true if and only if the "Expired Items" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canPrintExpiredReport()
	{
		return false;
	}

	/**
	 * Returns true if and only if the "Notices" menu item should be enabled.
	 */
	@Override
	public boolean canPrintNoticesReport()
	{
		return false;
	}

	/**
	 * Returns true if and only if the "Product Statistics" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canPrintProductReport()
	{
		return false;
	}

	/**
	 * Returns true if and only if the "Removed Items" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canPrintRemovedReport()
	{
		return false;
	}

	/**
	 * Returns true if and only if the "N-Month Supply" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canPrintSupplyReport()
	{
		return false;
	}

	/**
	 * This method is called when the user exits the application.
	 */
	@Override
	public void exit()
	{
		IPersistence persistence = new Serializer("data.Inventory");
		try
		{
			persistence.saveData();
		}
		catch(SerializerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IMainView getView()
	{
		return (IMainView) super.getView();
	}

	/**
	 * This method is called when the user selects the "Expired Items" menu
	 * item.
	 */
	@Override
	public void printExpiredReport()
	{
		getView().displayExpiredReportView();
	}

	/**
	 * This method is called when the user selects the "Notices" menu item.
	 */
	@Override
	public void printNoticesReport()
	{
		getView().displayNoticesReportView();
	}

	/**
	 * This method is called when the user selects the "Product Statistics" menu
	 * item.
	 */
	@Override
	public void printProductReport()
	{
		getView().displayProductReportView();
	}

	/**
	 * This method is called when the user selects the "Removed Items" menu
	 * item.
	 */
	@Override
	public void printRemovedReport()
	{
		getView().displayRemovedReportView();
	}

	/**
	 * This method is called when the user selects the "N-Month Supply" menu
	 * item.
	 */
	@Override
	public void printSupplyReport()
	{
		getView().displaySupplyReportView();
	}

}
