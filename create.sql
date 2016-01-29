create table ItemTable(
	ItemID int not null,
	Name varchar(200),
	Currently DECIMAL(8,2),
	First_Bid DECIMAL(8,2),
	Number_of_Bids int,
	Location varchar(200),
	Country varchar(200),
	Started TIMESTAMP,
	Ends TIMESTAMP,
	SellerID varchar(200),
	Description varchar(4000),
	primary key(ItemID)
)ENGINE=INNODB;

create table ItemCategory(
	ItemID int not null,
	Category varchar(200)
)ENGINE=INNODB;

create table ItemBuyPrice(
	ItemID int not null,
	Buy_Price DECIMAL(8,2),
	primary key(ItemID)
)ENGINE=INNODB;

create table ItemBids(
	ItemID int not null,
	BidderID varchar(200),
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
	UserID varchar(200) not null,
	Rating int,
	primary key(UserID)
)ENGINE=INNODB;

create table BidderRating(
	UserID varchar(200) not null,
	Rating int,
	primary key(UserID)
)ENGINE=INNODB;

create table UserLocation(
	UserID varchar(200) not null,
	Location varchar(200),
	Country varchar(200),
	primary key(UserID)
)ENGINE=INNODB;