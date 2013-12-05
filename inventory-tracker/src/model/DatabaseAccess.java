
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
			
			Map<String, IProduct> existingProducts = new HashMap<String, IProduct>();
			
			while(storageUnits.next())
			{
				int id = storageUnits.getInt("id");
				String name = storageUnits.getString("Name");
				try
				{
					IStorageUnit unit = new StorageUnit(id, name);
					inventory.addStorageUnit(unit);
					
					addChildGroups(unit);
					
					addProducts(unit, existingProducts);
					
				} catch (InvalidNameException e) { }
			}
			
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

	private void addProducts(IProductContainer container, Map<String, IProduct> existingProducts) throws SQLException
	{
		String productQuery =
				"SELECT Product.CreationDate, Product.Barcode, Product.Description, Product.Size, "
				+ "Product.ShelfLife, Product.ThreeMonthSupply, Product.SizeUnit "
				+ "FROM Product "
				+ "INNER JOIN ContainerProducts "
				+ "ON ContainerProducts.productID = Product.id "
				+ "WHERE ContainerProducts.containerID = " + container.getId();
		
		ResultSet products = this.statement.executeQuery(productQuery);
		while(products.next())
		{
			String barcode = products.getString("Product.Barcode");
			if(existingProducts.containsKey(barcode))
			{
				container.addProduct(existingProducts.get(barcode));
			}
			else
			{
				Date creation = new Date(products.getLong("Product.CreationDate"));
				String description = products.getString("Product.Description");
				
				UnitType sizeUnit = UnitType.values()[products.getInt("Product.SizeUnit")];
				Amount size;
				if(sizeUnit == UnitType.COUNT)
					size = new CountUnitSize((int)products.getFloat("Product.Size"));
				else size = new UnitSize(products.getFloat("Product.Size"), sizeUnit);
				
				int shelfLife = products.getInt("ShelfLife");
				CountThreeMonthSupply supply = new CountThreeMonthSupply
						(products.getInt("Product.ThreeMonthSupply"));
				
				Product product = new Product(creation, description, 
						new ProductBarcode(barcode), size, shelfLife, supply);
				container.addProduct(product);
				existingProducts.put(product.getBarcode().getNumber(), product);
			}
		}
		
	}

	private void addChildGroups(IProductContainer parent) throws SQLException
	{
		String productGroupQuery = 
				"SELECT id, Name, ThreeMonthSupply, parentID, ThreeMonthSupplyType "
				+ "FROM ProductContainer"
				+ "WHERE parentID = " + parent.getId();
		
		ResultSet productGroups = this.statement.executeQuery(productGroupQuery);
		while(productGroups.next())
		{
			int _id = productGroups.getInt("id");
			String _name = productGroups.getString("Name");
			float threeMonthSupplyValue = productGroups.getFloat("ThreeMonthSupply");
			int threeMonthSupplyType = productGroups.getInt("ThreeMonthSupplyType");
			
			UnitType supplyType = UnitType.values()[threeMonthSupplyType];
			Amount threeMonthSupply;
			
			if(supplyType == UnitType.COUNT)
				threeMonthSupply = new CountThreeMonthSupply((int)threeMonthSupplyValue);
			else
				threeMonthSupply = new ThreeMonthSupply(threeMonthSupplyValue, supplyType);
			
			IProductGroup group = new ProductGroup(_id, _name, threeMonthSupply);
			parent.addProductGroup(group);
			
			addChildGroups(group);
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
