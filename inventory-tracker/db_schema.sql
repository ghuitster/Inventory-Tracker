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
	ThreeMonthSupplyType TEXT,
	CONSTRAINT ck_ThreeMonthSupplyType CHECK(ThreeMonthSupplyType IN(""))
);

CREATE TABLE Item
(
	id INT NOT NULL PRIMARY KEY AUTOINCREMENT,
	productID INT NOT NULL,
	Barcode REAL NOT NULL,
	EntryTEXT TEXT NOT NULL,
	ExitTime TEXT,
	ExpirationDate TEXT,
	containerID INT NOT NULL 
);

CREATE TABLE Product
(
	id INT NOT NULL PRIMARY KEY AUTOINCREMENT,
	CreationTEXT TEXT NOT NULL,
	Barcode TEXT NOT NULL,
	Description TEXT NOT NULL,
	Size REAL NOT NULL,
	ShelfLife INT,
	ThreeMonthSupply INT,
	SizeUnit TEXT NOT NULL,
	CONSTRAINT ck_SizeUnit CHECK(SizeUnit IN(""))
);

CREATE TABLE ContainerProducts
(
	containerID INT NOT NULL,
	productID INT NOT NULL
);