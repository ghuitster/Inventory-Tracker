
package gui.batches;

import gui.common.Controller;
import gui.common.IView;
import gui.inventory.ProductContainerData;

import java.util.Date;

/**
 * Controller class for the add item batch view.
 */
public class AddItemBatchController extends Controller implements
		IAddItemBatchController
{
	private final ProductContainerData target;
	private final boolean useBarcodeScanner;
	private final Date entryDate;
	private int count;
	private boolean validCount;
	private final String barcode;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the add item batch view.
	 * @param target Reference to the storage unit to which items are being
	 *            added.
	 */
	public AddItemBatchController(IView view, ProductContainerData target)
	{
		super(view);
		this.target = target;
		count = Integer.parseInt(((IAddItemBatchView) (_view)).getCount());
		entryDate = ((IAddItemBatchView) (_view)).getEntryDate();
		useBarcodeScanner = ((IAddItemBatchView) (_view)).getUseScanner();
		validCount = true;
		barcode = ((IAddItemBatchView) (_view)).getBarcode();

		construct();
	}

	/**
	 * This method is called when the user clicks the "Add Item" button in the
	 * add item batch view.
	 */
	@Override
	public void addItem()
	{
		// if(product doesn't exist in system) open the AddProductView;
		// add the product to the products section
		// add the item to some temp list to be added when the user clicks done
	}

	/**
	 * This method is called when the "Product Barcode" field in the add item
	 * batch view is changed by the user.
	 */
	@Override
	public void barcodeChanged()
	{
		// enable the "Add Item" button
		// If the "Use barcode scanner" checkbox is filled call addItem
	}

	/**
	 * This method is called when the "Count" field in the add item batch view
	 * is changed by the user.
	 */
	@Override
	public void countChanged()
	{
		try
		{
			count = Integer.parseInt(((IAddItemBatchView) (_view)).getCount());
			validCount = true;

		}
		catch(NumberFormatException nfe)
		{
			validCount = false;
		}
		finally
		{
			enableComponents();
		}
	}

	/**
	 * This method is called when the user clicks the "Done" button in the add
	 * item batch view.
	 */
	@Override
	public void done()
	{
		// if(we created a new product) add that to the system and the selected
		// product container;
		// add the product and items to the target product container
		// print barcodes for all the items added
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
		// only Done is enabled at the start
		// Add Item gets enabled when the Product Barcode field is not empty
		// Undo gets enabled when we have clicked Add Item or Redo
		// Redo gets enabled when we have clicked Undo
	}

	/**
	 * This method is called when the "Entry Date" field in the add item batch
	 * view is changed by the user.
	 */
	@Override
	public void entryDateChanged()
	{
		// change some member entry date variable
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IAddItemBatchView getView()
	{
		return (IAddItemBatchView) super.getView();
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
		// Use barcode scanner check box is filled
		// Entry Date field set to today
		// Count field set to 1
	}

	/**
	 * This method is called when the user clicks the "Redo" button in the add
	 * item batch view.
	 */
	@Override
	public void redo()
	{
		// don't have to implement yet
	}

	/**
	 * This method is called when the selected product changes in the add item
	 * batch view.
	 */
	@Override
	public void selectedProductChanged()
	{
		// change what items are displayed in the Items pane
	}

	/**
	 * This method is called when the user clicks the "Undo" button in the add
	 * item batch view.
	 */
	@Override
	public void undo()
	{
		// don't have to implement yet
	}

	/**
	 * This method is called when the "Use Barcode Scanner" setting in the add
	 * item batch view is changed by the user.
	 */
	@Override
	public void useScannerChanged()
	{
		// change some member barcode scanner variable
	}
}
