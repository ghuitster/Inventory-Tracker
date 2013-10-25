
package gui.batches;

import gui.common.Controller;
import gui.common.IView;
import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.BarcodeLabelPage;
import model.CountAmount;
import model.CountThreeMonthSupply;
import model.IItem;
import model.IProduct;
import model.Inventory;
import model.Item;
import model.ItemBarcode;
import model.NonCountAmount;
import model.Product;
import model.ProductContainer;
import model.ProductGroup;
import model.StorageUnit;

import com.itextpdf.text.DocumentException;
import common.util.DateUtils;

/**
 * Controller class for the add item batch view.
 */
public class AddItemBatchController extends Controller implements
		IAddItemBatchController
{
	private final ProductContainerData target;
	private boolean useBarcodeScanner;
	private boolean validDate;
	private Date entryDate;
	private final Date currentDate;
	private int count;
	private boolean validCount;
	private String barcode;
	private final Map<ProductData, List<ItemData>> displayItems;
	private final List<ProductData> displayProducts;
	private final List<IItem> items;
	private final List<IProduct> products;

	public static IProduct product;

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
		count = 1;
		entryDate = DateUtils.removeTimeFromDate(DateUtils.currentDate());
		this.currentDate =
				DateUtils.removeTimeFromDate(DateUtils.currentDate());
		useBarcodeScanner = true;
		validCount = true;
		this.validDate = false;
		barcode = "";
		displayItems = new HashMap<ProductData, List<ItemData>>();
		displayProducts = new ArrayList<ProductData>();
		items = new ArrayList<IItem>();
		products = new ArrayList<IProduct>();
		product = null;

		construct();
	}

	private IProduct createProductFromStatic()
	{
		return new Product(AddItemBatchController.product.getCreationDate(),
				AddItemBatchController.product.getDescription()
						.getDescription(),
				AddItemBatchController.product.getBarcode(),
				AddItemBatchController.product.getSize(),
				AddItemBatchController.product.getShelfLife(),
				(CountThreeMonthSupply) AddItemBatchController.product
						.getThreeMonthSupply());
	}

	private ProductData createProductData(IProduct product)
	{
		ProductData result = new ProductData();
		result.setBarcode(product.getBarcode().getNumber());
		result.setCount(count + "");
		result.setDescription(product.getDescription().getDescription());
		result.setShelfLife(product.getShelfLife() + "");

		if(product.getSize() instanceof CountAmount)
			result.setSize(((CountAmount) product.getSize()).getUnitType() + "");
		else
			result.setSize(((NonCountAmount) product.getSize()).getUnitType()
					+ "");

		if(product.getThreeMonthSupply() instanceof CountAmount)
			result.setSupply(((CountAmount) product.getThreeMonthSupply())
					.getAmount() + "");
		else
			result.setSupply(((NonCountAmount) product.getThreeMonthSupply())
					.getAmount() + "");

		result.setTag(product);

		return result;
	}

	private boolean valiDate(Date date)
	{
		date = DateUtils.removeTimeFromDate(date);
		boolean response = false;

		if((date != null)
				&& (!date.before(DateUtils.removeTimeFromDate(DateUtils
						.earliestDate())))
				&& (!date.after(DateUtils.removeTimeFromDate(DateUtils
						.currentDate()))))
		{
			response = true;
		}

		return response;
	}

	/**
	 * This method is called when the user clicks the "Add Item" button in the
	 * add item batch view.
	 */
	@Override
	public void addItem()
	{
		if(!validCount)
		{
			getView().displayErrorMessage("Invalid count");
			return;
		}
		//
		// if(entryDate.after(DateUtils.currentDate()))
		// {
		// getView().displayErrorMessage("Invalid entry date");
		// return;
		// }
		//
		if(barcode.isEmpty())
		{
			getView().displayErrorMessage("Empty barcode");
			return;
		}

		boolean productExistsInSystem = false;
		ProductData addingProductData = null;
		IProduct addingProduct = null;
		boolean productDataExistsInDialog = false;

		for(IProduct ip: Inventory.getInstance().getAllProducts())
			if(ip.getBarcode().getNumber().equals(barcode))
			{
				productExistsInSystem = true;
				addingProduct = ip;
			}

		for(ProductData pd: displayProducts)
			if(pd.getBarcode().equals(barcode))
			{
				productDataExistsInDialog = true;
				addingProductData = pd;
			}

		if(productExistsInSystem)
		{
			if(productDataExistsInDialog)
				addingProductData.setCount(Integer.parseInt(addingProductData
						.getCount()) + count + "");
			else
			{
				addingProductData = createProductData(addingProduct);
				displayProducts.add(addingProductData);
				products.add(addingProduct);
			}
		}
		else
		{
			if(productDataExistsInDialog)
			{
				addingProductData.setCount(Integer.parseInt(addingProductData
						.getCount()) + count + "");
				addingProduct = (IProduct) addingProductData.getTag();
			}
			else
			{
				AddItemBatchController.product = null;
				getView().displayAddProductView();

				if(AddItemBatchController.product == null)
					return;

				addingProduct = createProductFromStatic();
				products.add(addingProduct);
				addingProductData = createProductData(addingProduct);
				displayProducts.add(addingProductData);
			}
		}

		for(int i = 0; i < count; i++)
		{
			Calendar expiration = Calendar.getInstance();
			expiration.set(Calendar.MONTH, entryDate.getMonth());
			expiration.set(Calendar.DAY_OF_MONTH, entryDate.getDate());
			expiration.set(Calendar.YEAR, entryDate.getYear() + 1900);
			expiration.add(Calendar.MONTH, addingProduct.getShelfLife());
			IItem tempItem = null;

			if(addingProduct.getShelfLife() != 0)
			{
				tempItem =
						new Item(addingProduct, new ItemBarcode(Inventory
								.getInstance().getUniqueBarCode() + ""),
								entryDate,
								DateUtils.removeTimeFromDate(expiration
										.getTime()));
			}
			else
			{
				tempItem =
						new Item(addingProduct, new ItemBarcode(Inventory
								.getInstance().getUniqueBarCode() + ""),
								entryDate, null);
			}

			items.add(tempItem);
			ItemData tempItemData = new ItemData();
			tempItemData.setBarcode(tempItem.getBarcode().getNumber());
			tempItemData.setEntryDate(entryDate);
			tempItemData.setExpirationDate(tempItem.getExpirationDate());
			tempItemData.setProductGroup("");

			if(target.getTag() instanceof ProductGroup)
				tempItemData.setProductGroup(((ProductGroup) target.getTag())
						.getName());

			tempItemData.setStorageUnit(((StorageUnit) target.getTag())
					.getName());

			tempItemData.setTag(tempItem);
			if(displayItems.get(addingProductData) == null)
			{
				List<ItemData> tempList = new ArrayList<ItemData>();
				tempList.add(tempItemData);
				displayItems.put(addingProductData, tempList);
			}
			else
				displayItems.get(addingProductData).add(tempItemData);
		}

		count = 1;
		// entryDate = DateUtils.currentDate();
		validCount = true;
		barcode = "";
		loadValues();
		ProductData[] temp = new ProductData[displayProducts.size()];
		getView().setProducts(displayProducts.toArray(temp));
		enableComponents();
	}

	/**
	 * This method is called when the "Product Barcode" field in the add item
	 * batch view is changed by the user.
	 */
	@Override
	public void barcodeChanged()
	{
		barcode = getView().getBarcode();

		if(useBarcodeScanner)
			addItem();

		enableComponents();
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
			count = Integer.parseInt(getView().getCount());
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
		if(products.isEmpty() || items.isEmpty())
		{
			getView().close();
			return;
		}

		for(IProduct ip: products)
			if(((ProductContainer) target.getTag()).ableToAddProduct(ip))
				((ProductContainer) target.getTag()).addProduct(ip);

		for(IItem ii: items)
			if(((ProductContainer) target.getTag()).ableToAddItem(ii))
				((ProductContainer) target.getTag()).addItem(ii);

		BarcodeLabelPage printer = new BarcodeLabelPage(items);
		try
		{
			printer.createPDF();
		}
		catch(DocumentException e)
		{
			getView().displayErrorMessage(
					"There was a barcode label creation error");
		}
		catch(IOException e)
		{
			getView().displayErrorMessage(
					"There was a barcode label creation error");
		}

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
		if(validCount && !barcode.isEmpty() && validDate)
			getView().enableItemAction(true);
		else
			getView().enableItemAction(false);

		getView().enableUndo(false);
		getView().enableRedo(false);

	}

	/**
	 * This method is called when the "Entry Date" field in the add item batch
	 * view is changed by the user.
	 */
	@Override
	public void entryDateChanged()
	{
		Date requestedDate = this.getView().getEntryDate();

		if(requestedDate == null)
		{
			this.validDate = false;
		}
		else
		{
			boolean valid = this.valiDate(requestedDate);
			if(valid)
			{
				this.entryDate = requestedDate;
				this.validDate = true;
			}
			else
			{
				this.validDate = false;
			}
		}
		this.enableComponents();
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
		getView().setBarcode(barcode);
		getView().setCount(count + "");
		getView().setEntryDate(this.entryDate);
		getView().setUseScanner(useBarcodeScanner);
	}

	/**
	 * This method is called when the user clicks the "Redo" button in the add
	 * item batch view.
	 */
	@Override
	public void redo()
	{
		return;
		// don't have to implement yet
	}

	/**
	 * This method is called when the selected product changes in the add item
	 * batch view.
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
	 * This method is called when the user clicks the "Undo" button in the add
	 * item batch view.
	 */
	@Override
	public void undo()
	{
		return;
		// don't have to implement yet
	}

	/**
	 * This method is called when the "Use Barcode Scanner" setting in the add
	 * item batch view is changed by the user.
	 */
	@Override
	public void useScannerChanged()
	{
		useBarcodeScanner = getView().getUseScanner();
		enableComponents();
	}
}
