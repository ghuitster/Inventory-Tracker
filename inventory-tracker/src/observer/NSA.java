
package observer;

import java.util.Observable;
import java.util.Observer;

import model.BaseInventory;
import model.Inventory;

/**
 * Notification System Arbitrator Receives update notifications from the model
 * and passes them on to the controller
 * @author Brian
 * 
 */
public class NSA implements Observer
{
	private NSA()
	{
		inventory = Inventory.getInstance();
		inventory.addObserver(this);
	}
	
	private NSA instance;
	public NSA getInstance()
	{
		if(instance == null)
			instance = new NSA();
		return instance;
	}
	
	/**
	 * Reference to the inventory we're tracking
	 */
	private BaseInventory inventory;

	/**
	 * Receiving function for then an observable in the system changes
	 * @param o The Observable that changed
	 * @param arg Type object for the interface, but is expected to be
	 *            enumeration type
	 * @pre arg is of type model.ObservableArg. o and arg are not null.
	 * @post The change described in arg has been reported to the controller, to
	 *       be refreshed in the view UpdateType. Described what the change was
	 *       to the Observable
	 */
	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub

	}

}
