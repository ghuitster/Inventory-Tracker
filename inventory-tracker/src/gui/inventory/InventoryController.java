
package gui.inventory;

import gui.common.Controller;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import model.*;

/**
 * Controller class for inventory view.
 */
public class InventoryController extends Controller implements
		IInventoryController
{

	private Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the inventory view
	 */
	public InventoryController(IInventoryView view)
	{
		super(view);

		construct();
	}

	/**
	 * This method is called when the user selects the "Add Items" menu item.
	 */
	@Override
	public void addItems()
	{
		getView().displayAddItemBatchView();
	}

	/**
	 * This method is called when the user selects the "Add Product Group" menu
	 * item.
	 */
	@Override
	public void addProductGroup()
	{
		getView().displayAddProductGroupView();
	}

	//
	// IInventoryController overrides
	//

	/**
	 * This method is called when the user drags a product into a product
	 * container.
	 * 
	 * @param productData Product dragged into the target product container
	 * @param containerData Target product container
	 */
	@Override
	public void addProductToContainer(ProductData productData,
			ProductContainerData containerData)
	{
		BaseProductContainer PC = (ProductContainer) containerData.getTag();
		BaseProduct product = (BaseProduct) productData.getTag();
		if(PC.ableToAddProduct(product))
		{
			PC.addProduct(product);
		}
	}

	/**
	 * This method is called when the user selects the "Add Storage Unit" menu
	 * item.
	 */
	@Override
	public void addStorageUnit()
	{
		getView().displayAddStorageUnitView();
	}

	/**
	 * Returns true if and only if the "Add Items" menu item should be enabled.
	 */
	@Override
	public boolean canAddItems()
	{
		boolean able = false;
		if(getView().getSelectedProductContainer() != null)
			able = true;
		return able;
	}

	/**
	 * Returns true if and only if the "Add Product Group" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canAddProductGroup()
	{
		return true;
	}

	/**
	 * Returns true if and only if the "Add Storage Unit" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canAddStorageUnit()
	{
		return true;
	}

	/**
	 * Returns true if and only if the "Delete Product" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canDeleteProduct()
	{
		boolean able = false;
		if(getView().getSelectedProductContainer() != null)
		{
			BaseProductContainer PC = (BaseProductContainer) getView().getSelectedProductContainer().getTag();
			able = PC.ableToRemoveProduct((BaseProduct) getView().getSelectedProduct().getTag());
		}
		return able;
	}

	/**
	 * Returns true if and only if the "Delete Product Group" menu item should
	 * be enabled.
	 */
	@Override
	public boolean canDeleteProductGroup()
	{
		
		return true;
	}

	/**
	 * Returns true if and only if the "Delete Storage Unit" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canDeleteStorageUnit()
	{
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Item" menu item should be enabled.
	 */
	@Override
	public boolean canEditItem()
	{
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Product" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canEditProduct()
	{
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Product Group" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canEditProductGroup()
	{
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Storage Unit" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canEditStorageUnit()
	{
		return true;
	}

	/**
	 * Returns true if and only if the "Remove Item" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canRemoveItem()
	{
		return true;
	}

	/**
	 * Returns true if and only if the "Remove Items" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canRemoveItems()
	{
		return true;
	}

	/**
	 * Returns true if and only if the "Transfer Items" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canTransferItems()
	{
		return true;
	}

	/**
	 * This method is called when the user selects the "Delete Product" menu
	 * item.
	 */
	@Override
	public void deleteProduct()
	{
		BaseProductContainer PC = (BaseProductContainer) getView().getSelectedProductContainer().getTag();
		PC.removeProduct((BaseProduct) getView().getSelectedProduct().getTag());
	}

	/**
	 * This method is called when the user selects the "Delete Product Group"
	 * menu item.
	 */
	@Override
	public void deleteProductGroup()
	{
		BaseProductContainer PC = (BaseProductContainer) getView().getSelectedProductContainer().getTag();
		//PC.removeProductGroup(PC);
	}

	/**
	 * This method is called when the user selects the "Delete Storage Unit"
	 * menu item.
	 */
	@Override
	public void deleteStorageUnit()
	{
		
	}

	/**
	 * This method is called when the user selects the "Edit Item" menu item.
	 */
	@Override
	public void editItem()
	{
		getView().displayEditItemView();
	}

	/**
	 * This method is called when the user selects the "Edit Product" menu item.
	 */
	@Override
	public void editProduct()
	{
		getView().displayEditProductView();
	}

	/**
	 * This method is called when the user selects the "Edit Product Group" menu
	 * item.
	 */
	@Override
	public void editProductGroup()
	{
		getView().displayEditProductGroupView();
	}

	/**
	 * This method is called when the user selects the "Edit Storage Unit" menu
	 * item.
	 */
	@Override
	public void editStorageUnit()
	{
		getView().displayEditStorageUnitView();
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
		return;
	}

	private String getRandomBarcode()
	{
		Random rand = new Random();
		StringBuilder barcode = new StringBuilder();
		for(int i = 0; i < 12; ++i)
		{
			barcode.append(((Integer) rand.nextInt(10)).toString());
		}
		return barcode.toString();
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IInventoryView getView()
	{
		return (IInventoryView) super.getView();
	}

	/**
	 * This method is called when the selected item changes.
	 */
	@Override
	public void itemSelectionChanged()
	{
		return;
	}

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
		ProductContainerData root = new ProductContainerData();

		ProductContainerData basementCloset =
				new ProductContainerData("Basement Closet");

		ProductContainerData toothpaste =
				new ProductContainerData("Toothpaste");
		toothpaste.addChild(new ProductContainerData("Kids"));
		toothpaste.addChild(new ProductContainerData("Parents"));
		basementCloset.addChild(toothpaste);

		root.addChild(basementCloset);

		ProductContainerData foodStorage =
				new ProductContainerData("Food Storage Room");

		ProductContainerData soup = new ProductContainerData("Soup");
		soup.addChild(new ProductContainerData("Chicken Noodle"));
		soup.addChild(new ProductContainerData("Split Pea"));
		soup.addChild(new ProductContainerData("Tomato"));
		foodStorage.addChild(soup);

		root.addChild(foodStorage);

		getView().setProductContainers(root);
	}

	/**
	 * This method is called when the user drags an item into a product
	 * container.
	 * 
	 * @param itemData Item dragged into the target product container
	 * @param containerData Target product container
	 */
	@Override
	public void moveItemToContainer(ItemData itemData,
			ProductContainerData containerData)
	{
		
	}

	/**
	 * This method is called when the selected item container changes.
	 */
	@Override
	public void productContainerSelectionChanged()
	{
		List<ProductData> productDataList = new ArrayList<ProductData>();
		ProductContainerData selectedContainer =
				getView().getSelectedProductContainer();
		if(selectedContainer != null)
		{
			int productCount = rand.nextInt(20) + 1;
			for(int i = 1; i <= productCount; ++i)
			{
				ProductData productData = new ProductData();
				productData.setBarcode(getRandomBarcode());
				int itemCount = rand.nextInt(25) + 1;
				productData.setCount(Integer.toString(itemCount));
				productData.setDescription("Item " + i);
				productData.setShelfLife("3 months");
				productData.setSize("1 pounds");
				productData.setSupply("10 count");

				productDataList.add(productData);
			}
		}
		getView().setProducts(productDataList.toArray(new ProductData[0]));

		getView().setItems(new ItemData[0]);
	}

	/**
	 * This method is called when the selected item changes.
	 */
	@Override
	public void productSelectionChanged()
	{
		List<ItemData> itemDataList = new ArrayList<ItemData>();
		ProductData selectedProduct = getView().getSelectedProduct();
		if(selectedProduct != null)
		{
			Date now = new Date();
			GregorianCalendar cal = new GregorianCalendar();
			int itemCount = Integer.parseInt(selectedProduct.getCount());
			for(int i = 1; i <= itemCount; ++i)
			{
				cal.setTime(now);
				ItemData itemData = new ItemData();
				itemData.setBarcode(getRandomBarcode());
				cal.add(Calendar.MONTH, -rand.nextInt(12));
				itemData.setEntryDate(cal.getTime());
				cal.add(Calendar.MONTH, 3);
				itemData.setExpirationDate(cal.getTime());
				itemData.setProductGroup("Some Group");
				itemData.setStorageUnit("Some Unit");

				itemDataList.add(itemData);
			}
		}
		getView().setItems(itemDataList.toArray(new ItemData[0]));
	}

	/**
	 * This method is called when the user selects the "Remove Item" menu item.
	 */
	@Override
	public void removeItem()
	{
		
	}

	/**
	 * This method is called when the user selects the "Remove Items" menu item.
	 */
	@Override
	public void removeItems()
	{
		getView().displayRemoveItemBatchView();
	}

	/**
	 * This method is called when the user selects the "Transfer Items" menu
	 * item.
	 */
	@Override
	public void transferItems()
	{
		getView().displayTransferItemBatchView();
	}

}
