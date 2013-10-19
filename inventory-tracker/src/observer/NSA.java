
package observer;

import gui.inventory.IInventoryView;
import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import common.util.DateUtils;
import model.*;

/**
 * Notification System Arbitrator Receives update notifications from the model
 * and passes them on to the controller
 * @author Brian
 * 
 */
public class NSA implements Observer
{
	private NSA(IInventoryView inventoryView, ProductContainerData root)
	{
		inventory = Inventory.getInstance();
		inventory.addObserver(this);
		this.inventoryView = inventoryView;
		this.root = root;
	}
	
	private static NSA instance;
	
	public static void init(IInventoryView inventoryView, ProductContainerData root)
	{
		if(instance == null)
			instance = new NSA(inventoryView, root);
	}
	
	public static NSA getInstance()
	{
		return instance;
	}
	
	/**
	 * Reference to the inventory we're tracking
	 */
	private IInventory inventory;
	
	private ProductContainerData root;
	
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
		
		verifyObjTag(obsArgs.getChangedObj());
		
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

	private void verifyObjTag(Object changedObj)
	{
		if(changedObj instanceof IItem)
		{
			IItem item = (IItem)changedObj;
			ItemData itemData;
			if(item.getTag() != null)
				itemData = (ItemData)item.getTag();
			else itemData = new ItemData();
			updateItemData(item, itemData);
		}
		else if(changedObj instanceof IProduct)
		{
			IProduct product = (IProduct)changedObj;
			ProductData productData;
			if(product.getTag() != null)
				productData = (ProductData)product.getTag();
			else productData = new ProductData();
			updateProductData(product, productData);
		}
		else if(changedObj instanceof IProductContainer)
		{
			IProductContainer productContainer = (IProductContainer)changedObj;
			ProductContainerData pcData = 
					(ProductContainerData)productContainer.getTag();
			if(productContainer.getTag() != null)
				pcData = (ProductContainerData)productContainer.getTag();
			else pcData = new ProductContainerData();
			updateProductContainerData(productContainer, pcData);
		}
	}

	private void objectAdded(Object changedObj)
	{
		if(changedObj instanceof IItem || changedObj instanceof IProduct)
		{
			itemOrProductUpdated(changedObj);
		}
		else if(changedObj instanceof IProductContainer)
		{
			IProductContainer productContainer = (IProductContainer)changedObj;
			addProductContainer(productContainer);
		}
		
	}

	private void objectRemoved(Object changedObj)
	{
		if(changedObj instanceof IItem || changedObj instanceof IProduct)
		{
			itemOrProductUpdated(changedObj);
		}
		else if(changedObj instanceof IProductContainer)
		{
			IProductContainer productContainer = (IProductContainer)changedObj;
			removeProductContainer(productContainer);
		}
	}

	private void objectUpdated(Object changedObj)
	{
		if(changedObj instanceof IItem || changedObj instanceof IProduct)
		{
			itemOrProductUpdated(changedObj);
		}
		else if (changedObj instanceof IProductContainer)
		{
			IProductContainer productContainer = (IProductContainer)changedObj;
			updateProductContainer(productContainer);
		}
	}
	
	private void itemOrProductUpdated(Object changedObj)
	{
		if (changedObj instanceof IItem)
		{
			IItem item = (IItem)changedObj;
			
			if(itemIsCurrentlyVisible(item))
			{
				populateItemData(item.getContainer());
			}
		}
		else if (changedObj instanceof IProduct)
		{
			IProduct product = (IProduct)changedObj;
					
			if(productIsCurrentlyVisible(product))
			{
				populateProductData((IProductContainer)inventoryView.
						getSelectedProductContainer().getTag());
			}
			
		}
	}

	private boolean productIsCurrentlyVisible(IProduct product)
	{
		return product.getContainers().contains(inventoryView.
				getSelectedProductContainer().getTag());
	}

	private boolean itemIsCurrentlyVisible(IItem item)
	{
		return item.getContainer() == inventoryView.
				getSelectedProductContainer().getTag();
	}

	public void populateProductContainers()
	{
		int index = 0;
		for(IStorageUnit unit : inventory.getAllStorageUnits())
		{
			addProductContainersRecursively(root, unit, index++);
		}
		inventoryView.setProductContainers(root);
	}
	
