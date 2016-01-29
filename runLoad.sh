ant run-all;

cat Itemtable | uniq > ItemTable-unique;
cat ItemCategory | uniq > ItemCategory-unique; #duplicates found
cat ItemBuyPrice | uniq > ItemBuyPrice-unique;
cat ItemBids | uniq > ItemBids-unique;
cat ItemPosition | uniq > ItemPosition-unique;
cat SellerRating | uniq > SellerRating-unique; #duplicates found
cat BidderRating | uniq > BidderRating-unique; #duplicates found
cat UserLocation | uniq > UserLocation-unique; #duplicates found

mysql CS144 < drop.sql;
mysql CS144 < create.sql;
mysql CS144 < load.sql;

