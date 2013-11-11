
package gui.batches;

import gui.common.Controller;
import gui.common.IView;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import model.IItem;
import model.IProduct;
import model.Inventory;
import model.command.Command;
import observer.DataUpdater;

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
	private Stack<Command> done;
	private Stack<Command> undone;

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
		
		this.done = new Stack<Command>();
		this.undone = new Stack<Command>();

		construct();
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
		getView().enableRedo(!this.undone.isEmpty());
		getView().enableUndo(!this.done.isEmpty());
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

		ItemData removingItemData = DataUpdater.createItemData(removingItem);
		IProduct removingProduct = removingItem.getProduct();
		ProductData removingProductData =
				DataUpdater.createProductData(removingProduct);
		removingProductData.setCount("1");
		displayProducts.add(removingProductData);
		items.add(removingItem);
		addItems(removingProductData, removingItemData);
		
		for(IItem ii: items)
			if(ii.getContainer().ableToRemoveItem(ii))
				ii.getContainer().removeItem(ii);
		items.clear();
		
		ProductData[] temp = new ProductData[displayProducts.size()];
		getView().setProducts(displayProducts.toArray(temp));
		barcode = "";
		loadValues();
		enableComponents();
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
