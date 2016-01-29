cat Itemtable | uniq > ItemTable-unique;
cat ItemCategory | uniq > ItemCategory-unique; #duplicates found
cat ItemBuyPrice | uniq > ItemBuyPrice-unique;
cat ItemBids | uniq > ItemBids-unique;
cat ItemPosition | uniq > ItemPositon-unique;
cat SellerRating | uniq > SellerRating-unique; #duplicates found
cat BidderRating | uniq > BidderRating-unique; #duplicates found
cat UserLocation | uniq > UserLocation-unique; #duplicates found