
package gui.inventory;

import gui.common.Controller;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.Random;

import model.IItem;
import model.IPersistence;
import model.IProduct;
import model.IProductContainer;
import model.Inventory;
import model.Item;
import model.ProductContainer;
import model.ProductGroup;
import model.StorageUnit;
import observer.NSA;

/**
 * Controller class for inventory view.
 */
public class InventoryController extends Controller implements
		IInventoryController
{

	private ProductContainerData treeRoot;
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
		IProductContainer PC = (ProductContainer) containerData.getTag();
		IProduct product = (IProduct) productData.getTag();
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
		return getView().getSelectedProductContainer() != null;
	}

	/**
	 * Returns true if and only if the "Add Product Group" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canAddProductGroup()
	{
		return getView().getSelectedProductContainer() != null;
	}

	/**
	 * Returns true if and only if the "Add Storage Unit" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canAddStorageUnit()
	{
		return getView().getSelectedProductContainer() != null;
	}

	/**
	 * Returns true if and only if the "Delete Product" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canDeleteProduct()
	{
		boolean able = false;
		if(getView().getSelectedProductContainer() != null
				&& getView().getSelectedProduct() != null)
		{
			IProductContainer PC =
					(IProductContainer) getView().getSelectedProductContainer()
							.getTag();
			IProduct selectedProduct =
					(IProduct) getView().getSelectedProduct().getTag();
			if(PC != null)
				able = PC.ableToRemoveProduct(selectedProduct);
			else
				able =
						Inventory.getInstance().ableToRemoveProduct(
								selectedProduct);
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
		boolean able = false;
		ProductGroup PG =
				(ProductGroup) getView().getSelectedProductContainer().getTag();
		if(PG != null)
			if(PG.getAllItems().size() == 0 && PG.getAllProducts().size() == 0)
				able = true;
		return able;
	}

	/**
	 * Returns true if and only if the "Delete Storage Unit" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canDeleteStorageUnit()
	{
		boolean able = false;
		Inventory invent = Inventory.getInstance();
		StorageUnit SU =
				(StorageUnit) getView().getSelectedProductContainer().getTag();
		if(invent.ableToRemoveStorageUnit(SU))
			able = true;
		return able;
	}

	/**
	 * Returns true if and only if the "Edit Item" menu item should be enabled.
	 */
	@Override
	public boolean canEditItem()
	{
		return getView().getSelectedItem() != null;
	}

	/**
	 * Returns true if and only if the "Edit Product" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canEditProduct()
	{
		return getView().getSelectedProduct() != null;
	}

	/**
	 * Returns true if and only if the "Edit Product Group" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canEditProductGroup()
	{
		return getView().getSelectedProductContainer() != null;
	}

	/**
	 * Returns true if and only if the "Edit Storage Unit" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canEditStorageUnit()
	{
		return getView().getSelectedProductContainer() != null;
	}

	/**
	 * Returns true if and only if the "Remove Item" menu item should be
	 * enabled.
	 */
	@Override
	public boolean canRemoveItem()
	{
		return getView().getSelectedItem() != null;
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
		return getView().getSelectedItem() != null;
	}

	/**
	 * This method is called when the user selects the "Delete Product" menu
	 * item.
	 */
	@Override
	public void deleteProduct()
	{
		IProduct selectedProduct =
				(IProduct) getView().getSelectedProduct().getTag();
		IProductContainer PC =
				(IProductContainer) getView().getSelectedProductContainer()
						.getTag();
		if(PC != null)
			PC.removeProduct(selectedProduct);
		else
			Inventory.getInstance().removeProduct(selectedProduct);
	}

	/**
	 * This method is called when the user selects the "Delete Product Group"
	 * menu item.
	 */
	@Override
	public void deleteProductGroup()
	{
		ProductGroup PG =
				(ProductGroup) getView().getSelectedProductContainer().getTag();
		IProductContainer PC = PG.getContainer();
		PC.removeProductGroup(PG);
	}

	/**
	 * This method is called when the user selects the "Delete Storage Unit"
	 * menu item.
	 */
	@Override
	public void deleteStorageUnit()
	{
		Inventory invent = Inventory.getInstance();
		StorageUnit SU =
				(StorageUnit) getView().getSelectedProductContainer().getTag();
		invent.removeStorageUnit(SU);
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
		treeRoot = root;
		try
		{
			IPersistence persistence = Inventory.getInstance().getPersistence();
			if(persistence.canLoadData())
				Inventory.getInstance().getPersistence().loadData();
		}
		catch(Exception e)
		{}

		getView().setProductContainers(root);

		NSA.init(this.getView(), root);
		NSA.getInstance().populateProductContainers();
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
		IProductContainer PC =
				(IProductContainer) getView().getSelectedProductContainer()
						.getTag();
		PC.transferItem((Item) itemData.getTag(),
				(ProductContainer) containerData.getTag());
	}

	/**
	 * This method is called when the selected item container changes.
	 */
	@Override
	public void productContainerSelectionChanged()
	{
		ProductContainerData selectedContainer =
				getView().getSelectedProductContainer();
		if(selectedContainer != null)
		{
			NSA.getInstance().populateProductData(
					(IProductContainer) selectedContainer.getTag());
			NSA.getInstance().populateItemData(
					(IProductContainer) selectedContainer.getTag());
		}

	}

	/**
	 * This method is called when the selected item changes.
	 */
	@Override
	public void productSelectionChanged()
	{

		ProductContainerData selectedContainer =
				getView().getSelectedProductContainer();

		if(selectedContainer != null)
			NSA.getInstance().populateItemData(
					(IProductContainer) selectedContainer.getTag());
	}

	/**
	 * This method is called when the user selects the "Remove Item" menu item.
	 */
	@Override
	public void removeItem()
	{
		IItem selectedItem = (IItem) getView().getSelectedItem().getTag();
		IProductContainer PC = selectedItem.getContainer();
		PC.removeItem(selectedItem);
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
