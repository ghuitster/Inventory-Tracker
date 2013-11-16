
package observer;

import gui.inventory.IInventoryView;
import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.IItem;
import model.IProduct;
import model.IProductContainer;
import model.IProductGroup;
import model.IStorageUnit;
import model.Inventory;

public class InventoryViewUpdater
{
	/**
	 * Reference to the inventoryView we're updating
	 */
	private IInventoryView inventoryView;

	private ProductContainerData root;

	private Inventory inventory;

	public InventoryViewUpdater(IInventoryView inventoryView,
			ProductContainerData root)
	{
		this.inventoryView = inventoryView;
		this.root = root;
		this.inventory = Inventory.getInstance();
	}

	void addProductContainer(IProductContainer container)
	{
		ProductContainerData parent;
		if(container instanceof IStorageUnit)
			parent = root;
		else
		{
			IProductGroup pg = (IProductGroup) container;
			parent = (ProductContainerData) pg.getContainer().getTag();
		}

		String name = container.getName();
		int i;
		for(i = 0; i < parent.getChildCount(); i++)
		{
			if(parent.getChild(i).getName().compareTo(name) > 0)
				break;
		}
		inventoryView.insertProductContainer(parent,
				(ProductContainerData) container.getTag(), i);

		inventoryView.selectProductContainer((ProductContainerData) container
				.getTag());

		populateProductData(container);

	}

	public void addProductContainersRecursively(ProductContainerData parent,
			IProductContainer unit, int index)
	{
		DataUpdater.verifyTagData(unit);

		for(IProduct product: unit.getAllProducts())
			DataUpdater.verifyTagData(product);

		for(IItem item: unit.getAllItems())
			DataUpdater.verifyTagData(item);

		ProductContainerData child = (ProductContainerData) unit.getTag();
		inventoryView.insertProductContainer(parent, child, index);
		int childIndex = 0;
		for(IProductContainer container: unit.getAllProductGroups())
		{
			addProductContainersRecursively(child, container, childIndex++);
		}
	}

	void itemOrProductUpdated(Object changedObj)
	{
		if(changedObj instanceof IItem)
		{
			if(inventoryView.getSelectedProductContainer() != null)
			{
				IProductContainer container =
						(IProductContainer) inventoryView
								.getSelectedProductContainer().getTag();
				populateProductData(container);

				IItem item = (IItem) changedObj;
				inventoryView.selectProduct((ProductData) item.getProduct()
						.getTag());

				populateItemData(container);
			}
		}
		else if(changedObj instanceof IProduct)
		{
			if(inventoryView.getSelectedProductContainer() != null)
			{
				populateProductData((IProductContainer) inventoryView
						.getSelectedProductContainer().getTag());

				IProduct product = (IProduct) changedObj;
				inventoryView.selectProduct((ProductData) product.getTag());
			}

		}
	}

	public void populateItemData(IProductContainer container)
	{
		Set<IItem> items = container != null ? container.getAllItems() : null;
		populateItemData(items);
	}

	private void populateItemData(Set<IItem> items)
	{
		if(inventoryView.getSelectedProduct() != null)
		{
			IProduct selectedProduct =
					(IProduct) inventoryView.getSelectedProduct().getTag();
			List<ItemData> itemDatas = new ArrayList<ItemData>();

			Set<IItem> itemList = items;
			if(selectedProduct != null && itemList == null)
				itemList = inventory.getAllItems(selectedProduct);

			if(selectedProduct != null)
			{
				for(IItem item: itemList)
				{
					if(item.getProduct() == selectedProduct)
						itemDatas.add((ItemData) item.getTag());
				}
			}
			inventoryView.setItems(itemDatas.toArray(new ItemData[0]));
		}
		else
			inventoryView.setItems(new ItemData[0]);
	}

	public void populateProductContainers()
	{
		int index = 0;
		for(IStorageUnit unit: inventory.getAllStorageUnits())
		{
			addProductContainersRecursively(root, unit, index++);
		}
		inventoryView.setProductContainers(root);
	}

	public void populateProductData(IProductContainer container)
	{
		Set<IProduct> products;
		if(container != null)
			products = container.getAllProducts();
		else
			products = inventory.getAllProducts();
		ProductData[] productDatas = new ProductData[products.size()];
		Iterator<IProduct> productIterator = products.iterator();
		for(int i = 0; i < productDatas.length; i++)
		{
			productDatas[i] = (ProductData) productIterator.next().getTag();
			productDatas[i].setCount("0");
		}

		Set<IItem> items;
		if(container != null)
			items = container.getAllItems();
		else
			items = inventory.getAllItems(null);

		for(IItem item: items)
		{
			ProductData pd = (ProductData) item.getProduct().getTag();
			int count = Integer.parseInt(pd.getCount());
			count++;
			pd.setCount(count + "");
		}

		if(inventoryView.getSelectedProduct() != null)
		{
			populateItemData(items);
		}

		inventoryView.setProducts(productDatas);
	}

	void removeProductContainer(IProductContainer container)
	{
		inventoryView.deleteProductContainer((ProductContainerData) container
				.getTag());
	}

	void updateProductContainer(IProductContainer container)
	{
		ProductContainerData pcData = (ProductContainerData) container.getTag();

		ProductContainerData parent;

		int index = 0;
		if(container instanceof IStorageUnit)
			parent = root;
		else
			parent =
					(ProductContainerData) ((IProductGroup) container)
							.getContainer().getTag();

		boolean passedSelf = false;
		for(index = 0; index < parent.getChildCount(); index++)
		{
			if(!parent.getChild(index).getName().equals(pcData.getName()))
			{
				if(parent.getChild(index).getName().compareTo(pcData.getName()) > 0)
					break;
			}
			else
				passedSelf = true;
		}
		if(passedSelf)
			index--;

		inventoryView
				.renameProductContainer(pcData, container.getName(), index);

		inventoryView.selectProductContainer((ProductContainerData) container
				.getTag());
	}
}
