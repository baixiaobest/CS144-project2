CREATE TABLE ItemTable (
	ItemID			INT	NOT NULL,
	Name			VARCHAR(100) NOT NULL,
	Currently 		INT NOT NULL,
	First_Bid		INT	NOT NULL,
	Number_of_Bids	INT	NOT NULL,
	Location		VARCHAR(40) NOT NULL,
	Country			VARCHAR(40) NOT NULL,
	Started			TIMESTAMP NOT NULL,
	Ends			TIMESTAMP NOT NULL,
	SellerID		INT	NOT NULL,
	Description		VARCHAR(4000) NOT NULL,
	PRIMARY KEY (ItemID)
);

CREATE TABLE ItemCategory (
	ItemID			INT	NOT NULL,
	Category		VARCHAR(100) NOT NULL,
	PRIMARY KEY (ItemID)
);

CREATE TABLE ItemBuyPrice (
	ItemID			INT	NOT NULL,
	BuyPrice		INT NOT NULL,
	PRIMARY KEY (ItemID)
);

CREATE TABLE ItemBids (
	ItemID			INT	NOT NULL,
	BidderID		INT	NOT NULL,
	Time 			TIMESTAMP NOT NULL,
	Amount			INT NOT NULL,
	PRIMARY KEY (ItemID, BidderID, Time)
);

CREATE TABLE ItemPosition (
	ItemID			INT	NOT NULL,
	Latitude		REAL, -- could be NULL when longitude is provided
	Longitude		REAL, -- could be NULL when latitude is provided
	PRIMARY KEY (ItemID)
);

CREATE TABLE SellerRating (
	UserID			INT	NOT NULL,
	Rating			INT	NOT NULL,
	PRIMARY KEY (UserID)
);

CREATE TABLE BidderRating (
	UserID			INT	NOT NULL,
	Rating			INT	NOT NULL,
	PRIMARY KEY (UserID)
);

CREATE TABLE UserLocation (
	UserID			INT	NOT NULL,
	Location		VARCHAR(40) NOT NULL,
	PRIMARY KEY (UserID)
);

CREATE TABLE UserCountry (
	UserID			INT	NOT NULL,
	Country			VARCHAR(40) NOT NULL,
	PRIMARY KEY (UserID)
);