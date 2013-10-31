
package gui.batches;

import gui.common.Controller;
import gui.common.IView;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.CountAmount;
import model.IItem;
import model.IProduct;
import model.Inventory;
import model.NonCountAmount;
import model.ProductGroup;

/**
 * Controller class for the remove item batch view.
 */
public class RemoveItemBatchController extends Controller implements
		IRemoveItemBatchController
{
	private String barcode;
	private boolean useBarcodeScanner;
	private final Map<ProductData, List<ItemData>> displayItems;
	private final List<ProductData> displayProducts;
	private final List<IItem> items;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the remove item batch view.
	 */
	public RemoveItemBatchController(IView view)
	{
		super(view);
		barcode = "";
		useBarcodeScanner = true;
		displayItems = new HashMap<ProductData, List<ItemData>>();
		displayProducts = new ArrayList<ProductData>();
		items = new ArrayList<IItem>();

		construct();
	}

	/**
	 * This method is called when the "Item Barcode" field is changed in the
	 * remove item batch view by the user.
	 */
	@Override
	public void barcodeChanged()
	{
		barcode = getView().getBarcode();

		if(useBarcodeScanner)
			removeItem();

		enableComponents();
	}

	/**
	 * This method is called when the user clicks the "Done" button in the
	 * remove item batch view.
	 */
	@Override
	public void done()
	{
		if(items.isEmpty())
			getView().close();

		for(IItem ii: items)
			if(ii.getContainer().ableToRemoveItem(ii))
				ii.getContainer().removeItem(ii);

		getView().close();
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
		getView().enableRedo(false);
		getView().enableUndo(false);
		getView().enableItemAction(!barcode.isEmpty());
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IRemoveItemBatchView getView()
	{
		return (IRemoveItemBatchView) super.getView();
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
		getView().setUseScanner(useBarcodeScanner);
		getView().setBarcode(barcode);
	}

	/**
	 * This method is called when the user clicks the "Redo" button in the
	 * remove item batch view.
	 */
	@Override
	public void redo()
	{
		// not implemented yet
		return;
	}

	private ItemData constructItemData(IItem item)
	{
		ItemData itemData = new ItemData();
		itemData.setBarcode(barcode);
		itemData.setEntryDate(item.getEntryDate());
		itemData.setExpirationDate(item.getExpirationDate());

		if(item.getContainer() instanceof ProductGroup)
			itemData.setProductGroup(item.getContainer().getName());
		else
			itemData.setStorageUnit(item.getContainer().getName());

		itemData.setTag(item);

		return itemData;
	}

	private ProductData constructProductData(IProduct product)
	{
		ProductData productData = new ProductData();
		productData.setBarcode(product.getBarcode().getNumber());
		productData.setCount("1");
		productData.setDescription(product.getDescription().getDescription());
		productData.setShelfLife(product.getShelfLife() + "");

		if(product.getSize() instanceof CountAmount)
			productData.setSize(((CountAmount) product.getSize()).getAmount()
					+ "");
		else
			productData.setSize(((NonCountAmount) product.getSize())
					.getAmount() + "");

		if(product.getThreeMonthSupply() instanceof CountAmount)
			productData.setSupply(((CountAmount) product.getThreeMonthSupply())
					.getAmount() + "");
		else
			productData.setSupply(((NonCountAmount) product
					.getThreeMonthSupply()).getAmount() + "");

		productData.setTag(product);

		return productData;
	}

	/**
	 * This method is called when the user clicks the "Remove Item" button in
	 * the remove item batch view.
	 */
	@Override
	public void removeItem()
	{
		if(barcode.isEmpty())
		{
			getView().displayErrorMessage("Empty barcode");
			return;
		}

		IItem removingItem = Inventory.getInstance().getItem(barcode);

		if(removingItem == null)
		{
			getView().displayErrorMessage("No item with that barcode");
			barcode = "";
			enableComponents();
			loadValues();
			return;
		}

		ItemData removingItemData = constructItemData(removingItem);
		IProduct removingProduct = removingItem.getProduct();
		ProductData removingProductData = constructProductData(removingProduct);
		displayProducts.add(removingProductData);
		items.add(removingItem);
		addItems(removingProductData, removingItemData);

		ProductData[] temp = new ProductData[displayProducts.size()];
		getView().setProducts(displayProducts.toArray(temp));
		barcode = "";
		loadValues();
		enableComponents();
	}

	private void addItems(ProductData productData, ItemData itemData)
	{
		if(displayItems.get(productData) == null)
		{
			List<ItemData> tempList = new ArrayList<ItemData>();
			tempList.add(itemData);
			displayItems.put(productData, tempList);
		}
		else
			displayItems.get(productData).add(itemData);
	}

	/**
	 * This method is called when the selected product changes in the remove
	 * item batch view.
	 */
	@Override
	public void selectedProductChanged()
	{
		ItemData[] temp =
				new ItemData[displayItems.get(getView().getSelectedProduct())
						.size()];
		getView().setItems(
				displayItems.get(getView().getSelectedProduct()).toArray(temp));

		enableComponents();
	}

	/**
	 * This method is called when the user clicks the "Undo" button in the
	 * remove item batch view.
	 */
	@Override
	public void undo()
	{
		// not implemented yet
		return;
	}

	/**
	 * This method is called when the "Use Barcode Scanner" setting is changed
	 * in the remove item batch view by the user.
	 */
	@Override
	public void useScannerChanged()
	{
		useBarcodeScanner = getView().getUseScanner();
		enableComponents();
	}
}
