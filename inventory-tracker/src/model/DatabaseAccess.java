
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.exception.InvalidNameException;

public class DatabaseAccess
{
	public static boolean databaseExists(String name)
	{
		return false;
	}

	private final String databaseName;
	private Connection connection;

	private Statement statement;

	public DatabaseAccess(String databaseName)
	{
		this.databaseName = databaseName;
	}

	private void addChildGroups(IProductContainer parent,
			Map<Integer, IProduct> existingProducts) throws SQLException
	{
		String productGroupQuery =
				"SELECT id, Name, ThreeMonthSupply, parentID, ThreeMonthSupplyType "
						+ "FROM ProductContainer" + " WHERE parentID = "
						+ parent.getId();

		ResultSet productGroups =
				this.statement.executeQuery(productGroupQuery);

		List<IProductGroup> newGroups = new ArrayList<IProductGroup>();

		while(productGroups.next())
		{
			int id = productGroups.getInt("id");
			String name = productGroups.getString("Name");
			float threeMonthSupplyValue =
					productGroups.getFloat("ThreeMonthSupply");
			int threeMonthSupplyType =
					productGroups.getInt("ThreeMonthSupplyType");

			UnitType supplyType = UnitType.values()[threeMonthSupplyType];
			Amount threeMonthSupply;

			if(supplyType == UnitType.COUNT)
				threeMonthSupply =
						new CountThreeMonthSupply((int) threeMonthSupplyValue);
			else
				threeMonthSupply =
						new ThreeMonthSupply(threeMonthSupplyValue, supplyType);

			IProductGroup group = new ProductGroup(name, threeMonthSupply);
			group.setId(id);
			parent.addProductGroup(group);
			addProducts(group, existingProducts);
			addItems(group, existingProducts);

			newGroups.add(group);
		}

		for(IProductGroup group: newGroups)
		{
			addChildGroups(group, existingProducts);
		}
	}

