package gui.inventory;

import gui.common.*;
import gui.item.*;
import gui.product.*;

import java.util.*;

/**
 * Controller class for inventory view.
 */
public class InventoryController extends Controller 
									implements IInventoryController {

	/**
	 * Constructor.
	 *  
	 * @param view Reference to the inventory view
	 */
	public InventoryController(IInventoryView view) {
		super(view);

		construct();
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IInventoryView getView() {
		return (IInventoryView)super.getView();
	}

	/**
	 * Loads data into the controller's view.
	 * 
	 *  {@pre None}
	 *  
	 *  {@post The controller has loaded data into its view}
	 */
	@Override
	protected void loadValues() {
		ProductContainerData root = new ProductContainerData();
		
		ProductContainerData basementCloset = new ProductContainerData("Basement Closet");
		
		ProductContainerData toothpaste = new ProductContainerData("Toothpaste");
		toothpaste.addChild(new ProductContainerData("Kids"));
		toothpaste.addChild(new ProductContainerData("Parents"));
		basementCloset.addChild(toothpaste);
		
		root.addChild(basementCloset);
		
		ProductContainerData foodStorage = new ProductContainerData("Food Storage Room");
		
		ProductContainerData soup = new ProductContainerData("Soup");
		soup.addChild(new ProductContainerData("Chicken Noodle"));
		soup.addChild(new ProductContainerData("Split Pea"));
		soup.addChild(new ProductContainerData("Tomato"));
		foodStorage.addChild(soup);
		
		root.addChild(foodStorage);
		
		getView().setProductContainers(root);
	}

	/**
	 * Sets the enable/disable state of all components in the controller's view.
	 * A component should be enabled only if the user is currently
	 * allowed to interact with that component.
	 * 
	 * {@pre None}
	 * 
	 * {@post The enable/disable state of all components in the controller's view
	 * have been set appropriately.}
	 */
	@Override
	protected void enableComponents() {
		return;
	}
	
	//
	// IInventoryController overrides
	//

	/**
	 * Returns true if and only if the "Add Storage Unit" menu item should be enabled.
	 */
	@Override
	public boolean canAddStorageUnit() {
		return true;
	}
	
	/**
	 * Returns true if and only if the "Add Items" menu item should be enabled.
	 */
	@Override
	public boolean canAddItems() {
		return true;
	}
	
	/**
	 * Returns true if and only if the "Transfer Items" menu item should be enabled.
	 */
	@Override
	public boolean canTransferItems() {
		return true;
	}
	
	/**
	 * Returns true if and only if the "Remove Items" menu item should be enabled.
	 */
	@Override
	public boolean canRemoveItems() {
		return true;
	}

	/**
	 * Returns true if and only if the "Delete Storage Unit" menu item should be enabled.
	 */
	@Override
	public boolean canDeleteStorageUnit() {
		return true;
	}
	
	/**
	 * This method is called when the user selects the "Delete Storage Unit" menu item.
	 */
	@Override
	public void deleteStorageUnit() {
	}

	/**
	 * Returns true if and only if the "Edit Storage Unit" menu item should be enabled.
	 */
	@Override
	public boolean canEditStorageUnit() {
		return true;
	}

	/**
	 * Returns true if and only if the "Add Product Group" menu item should be enabled.
	 */
	@Override
	public boolean canAddProductGroup() {
		return true;
	}

	/**
	 * Returns true if and only if the "Delete Product Group" menu item should be enabled.
	 */
	@Override
	public boolean canDeleteProductGroup() {
		return true;
	}

	/**
	 * Returns true if and only if the "Edit Product Group" menu item should be enabled.
	 */
	@Override
	public boolean canEditProductGroup() {
		return true;
	}
	
	/**
	 * This method is called when the user selects the "Delete Product Group" menu item.
	 */
	@Override
	public void deleteProductGroup() {
	}

	private Random rand = new Random();
	
	private String getRandomBarcode() {
		Random rand = new Random();
		StringBuilder barcode = new StringBuilder();
		for (int i = 0; i < 12; ++i) {
			barcode.append(((Integer)rand.nextInt(10)).toString());
		}
		return barcode.toString();
	}

	/**
	 * This method is called when the selected item container changes.
	 */
	@Override
	public void productContainerSelectionChanged() {
		List<ProductData> productDataList = new ArrayList<ProductData>();		
		ProductContainerData selectedContainer = getView().getSelectedProductContainer();
		if (selectedContainer != null) {
			int productCount = rand.nextInt(20) + 1;
			for (int i = 1; i <= productCount; ++i) {
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
	public void productSelectionChanged() {
		List<ItemData> itemDataList = new ArrayList<ItemData>();		
		ProductData selectedProduct = getView().getSelectedProduct();
		if (selectedProduct != null) {
			Date now = new Date();
			GregorianCalendar cal = new GregorianCalendar();
			int itemCount = Integer.parseInt(selectedProduct.getCount());
			for (int i = 1; i <= itemCount; ++i) {
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
	 * This method is called when the selected item changes.
	 */
	@Override
	public void itemSelectionChanged() {
		return;
	}

	/**
	 * Returns true if and only if the "Delete Product" menu item should be enabled.
	 */
	@Override
	public boolean canDeleteProduct() {
		return true;
	}

	/**
	 * This method is called when the user selects the "Delete Product" menu item.
	 */
	@Override
	public void deleteProduct() {
	}

	/**
	 * Returns true if and only if the "Edit Item" menu item should be enabled.
	 */
	@Override
	public boolean canEditItem() {
		return true;
	}

	/**
	 * This method is called when the user selects the "Edit Item" menu item.
	 */
	@Override
	public void editItem() {
		getView().displayEditItemView();
	}

	/**
	 * Returns true if and only if the "Remove Item" menu item should be enabled.
	 */
	@Override
	public boolean canRemoveItem() {
		return true;
	}

	/**
	 * This method is called when the user selects the "Remove Item" menu item.
	 */
	@Override
	public void removeItem() {
	}

	/**
	 * Returns true if and only if the "Edit Product" menu item should be enabled.
	 */
	@Override
	public boolean canEditProduct() {
		return true;
	}

	/**
	 * This method is called when the user selects the "Add Product Group" menu item.
	 */
	@Override
	public void addProductGroup() {
		getView().displayAddProductGroupView();
	}
	
	/**
	 * This method is called when the user selects the "Add Items" menu item.
	 */
	@Override
	public void addItems() {
		getView().displayAddItemBatchView();
	}
	
	/**
	 * This method is called when the user selects the "Transfer Items" menu item.
	 */
	@Override
	public void transferItems() {
		getView().displayTransferItemBatchView();
	}
	
	/**
	 * This method is called when the user selects the "Remove Items" menu item.
	 */
	@Override
	public void removeItems() {
		getView().displayRemoveItemBatchView();
	}

	/**
	 * This method is called when the user selects the "Add Storage Unit" menu item.
	 */
	@Override
	public void addStorageUnit() {
		getView().displayAddStorageUnitView();
	}

	/**
	 * This method is called when the user selects the "Edit Product Group" menu item.
	 */
	@Override
	public void editProductGroup() {
		getView().displayEditProductGroupView();
	}

	/**
	 * This method is called when the user selects the "Edit Storage Unit" menu item.
	 */
	@Override
	public void editStorageUnit() {
		getView().displayEditStorageUnitView();
	}

	/**
	 * This method is called when the user selects the "Edit Product" menu item.
	 */
	@Override
	public void editProduct() {
		getView().displayEditProductView();
	}
	
	/**
	 * This method is called when the user drags a product into a
	 * product container.
	 * 
	 * @param productData Product dragged into the target product container
	 * @param containerData Target product container
	 */
	@Override
	public void addProductToContainer(ProductData productData, 
										ProductContainerData containerData) {		
	}

	/**
	 * This method is called when the user drags an item into
	 * a product container.
	 * 
	 * @param itemData Item dragged into the target product container
	 * @param containerData Target product container
	 */
	@Override
	public void moveItemToContainer(ItemData itemData,
									ProductContainerData containerData) {
	}

}

