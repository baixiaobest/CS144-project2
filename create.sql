create table ItemTable(
	ItemID int not null,
	Name varchar(100),
	Currently DECIMAL(8,2),
	First_Bid DECIMAL(8,2),
	Number_of_Bids int,
	Location varchar(50),
	Country varchar(50),
	Started TIMESTAMP,
	Ends TIMESTAMP,
	SellerID int,
	Description varchar(4000),
	primary key(ItemID)
)ENGINE=INNODB;

create table ItemCategory(
	ItemID int not null,
	Category varchar(50)
)ENGINE=INNODB;

create table ItemBuyPrice(
	ItemID int not null,
	Buy_Price DECIMAL(8,2),
	primary key(ItemID)
)ENGINE=INNODB;

create table ItemBids(
	ItemID int not null,
	BidderID int,
	Time TIMESTAMP,
	Amount DECIMAL(8,2),
	primary key(ItemID,BidderID, Time)
)ENGINE=INNODB;

create table ItemPosition(
	ItemID int not null,
	Latitude varchar(50),
	Longitude varchar(50),
	primary key(ItemID)
)ENGINE=INNODB;

create table SellerRating(
	UserID int not null,
	Rating int,
	primary key(UserID)
)ENGINE=INNODB;

create table BidderRating(
	UserID int not null,
	Rating int,
	primary key(UserID)
)ENGINE=INNODB;

create table UserLocation(
	UserID int not null,
	Location varchar(50),
	Country varchar(50),
	primary key(UserID)
)ENGINE=INNODB;

