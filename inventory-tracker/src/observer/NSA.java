
package observer;

import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.Observable;
import java.util.Observer;

import model.*;

/**
 * Notification System Arbitrator Receives update notifications from the model
 * and passes them on to the controller
 * @author Brian
 * 
 */
public class NSA implements Observer
{
	public NSA()
	{
		inventory = Inventory.getInstance();
		inventory.addObserver(this);
	}
	
	/**
	 * Reference to the inventory we're tracking
	 */
	private IInventory inventory;

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
		ObservableArgs obsArgs = (ObservableArgs)arg;
		
		if(obsArgs.getUpdateType() == UpdateType.UPDATED)
		{
			if (obsArgs.getChangedObj() instanceof IItem)
			{
				IItem item = (IItem)obsArgs.getChangedObj();
				ItemData itemData = (ItemData)item.getTag();
				
				itemData.setBarcode(item.getBarcode().toString());
				itemData.setEntryDate(item.getEntryDate());
				itemData.setExpirationDate(item.getExpirationDate());
				if(item.getContainer() instanceof IProductGroup)
					itemData.setProductGroup(item.getContainer().getName());
				itemData.setStorageUnit(item.getContainer().getStorageUnit().getName());
				
			}
			else if (obsArgs.getChangedObj() instanceof IProduct)
			{
				IProduct product = (IProduct)obsArgs.getChangedObj();
				ProductData productData = (ProductData)product.getTag();
				
				productData.setBarcode(product.getBarcode().toString());
				productData.setDescription(product.getDescription().toString());
				productData.setShelfLife("" + product.getShelfLife());
				productData.setSize(product.getSize().toString());
				productData.setSupply(product.getThreeMonthSupply().toString());
						
			}
			else if (obsArgs.getChangedObj() instanceof IProductContainer)
			{
				IProductContainer productContainer = (IProductContainer)obsArgs.getChangedObj();
				ProductContainerData pcData = (ProductContainerData)productContainer.getTag();
				
				pcData.setName(productContainer.getName());
			}
		}

	}

}
