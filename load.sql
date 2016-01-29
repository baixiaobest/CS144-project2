load data local infile 'ItemTable-unique' into table ItemTable
fields terminated by '\t';

load data local infile 'ItemCategory-unique' into table ItemCategory
fields terminated by '\t';

load data local infile 'ItemBuyPrice-unique' into table ItemBuyPrice
fields terminated by '\t';

load data local infile 'ItemBids-unique' into table ItemBids
fields terminated by '\t';

load data local infile 'ItemPosition-unique' into table ItemPosition
fields terminated by '\t';

load data local infile 'SellerRating-unique' into table SellerRating
fields terminated by '\t';

load data local infile 'BidderRating-unique' into table BidderRating
fields terminated by '\t';

load data local infile 'UserLocation-unique' into table UserLocation
fields terminated by '\t';
