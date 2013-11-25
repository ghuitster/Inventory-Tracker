DROP TABLE IF EXISTS ProductContainer;
DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS ContainerProducts;

CREATE TABLE ProductContainer
(
	id INT NOT NULL PRIMARY KEY AUTOINCREMENT,
	Name TEXT NOT NULL,
	ThreeMonthSupply REAL,
	parentID INT NOT NULL,
	ThreeMonthSupplyType INT
);

CREATE TABLE Item
(
	id INT NOT NULL PRIMARY KEY AUTOINCREMENT,
	productID INT NOT NULL,
	Barcode INT NOT NULL,
	EntryDate INT NOT NULL,
	ExitTime INT,
	ExpirationDate INT,
	containerID INT NOT NULL 
);

CREATE TABLE Product
(
	id INT NOT NULL PRIMARY KEY AUTOINCREMENT,
	CreationDate INT NOT NULL,
	Barcode TEXT NOT NULL,
	Description TEXT NOT NULL,
	Size REAL NOT NULL,
	ShelfLife INT,
	ThreeMonthSupply INT,
	SizeUnit INT NOT NULL
);

CREATE TABLE ContainerProducts
(
	containerID INT NOT NULL,
	productID INT NOT NULL
);