	public void addItem(IItem item)
	{
		// I'm assuming that all the information about the item, its product and
		// product container have been set correctly before we get here...
		String firstPartOfQuery =
				"INSERT INTO Item (\"productID\", \"Barcode\", \"EntryDate\", ";
		String secondPartOfQuery =
				"VALUES (\"" + item.getProduct().getId() + "\", \""
						+ item.getBarcode().getNumber() + "\", \""
						+ item.getEntryDate().getTime() + "\", ";

		if(item.getExitTime() != null)
		{
			firstPartOfQuery += "\"ExitTime\", ";
			secondPartOfQuery += "\"" + item.getExitTime().getTime() + "\", ";
		}

		if(item.getExpirationDate() != null)
		{
			firstPartOfQuery += "\"ExpirationDate\", ";
			secondPartOfQuery +=
					"\"" + item.getExpirationDate().getTime() + "\", ";
		}

		firstPartOfQuery += "\"containerID\") ";
		secondPartOfQuery += "\"" + item.getContainer().getId() + "\")";

		try
		{
			this.createConnection();
			this.statement.executeUpdate(firstPartOfQuery + secondPartOfQuery);
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

		String query =
				"SELECT id FROM Item WHERE Barcode=\""
						+ item.getBarcode().getNumber() + "\"";

		try
		{
			this.createConnection();
			ResultSet results = this.statement.executeQuery(query);

			results.next();
			item.setID(results.getInt("id"));
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

	private void addItems(IProductContainer container,
			Map<Integer, IProduct> existingProducts) throws SQLException
	{
		String itemQuery =
				"SELECT id, productID, Barcode, EntryDate, ExitTime, ExpirationDate "
						+ "FROM Item " + "WHERE containerID = "
						+ container.getId();

		ResultSet items = this.statement.executeQuery(itemQuery);
		while(items.next())
		{
			Item item = makeItem(existingProducts, items);
			container.addItem(item);
		}

	}

	/**
	 * Adds a product to the database
	 * @param product The product to add
	 */
	public void addProduct(IProduct product)
	{
		double size = 0.0;

		if(product.getSize() instanceof CountAmount)
			size = ((CountAmount) product.getSize()).getAmount();
		else if(product.getSize() instanceof NonCountAmount)
			size = ((NonCountAmount) product.getSize()).getAmount();

		String query =
				"INSERT INTO Product (\"CreationDate\", \"Barcode\", \"Description\", \"Size\", "
						+ "\"ShelfLife\", \"ThreeMonthSupply\", \"SizeUnit\") VALUES (\""
						+ product.getCreationDate().getTime()
						+ "\", \""
						+ product.getBarcode().getNumber()
						+ "\", \""
						+ product.getDescription().getDescription()
						+ "\", \""
						+ size
						+ "\", \""
						+ product.getShelfLife()
						+ "\", \""
						+ product.getThreeMonthSupply().getAmount()
						+ "\", \""
						+ product.getSize().getUnitType().ordinal() + "\")";

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

		query =
				"SELECT id FROM Product WHERE Barcode=\""
						+ product.getBarcode().getNumber() + "\"";

		try
		{
			this.createConnection();
			ResultSet results = this.statement.executeQuery(query);

			results.next();
			product.setID(results.getInt("id"));
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

		int containerID = product.getContainers().iterator().next().getId();

		query =
				"INSERT INTO ContainerProducts (\"containerID\", \"productID\") VALUES (\""
						+ containerID + "\", \"" + product.getId() + "\")";

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
	 * Adds a product container to the database
	 * @param productContainer The product container to add
	 */
	public void addProductContainer(IProductContainer productContainer)
	{
		String query = "";

		if(productContainer instanceof ProductGroup)
		{
			double threeMonthSupply = -1.0;
			int parentID = -1;
			int threeMonthSupplyType = -1;

			if(((ProductGroup) productContainer).getThreeMonthSupply() instanceof CountAmount)
			{
				threeMonthSupplyType =
						((CountAmount) ((ProductGroup) productContainer)
								.getThreeMonthSupply()).getUnitType().ordinal();
				threeMonthSupply =
						((CountAmount) ((ProductGroup) productContainer)
								.getThreeMonthSupply()).getAmount();
			}
			else if(((ProductGroup) productContainer).getThreeMonthSupply() instanceof NonCountAmount)
			{
				threeMonthSupplyType =
						((NonCountAmount) ((ProductGroup) productContainer)
								.getThreeMonthSupply()).getUnitType().ordinal();
				threeMonthSupply =
						((NonCountAmount) ((ProductGroup) productContainer)
								.getThreeMonthSupply()).getAmount();
			}

			parentID = ((ProductGroup) productContainer).getContainer().getId();

			query =
					"INSERT INTO ProductContainer (\"Name\", \"ThreeMonthSupply\", \"parentID\", \"ThreeMonthSupplyType\") VALUES (\""
							+ productContainer.getName()
							+ "\", \""
							+ threeMonthSupply
							+ "\", \""
							+ parentID
							+ "\", \""
							+ threeMonthSupplyType + "\")";
		}

		else
		{
			query =
					"INSERT INTO ProductContainer (\"Name\") VALUES (\""
							+ productContainer.getName() + "\")";
		}

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

		query =
				"SELECT id FROM ProductContainer WHERE Name=\""
						+ productContainer.getName() + "\"";

		try
		{
			this.createConnection();
			ResultSet results = this.statement.executeQuery(query);

			results.next();
			productContainer.setId(results.getInt("id"));
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

	private void addProducts(IProductContainer container,
			Map<Integer, IProduct> existingProducts) throws SQLException
	{
		String productQuery =
				"SELECT Product.id, Product.CreationDate, Product.Barcode, Product.Description, "
						+ "Product.Size, Product.ShelfLife, Product.ThreeMonthSupply, Product.SizeUnit "
						+ "FROM Product "
						+ "INNER JOIN ContainerProducts "
						+ "ON ContainerProducts.productID = Product.id "
						+ "WHERE ContainerProducts.containerID = "
						+ container.getId();

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

	private void addRemovedItems(Map<Integer, IProduct> existingProducts)
			throws SQLException
	{
		String itemQuery =
				"SELECT id, productID, Barcode, EntryDate, ExitTime, ExpirationDate "
						+ "FROM Item " + "WHERE containerID IS NULL";

		ResultSet items = this.statement.executeQuery(itemQuery);
		while(items.next())
		{
			Item item = makeItem(existingProducts, items);
			Inventory.getInstance().reportRemovedItem(item);
		}
	}

	private void addRemovedProducts(Map<Integer, IProduct> existingProducts)
			throws SQLException
	{
		String productQuery =
				"SELECT Product.id, Product.CreationDate, Product.Barcode, Product.Description, "
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
	 * This method will create a connection with the database
	 * @throws SQLException If something bad happens when attempting to create
	 *             the statements to be executed
	 * @throws ClassNotFoundException If something bad happens when attempting
	 *             to load the SQLite driver
	 */
	private void createConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		new File("." + File.separator + "database" + File.separator).mkdir();
		String path =
				"jdbc:sqlite:database" + File.separator + databaseName
						+ ".sqlite";
		this.connection = DriverManager.getConnection(path);
		this.statement = this.connection.createStatement();
	}

	/**
	 * Creates the database using SQL statements from a txt file
	 * @throws FileNotFoundException Should never happen
	 * @throws SQLException from addSqlBatch from DataAccess
	 */
	private void createDatabase()
	{
		try
		{
			Scanner scan = new Scanner(new File("db_schema.sql"));
			this.createConnection();

			while(scan.hasNext())
				this.statement.executeUpdate(scan.nextLine());

			scan.close();
		}
		catch(FileNotFoundException | ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Gets the complete Product Container tree from the database. This will go
	 * get EVERYTHING from the database and build the in memory representation
	 * @pre The Inventory instance is empty
	 * @post Inventory has been populated with all information from the database
	 */
	public void loadInventory()
	{
		this.createDatabase();

		IInventory inventory = Inventory.getInstance();

		String storageUnitsQuery =
				"SELECT id, Name FROM ProductContainer "
						+ "WHERE parentID IS NULL;";

		try
		{
			this.createConnection();
			ResultSet storageUnits =
					this.statement.executeQuery(storageUnitsQuery);

			Map<Integer, IProduct> existingProducts =
					new HashMap<Integer, IProduct>();

			while(storageUnits.next())
			{
				int id = storageUnits.getInt("id");
				String name = storageUnits.getString("Name");
				try
				{
					IStorageUnit unit = new StorageUnit(name);
					unit.setId(id);
					inventory.addStorageUnit(unit);

				}
				catch(InvalidNameException e)
				{}
			}

			for(IStorageUnit unit: inventory.getAllStorageUnits())
			{
				addChildGroups(unit, existingProducts);

				addProducts(unit, existingProducts);
				addItems(unit, existingProducts);
			}

			addRemovedProducts(existingProducts);
			addRemovedItems(existingProducts);

			long largestBarcode = 400000000000l;
			String largestBarcodeQuery =
					"SELECT Barcode FROM Item " + "ORDER BY id DESC "
							+ "LIMIT 1";
			ResultSet largestBarcodeSet =
					this.statement.executeQuery(largestBarcodeQuery);
			while(largestBarcodeSet.next())
				largestBarcode = largestBarcodeSet.getLong("Barcode");
			inventory.setLastGeneratedBarCode(new ItemBarcode(""
					+ largestBarcode));

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
		Item item =
				new Item(product, new ItemBarcode("" + barcode), entry,
						expiration);
		item.setExitTime(exit);
		return item;
	}

	private Product makeProduct(ResultSet products) throws SQLException
	{
		int id = products.getInt("Product.id");
		Barcode barcode =
				new ProductBarcode(products.getString("Product.Barcode"));
		Date creation = new Date(products.getLong("Product.CreationDate"));
		String description = products.getString("Product.Description");

		UnitType sizeUnit =
				UnitType.values()[products.getInt("Product.SizeUnit")];
		Amount size;
		if(sizeUnit == UnitType.COUNT)
			size = new CountUnitSize((int) products.getFloat("Product.Size"));
		else
			size = new UnitSize(products.getFloat("Product.Size"), sizeUnit);

		int shelfLife = products.getInt("ShelfLife");
		if(products.wasNull())
			shelfLife = 0;
		CountThreeMonthSupply supply =
				new CountThreeMonthSupply(
						products.getInt("Product.ThreeMonthSupply"));

		Product product =
				new Product(creation, description, barcode, size, shelfLife,
						supply);
		product.setID(id);
		return product;
	}

	/**
	 * Removes an item by id from the database
	 * @param item The item to remove
	 */
	public void removeItem(IItem item)
	{
		String query = "DELETE FROM Item WHERE id=\"" + item.getId() + "\"";

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
	 * Removes a product by id from the database
	 * @param product The product to remove
	 */
	public void removeProduct(IProduct product)
	{
		String query =
				"DELETE FROM Product WHERE id=\"" + product.getId() + "\"";

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
	 * Removes a product container by id from the database
	 * @param container The container to remove
	 */
	public void removeProductContainer(IProductContainer container)
	{
		String query =
				"DELETE FROM ProductContainer WHERE id=\"" + container.getId()
						+ "\"";

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
	 * Updates an item in the database
	 */
	public void updateItem(IItem item)
	{
		String query =
				"UPDATE Item SET \"productID\"=\"" + item.getProduct().getId()
						+ "\", \"Barcode\"=\"" + item.getBarcode().getNumber()
						+ "\", \"EntryDate\"=\""
						+ item.getEntryDate().getTime() + "\", ";

		if(item.getExitTime() != null)
			query += "\"ExitTime\"=\"" + item.getExitTime().getTime() + "\", ";

		if(item.getExpirationDate() != null)
			query +=
					"\"ExpirationDate\"=\""
							+ item.getExpirationDate().getTime() + "\", ";

		query += "\"containerID\"=\"" + item.getContainer().getId() + "\"";

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
	 * Updates a product in the database
	 * @param product
	 */
	public void updateProduct(IProduct product)
	{
		double size = 0.0;

		if(product.getSize() instanceof CountAmount)
			size = ((CountAmount) product.getSize()).getAmount();
		else if(product.getSize() instanceof NonCountAmount)
			size = ((NonCountAmount) product.getSize()).getAmount();

		String query =
				"UPDATE Product SET \"CreationDate\"=\""
						+ product.getCreationDate().getTime()
						+ "\", \"Barcode\"=\""
						+ product.getBarcode().getNumber()
						+ "\", \"Description\"=\""
						+ product.getDescription().getDescription()
						+ "\", \"Size\"=\"" + size + "\", "
						+ "\"ShelfLife\"=\"" + product.getShelfLife()
						+ "\", \"ThreeMonthSupply\"=\""
						+ product.getThreeMonthSupply().getAmount()
						+ "\", \"SizeUnit\"=\""
						+ product.getSize().getUnitType().ordinal() + "\"";

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
	 * Updates a product container in the database
	 * @param productContainer
	 */
	public void updateProductContainer(IProductContainer productContainer)
	{
		String query = "";
		if(productContainer instanceof ProductGroup)
		{
			double threeMonthSupply = -1.0;
			int parentID = -1;
			int threeMonthSupplyType = -1;

			if(((ProductGroup) productContainer).getThreeMonthSupply() instanceof CountAmount)
			{
				threeMonthSupplyType =
						((CountAmount) ((ProductGroup) productContainer)
								.getThreeMonthSupply()).getUnitType().ordinal();
				threeMonthSupply =
						((CountAmount) ((ProductGroup) productContainer)
								.getThreeMonthSupply()).getAmount();
			}
			else if(((ProductGroup) productContainer).getThreeMonthSupply() instanceof NonCountAmount)
			{
				threeMonthSupplyType =
						((NonCountAmount) ((ProductGroup) productContainer)
								.getThreeMonthSupply()).getUnitType().ordinal();
				threeMonthSupply =
						((NonCountAmount) ((ProductGroup) productContainer)
								.getThreeMonthSupply()).getAmount();
			}

			parentID = ((ProductGroup) productContainer).getContainer().getId();

			query =
					"UPDATE ProductContainer SET \"Name\"=\""
							+ productContainer.getName()
							+ "\", \"ThreeMonthSupply\"=\"" + threeMonthSupply
							+ "\", \"parentID\"=\"" + parentID
							+ "\", \"ThreeMonthSupplyType\"=\""
							+ threeMonthSupplyType + "\"";
		}

		else
		{
			query =
					"UPDATE ProductContainer SET \"Name\"=\""
							+ productContainer.getName() + "\"";
		}

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
}
