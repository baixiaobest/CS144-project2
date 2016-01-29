All relations:
ItemTable(ItemID, Name, Currently, First_Bid, Number_of_Bids, Location, Country, Started, Ends, SellerID, Description)
key:(ItemID)

ItemCategory(ItemID, Category) //ItemID can map to multiple Categories
key:(ItemID, Category)

ItemBuyPrice(ItemID, Buy_Price)
key:(ItemID)

ItemBids(ItemID, BidderID, Time, Amount) //ItemID can map to multiple Bidders
key:(ItemID, BidderID, Time)

ItemPosition(ItemID, Latitude, Longitude)
key:(ItemID)

SellerRating(UserID, Rating)
key:(UserID)

BidderRating(UserID, Rating)
key:(UserID)

UserLocation(UserID, Location, Country)
key:(UserID)



Functional Dependencies:
ItemID->Name, Currently, First_Bid, Number_of_Bids, Location, Country, Started, Ends, SellerID, Description

ItemID->Buy_Price

ItemID->Latitude, Longitude

UserID->Rating

UserID->Location

UserID->Country