	public void addProductContainersRecursively(ProductContainerData parent,
			IProductContainer unit, int index)
	{
		verifyObjTag(unit);
		
		for(IProduct product : unit.getAllProducts())
			verifyObjTag(product);
		
		for(IItem item : unit.getAllItems())
			verifyObjTag(item);
		
		ProductContainerData child = (ProductContainerData)unit.getTag();
		inventoryView.insertProductContainer(parent, child, index);
		int childIndex = 0;
		for(IProductContainer container : unit.getAllProductGroups())
		{
			addProductContainersRecursively(child, container, childIndex++);
		}
	}

	public void populateProductData(IProductContainer container)
	{
		Set<IProduct> products = container.getAllProducts();
		ProductData[] productDatas = new ProductData[products.size()];
		Iterator<IProduct> productIterator = products.iterator();
		for(int i = 0; i < productDatas.length; i++)
			productDatas[i] = (ProductData)productIterator.next().getTag();
		inventoryView.setProducts(productDatas);
	}

	public void populateItemData(IProductContainer container)
	{
		IProduct selectedProduct = null;
		if(inventoryView.getSelectedProduct() != null)
			selectedProduct = (IProduct)inventoryView.getSelectedProduct().getTag();

		Set<IItem> items = container.getAllItems();
		List<ItemData> itemDatas = new ArrayList<ItemData>();
		for(IItem item : items)
		{
			if(selectedProduct == null || item.getProduct() == selectedProduct)
			itemDatas.add((ItemData)item.getTag());
		}
		inventoryView.setItems(itemDatas.toArray(new ItemData[0]));
	}
	
	public void addProductContainer(IProductContainer container)
	{
		ProductContainerData parent;
		if(container instanceof IStorageUnit)
			parent = root;
		else
		{
			IProductGroup pg = (IProductGroup)container;
			parent = (ProductContainerData)pg.getContainer().getTag();
		}
		
		String name = container.getName();
		int i;
		for(i = 0; i < parent.getChildCount(); i++)
		{
			if(parent.getChild(i).getName().compareTo(name) > 0)
				break;
		}
		inventoryView.insertProductContainer(parent, 
				(ProductContainerData)container.getTag(), i);
		inventoryView.setProductContainers(root);
		
	}
	
	private void removeProductContainer(IProductContainer container)
	{
		inventoryView.deleteProductContainer
			((ProductContainerData)container.getTag());
		inventoryView.setProductContainers(root);
	}
	
	private void updateProductContainer(IProductContainer container)
	{
		ProductContainerData pcData = 
				(ProductContainerData)container.getTag();
		
		ProductContainerData parent;
		
		int index = 0;
		if(container instanceof IStorageUnit)
			parent = root;
		else
			parent = (ProductContainerData)((IProductGroup)container)
				.getContainer().getTag();
		
		boolean passedSelf = false;
		for(index = 0; index < root.getChildCount(); index++)
		{
			if(!parent.getChild(index).getName().equals(pcData.getName()))
			{
				if(parent.getChild(index).getName()
						.compareTo(pcData.getName()) > 0)
					break;
			}
			else passedSelf = true;
		}
		if(passedSelf)
			index--;
		
		inventoryView.renameProductContainer(pcData, 
				container.getName(), index);
		inventoryView.setProductContainers(root);
	}

	private void updateProductContainerData(IProductContainer productContainer,
			ProductContainerData pcData)
	{
		pcData.setName(productContainer.getName());
		productContainer.setTag(pcData);
		pcData.setTag(productContainer);
	}

	private void updateProductData(IProduct product, ProductData productData)
	{
		productData.setBarcode(product.getBarcode().toString());
		productData.setDescription(product.getDescription().toString());
		productData.setShelfLife("" + product.getShelfLife());
		productData.setSize(product.getSize().toString());
		productData.setSupply(product.getThreeMonthSupply().toString());
		product.setTag(productData);
		productData.setTag(product);
	}

	private void updateItemData(IItem item, ItemData itemData)
	{
		itemData.setBarcode(item.getBarcode().toString());
		itemData.setEntryDate(item.getEntryDate());
		itemData.setExpirationDate(item.getExpirationDate());
		if(item.getContainer() instanceof IProductGroup)
			itemData.setProductGroup(item.getContainer().getName());
		itemData.setStorageUnit(item.getContainer().getStorageUnit().getName());
		item.setTag(itemData);
		itemData.setTag(item);
	}


}
