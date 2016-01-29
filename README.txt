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



Excluding those that effectively specify keys, there is no completely nontrivial functional dependencies that hold on each relation.

********
Shall we count
Something like ItemID, Name -> rest ? It is compeletely nontrivial but redundant.

All relations are in BCNF and 4NF.
