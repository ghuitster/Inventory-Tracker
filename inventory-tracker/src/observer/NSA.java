
package observer;

import gui.inventory.IInventoryView;
import gui.inventory.ProductContainerData;

import java.util.Observable;
import java.util.Observer;

import model.IInventory;
import model.IItem;
import model.IProduct;
import model.IProductContainer;
import model.Inventory;
import model.ObservableArgs;

/**
 * Notification System Arbitrator Receives update notifications from the model
 * and passes them on to the controller
 * @author Brian
 * 
 */
public class NSA implements Observer
{
	private static NSA instance;

	public static NSA getInstance()
	{
		return instance;
	}

	public static void init(IInventoryView inventoryView,
			ProductContainerData root)
	{
		if(instance == null)
			instance = new NSA(inventoryView, root);
	}

	/**
	 * Reference to the inventory we're tracking
	 */
	private IInventory inventory;

	private InventoryViewUpdater inventoryViewUpdater;

	private NSA(IInventoryView inventoryView, ProductContainerData root)
	{
		inventory = Inventory.getInstance();
		inventory.addObserver(this);

		this.inventoryViewUpdater =
				new InventoryViewUpdater(inventoryView, root);
	}

	private void objectAdded(Object changedObj)
	{
		if(changedObj instanceof IItem || changedObj instanceof IProduct)
		{
			inventoryViewUpdater.itemOrProductUpdated(changedObj);
		}
		else if(changedObj instanceof IProductContainer)
		{
			IProductContainer productContainer = (IProductContainer) changedObj;
			inventoryViewUpdater.addProductContainer(productContainer);
		}

	}

	private void objectRemoved(Object changedObj)
	{
		if(changedObj instanceof IItem || changedObj instanceof IProduct)
		{
			inventoryViewUpdater.itemOrProductUpdated(changedObj);
		}
		else if(changedObj instanceof IProductContainer)
		{
			IProductContainer productContainer = (IProductContainer) changedObj;
			inventoryViewUpdater.removeProductContainer(productContainer);
		}
	}

	private void objectUpdated(Object changedObj)
	{
		if(changedObj instanceof IItem || changedObj instanceof IProduct)
		{
			inventoryViewUpdater.itemOrProductUpdated(changedObj);
		}
		else if(changedObj instanceof IProductContainer)
		{
			IProductContainer productContainer = (IProductContainer) changedObj;
			inventoryViewUpdater.updateProductContainer(productContainer);
		}
	}

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
		ObservableArgs obsArgs = (ObservableArgs) arg;

		if(obsArgs.getChangedObj() instanceof IItem)
			DataUpdater.verifyTagData((IItem) obsArgs.getChangedObj());
		else if(obsArgs.getChangedObj() instanceof IProduct)
			DataUpdater.verifyTagData((IProduct) obsArgs.getChangedObj());
		else if(obsArgs.getChangedObj() instanceof IProductContainer)
			DataUpdater.verifyTagData((IProductContainer) obsArgs
					.getChangedObj());

		switch(obsArgs.getUpdateType())
		{
		case ADDED:
			objectAdded(obsArgs.getChangedObj());
			break;
		case REMOVED:
		case TEMP_REMOVED:
		case PERMANENTLY_REMOVED:
			objectRemoved(obsArgs.getChangedObj());
			break;
		case UPDATED:
			objectUpdated(obsArgs.getChangedObj());
			break;
		}

	}

}
