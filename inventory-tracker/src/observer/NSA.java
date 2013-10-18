
package observer;

import gui.inventory.IInventoryView;
import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import model.*;

/**
 * Notification System Arbitrator Receives update notifications from the model
 * and passes them on to the controller
 * @author Brian
 * 
 */
public class NSA implements Observer
{
	private NSA(IInventoryView inventoryView)
	{
		inventory = Inventory.getInstance();
		inventory.addObserver(this);
		this.inventoryView = inventoryView;
	}
	
	private static NSA instance;
	
	public static void init(IInventoryView inventoryView)
	{
		if(instance == null)
			instance = new NSA(inventoryView);
	}
	
	public static NSA getInstance()
	{
		return instance;
	}
	
	/**
	 * Reference to the inventory we're tracking
	 */
	private IInventory inventory;
	
	private IInventoryView inventoryView;

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
		
		switch(obsArgs.getUpdateType())
		{
		case ADDED:
			objectAdded(obsArgs.getChangedObj());
			break;
		case REMOVED:
			objectRemoved(obsArgs.getChangedObj());
			break;
		case UPDATED:
			objectUpdated(obsArgs.getChangedObj());
			break;
		}

	}

	private void objectAdded(Object changedObj)
	{
		if(changedObj instanceof IItem)
		{
			
		}
		else if(changedObj instanceof IProduct)
		{
			
		}
		else if(changedObj instanceof IProductContainer)
		{
			
		}
		
	}

	private void objectRemoved(Object changedObj)
	{
		
		
	}

	private void objectUpdated(Object changedObj)
	{
		if (changedObj instanceof IItem)
		{
			IItem item = (IItem)changedObj;
			ItemData itemData = (ItemData)item.getTag();
			
			updateItemData(item, itemData);
			
			if(item.getContainer() == inventoryView.
					getSelectedProductContainer().getTag())
			{
				populateItemData(item.getContainer());
			}
		}
		else if (changedObj instanceof IProduct)
		{
			IProduct product = (IProduct)changedObj;
			ProductData productData = (ProductData)product.getTag();
			
			updateProductData(product, productData);
					
			if(product.getContainers().contains(inventoryView.
					getSelectedProductContainer().getTag()))
			{
				populateProductData((IProductContainer)inventoryView.
						getSelectedProductContainer().getTag());
			}
			
		}
		else if (changedObj instanceof IProductContainer)
		{
			IProductContainer productContainer = (IProductContainer)changedObj;
			ProductContainerData pcData = 
					(ProductContainerData)productContainer.getTag();
			
			updateProductContainerData(productContainer, pcData);
			
			inventoryView.renameProductContainer(renamedContainer, newName, newIndex);
		}
	}

	private void populateProductData(IProductContainer container)
	{
		Set<IProduct> products = container.getAllProducts();
		ProductData[] productDatas = new ProductData[products.size()];
		Iterator<IProduct> productIterator = products.iterator();
		for(int i = 0; i < productDatas.length; i++)
			productDatas[i] = (ProductData)productIterator.next().getTag();
		inventoryView.setProducts(productDatas);
	}

	private void populateItemData(IProductContainer container)
	{
		Set<IItem> items = container.getAllItems();
		ItemData[] itemDatas = new ItemData[items.size()];
		Iterator<IItem> itemIterator = items.iterator();
		for(int i = 0; i < itemDatas.length; i++)
			itemDatas[i] = (ItemData)itemIterator.next().getTag();
		inventoryView.setItems(itemDatas);
	}

	private void updateProductContainerData(IProductContainer productContainer,
			ProductContainerData pcData)
	{
		pcData.setName(productContainer.getName());
	}

	private void updateProductData(IProduct product, ProductData productData)
	{
		productData.setBarcode(product.getBarcode().toString());
		productData.setDescription(product.getDescription().toString());
		productData.setShelfLife("" + product.getShelfLife());
		productData.setSize(product.getSize().toString());
		productData.setSupply(product.getThreeMonthSupply().toString());
	}

	private void updateItemData(IItem item, ItemData itemData)
	{
		itemData.setBarcode(item.getBarcode().toString());
		itemData.setEntryDate(item.getEntryDate());
		itemData.setExpirationDate(item.getExpirationDate());
		if(item.getContainer() instanceof IProductGroup)
			itemData.setProductGroup(item.getContainer().getName());
		itemData.setStorageUnit(item.getContainer().getStorageUnit().getName());
	}

	/**
	 * Returns a unique bar code
	 * @return A bar code which is unique
	 */
	public long getUniqueBarCode()
	
}
