
package gui.batches;

import gui.common.Controller;
import gui.common.IView;
import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for the transfer item batch view.
 */
public class TransferItemBatchController extends Controller implements
		ITransferItemBatchController
{
	private String barcode;
	private boolean useBarcodeScanner;
	private final Map<ProductData, List<ItemData>> displayItems;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the transfer item batch view.
	 * @param target Reference to the storage unit to which items are being
	 *            transferred.
	 */
	public TransferItemBatchController(IView view, ProductContainerData target)
	{
		super(view);
		barcode = "";
		useBarcodeScanner = true;
		displayItems = new HashMap<ProductData, List<ItemData>>();

		construct();
	}

	/**
	 * This method is called when the "Item Barcode" field in the transfer item
	 * batch view is changed by the user.
	 */
	@Override
	public void barcodeChanged()
	{
		barcode = getView().getBarcode();
		enableComponents();
	}

	/**
	 * This method is called when the user clicks the "Done" button in the
	 * transfer item batch view.
	 */
	@Override
	public void done()
	{
		// TODO
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
	protected ITransferItemBatchView getView()
	{
		return (ITransferItemBatchView) super.getView();
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
		getView().setBarcode(barcode);
		getView().setUseScanner(useBarcodeScanner);
	}

	/**
	 * This method is called when the user clicks the "Redo" button in the
	 * transfer item batch view.
	 */
	@Override
	public void redo()
	{
		// not implemented yet
		return;
	}

	/**
	 * This method is called when the selected product changes in the transfer
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
	 * This method is called when the user clicks the "Transfer Item" button in
	 * the transfer item batch view.
	 */
	@Override
	public void transferItem()
	{
		// TODO
	}

	/**
	 * This method is called when the user clicks the "Undo" button in the
	 * transfer item batch view.
	 */
	@Override
	public void undo()
	{
		// not implemented yet
		return;
	}

	/**
	 * This method is called when the "Use Barcode Scanner" setting in the
	 * transfer item batch view is changed by the user.
	 */
	@Override
	public void useScannerChanged()
	{
		useBarcodeScanner = getView().getUseScanner();
		enableComponents();
	}
}
