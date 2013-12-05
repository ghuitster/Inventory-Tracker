
package model;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.exception.InvalidNameException;

public class DatabaseAccess
{
	private final String databaseName;
	private Connection connection;
	private Statement statement;

	public static boolean databaseExists(String name)
	{
		return false;
	}

	public DatabaseAccess(String databaseName)
	{
		this.databaseName = databaseName;
	}

	/**
	 * Adds an item to the database
	 * @param item The item to add
	 */
	public void addItem(IItem item)
	{
		// I'm assuming that all the information about the item, its product and
		// product container have been set correctly before we get here...
		String query =
				"INSERT INTO Item (\"productID\", \"Barcode\", \"EntryDate\", \"ExitTime\", "
						+ "\"ExpirationDate\", \"containerID\") VALUES (\""
						+ item.getProduct().getId() + "\", \""
						+ item.getBarcode().getNumber() + "\", \""
						+ item.getEntryDate().getTime() + "\", \""
						+ item.getExitTime().getTime() + "\", \""
						+ item.getExpirationDate().getTime() + "\", \""
						+ item.getContainer().getId() + "\")";

		try
		{
			this.createConnection();
			this.statement.executeUpdate(query);
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				this.closeConnection();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

	}

	/**
	 * Adds a product to the database
	 * @param product The product to add
	 */
	public void addProduct(IProduct product)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Adds a product container to the database
	 * @param productContainer The product container to add
	 */
	public void addProductContainer(IProductContainer productContainer)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets the complete Product Container tree from the database. This will go
	 * get EVERYTHING from the database and build the in memory representation
	 */
	public void loadInventory()
	{
		IInventory inventory = Inventory.getInstance();
		
		String storageUnitsQuery = 
				"SELECT id, Name FROM ProductContainer "
				+ "WHERE parentID IS NULL;";
		
		try
		{
			this.createConnection();
			ResultSet storageUnits = this.statement.executeQuery(storageUnitsQuery);
			
			Map<Integer, IProduct> existingProducts = new HashMap<Integer, IProduct>();
			
			while(storageUnits.next())
			{
				int id = storageUnits.getInt("id");
				String name = storageUnits.getString("Name");
				try
				{
					IStorageUnit unit = new StorageUnit(name);
					unit.setId(id);
					inventory.addStorageUnit(unit);
					
					addChildGroups(unit, existingProducts);
					
					addProducts(unit, existingProducts);
					addItems(unit, existingProducts);
					
					addRemovedProducts(existingProducts);
					addRemovedItems(existingProducts);
					
				} catch (InvalidNameException e) { }
			}
			
			long largestBarcode = 400000000000l;
			String largestBarcodeQuery =
					"SELECT Barcode FROM Items "
					+ "ORDER BY id DESC"
					+ "LIMIT 1";
			ResultSet largestBarcodeSet = this.statement.executeQuery(largestBarcodeQuery);
			while(largestBarcodeSet.next())
				largestBarcode = largestBarcodeSet.getLong("Barcode");
			inventory.setLastGeneratedBarCode(new ItemBarcode("" + largestBarcode));
			
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				this.closeConnection();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void addRemovedItems(Map<Integer, IProduct> existingProducts) throws SQLException
	{
		String itemQuery = 
				"SELECT id, productID, Barcode, EntryDate, ExitTime, ExpirationDate "
				+ "FROM Item "
				+ "WHERE containerID IS NULL";
		
		ResultSet items = this.statement.executeQuery(itemQuery);
		while(items.next())
		{
			Item item = makeItem(existingProducts, items);
			Inventory.getInstance().reportRemovedItem(item);
		}
	}

	private void addRemovedProducts(Map<Integer, IProduct> existingProducts) throws SQLException
	{
		String productQuery =
				"SELECT Product.id Product.CreationDate, Product.Barcode, Product.Description, "
				+ "Product.Size, Product.ShelfLife, Product.ThreeMonthSupply, Product.SizeUnit "
				+ "FROM Product "
				+ "LEFT JOIN ContainerProducts "
				+ "ON ContainerProducts.productID = Product.id "
				+ "WHERE ContainerProducts.containerID IS NULL";
		ResultSet products = this.statement.executeQuery(productQuery);
		while(products.next())
		{
			Product product = makeProduct(products);
			Inventory.getInstance().reportRemovedProduct(product);
		}
	}

	private void addItems(IProductContainer container, Map<Integer, IProduct> existingProducts) throws SQLException
	{
		String itemQuery = 
				"SELECT id, productID, Barcode, EntryDate, ExitTime, ExpirationDate "
				+ "FROM Item "
				+ "WHERE containerID = " + container.getId();
		
		ResultSet items = this.statement.executeQuery(itemQuery);
		while(items.next())
		{
			Item item = makeItem(existingProducts, items);
			container.addItem(item);
		}
		
	}

	private Item makeItem(Map<Integer, IProduct> existingProducts,
			ResultSet items) throws SQLException
	{
		int id = items.getInt("id");
		int productId = items.getInt("productID");
		long barcode = items.getLong("Barcode");
		Date entry = new Date(items.getLong("EntryDate"));
		long exitSeconds = items.getLong("ExitTime");
		Date exit = null;
		if(!items.wasNull())
			exit = new Date(exitSeconds);
		long expirationSeconds = items.getLong("ExpirationDate");
		Date expiration = null;
		if(!items.wasNull())
			expiration = new Date(expirationSeconds);
		
		IProduct product = existingProducts.get(productId);
		Item item = new Item(product, new ItemBarcode("" + barcode), 
				entry, expiration);
		return item;
	}

	private void addProducts(IProductContainer container, Map<Integer, IProduct> existingProducts) throws SQLException
	{
		String productQuery =
				"SELECT Product.id Product.CreationDate, Product.Barcode, Product.Description, "
				+ "Product.Size, Product.ShelfLife, Product.ThreeMonthSupply, Product.SizeUnit "
				+ "FROM Product "
				+ "INNER JOIN ContainerProducts "
				+ "ON ContainerProducts.productID = Product.id "
				+ "WHERE ContainerProducts.containerID = " + container.getId();
		
		ResultSet products = this.statement.executeQuery(productQuery);
		while(products.next())
		{
			int id = products.getInt("Product.id");
			if(existingProducts.containsKey(id))
			{
				container.addProduct(existingProducts.get(id));
			}
			else
			{
				Product product = makeProduct(products);
				container.addProduct(product);
				existingProducts.put(product.getID(), product);
			}
		}
		
	}

	private Product makeProduct(ResultSet products)
			throws SQLException
	{
		int id = products.getInt("Product.id");
		Barcode barcode = new ProductBarcode(products.getString("Product.Barcode"));
		Date creation = new Date(products.getLong("Product.CreationDate"));
		String description = products.getString("Product.Description");
		
		UnitType sizeUnit = UnitType.values()[products.getInt("Product.SizeUnit")];
		Amount size;
		if(sizeUnit == UnitType.COUNT)
			size = new CountUnitSize((int)products.getFloat("Product.Size"));
		else size = new UnitSize(products.getFloat("Product.Size"), sizeUnit);
		
		int shelfLife = products.getInt("ShelfLife");
		if(products.wasNull())
			shelfLife = 0;
		CountThreeMonthSupply supply = new CountThreeMonthSupply
				(products.getInt("Product.ThreeMonthSupply"));
		
		Product product = new Product(creation, description, barcode, size, shelfLife, supply);
		product.setID(id);
		return product;
	}

	private void addChildGroups(IProductContainer parent, Map<Integer, IProduct> existingProducts) throws SQLException
	{
		String productGroupQuery = 
				"SELECT id, Name, ThreeMonthSupply, parentID, ThreeMonthSupplyType "
				+ "FROM ProductContainer"
				+ "WHERE parentID = " + parent.getId();
		
		ResultSet productGroups = this.statement.executeQuery(productGroupQuery);
		while(productGroups.next())
		{
			int id = productGroups.getInt("id");
			String name = productGroups.getString("Name");
			float threeMonthSupplyValue = productGroups.getFloat("ThreeMonthSupply");
			int threeMonthSupplyType = productGroups.getInt("ThreeMonthSupplyType");
			
			UnitType supplyType = UnitType.values()[threeMonthSupplyType];
			Amount threeMonthSupply;
			
			if(supplyType == UnitType.COUNT)
				threeMonthSupply = new CountThreeMonthSupply((int)threeMonthSupplyValue);
			else
				threeMonthSupply = new ThreeMonthSupply(threeMonthSupplyValue, supplyType);
			
			IProductGroup group = new ProductGroup(name, threeMonthSupply);
			group.setId(id);
			parent.addProductGroup(group);
			addProducts(group, existingProducts);
			addItems(group, existingProducts);
			
			addChildGroups(group, existingProducts);
		}
	}

	/**
	 * This method will create a connection with the database
	 * @throws SQLException If something bad happens when attempting to create
	 *             the statements to be executed
	 * @throws ClassNotFoundException If something bad happens when attempting
	 *             to load the SQLite driver
	 */
	private void createConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		String path = "jdbc:sqlite:database" + File.separator + databaseName;
		this.connection = DriverManager.getConnection(path);
		this.statement = this.connection.createStatement();
	}

	/**
	 * This method will close the connection with the database
	 * @throws SQLException If something bad happens when trying to close the
	 *             connection
	 */
	private void closeConnection() throws SQLException
	{
		this.statement.close();
		this.connection.close();
	}

	/**
	 * Removes an item by id from the database
	 * @param item The item to remove
	 */
	public void removeItem(IItem item)
	{

	}

	/**
	 * Removes a product by id from the database
	 * @param product The product to remove
	 */
	public void removeProduct(IProduct product)
	{

	}

	/**
	 * Removes a product container by id from the database
	 * @param container The container to remove
	 */
	public void removeProductContainer(IProductContainer container)
	{

	}

	/**
	 * Updates an item in the database
	 */
	public void updateItem(IItem item)
	{

	}

	/**
	 * Updates a product container in the database
	 * @param productContainer
	 */
	public void updateProductContainer(IProductContainer productContainer)
	{

	}

	/**
	 * Updates a product in the database
	 * @param product
	 */
	public void updateProduct(IProduct product)
	{

	}
}
