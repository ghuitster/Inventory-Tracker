DROP TABLE IF EXISTS ProductContainer;
DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS ContainerProducts;

CREATE TABLE ProductContainer
(
	id INT NOT NULL PRIMARY KEY AUTOINCREMENT,
	Name TEXT NOT NULL,
	ThreeMonthSupply NUMBER NOT NULL,
	parentID INT NOT NULL,
	ThreeMonthSupplyType TEXT NOT NULL,
	CONSTRAINT ck_ThreeMonthSupplyType CHECK(ThreeMonthSupplyType IN(""))
);

CREATE TABLE Item
(
	id INT NOT NULL PRIMARY KEY AUTOINCREMENT,
	productID INT NOT NULL,
	Barcode BIGINT NOT NULL,
	EntryDate DATE NOT NULL,
	ExitTime DATETIME NOT NULL,
	ExpirationDate DATE NOT NULL,
	containerID INT NOT NULL 
);

CREATE TABLE Product
(
	id INT NOT NULL PRIMARY KEY AUTOINCREMENT,
	CreationDate DATE NOT NULL,
	Barcode TEXT NOT NULL,
	Description TEXT NOT NULL,
	Size NUMBER NOT NULL,
	ShelfLife INT NOT NULL,
	ThreeMonthSupply INT NOT NULL,
	SizeUnit TEXT NOT NULL,
	CONSTRAINT ck_SizeUnit CHECK(SizeUnit IN(""))
);

CREATE TABLE ContainerProducts
(
	containerID INT NOT NULL,
	productID INT NOT NULL
);