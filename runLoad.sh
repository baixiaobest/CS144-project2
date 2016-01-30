ant run-all;

cat ItemTable | sort | uniq > ItemTable-unique;
cat ItemCategory | sort | uniq > ItemCategory-unique; #duplicates found
cat ItemBuyPrice | sort | uniq > ItemBuyPrice-unique;
cat ItemBids | sort | uniq > ItemBids-unique;
cat ItemPosition | sort | uniq > ItemPosition-unique;
cat SellerRating | sort | uniq > SellerRating-unique; #duplicates found
cat BidderRating | sort | uniq > BidderRating-unique; #duplicates found
cat UserLocation | sort | uniq > UserLocation-unique; #duplicates found

mysql CS144 < drop.sql;
mysql CS144 < create.sql;
mysql CS144 < load.sql;

rm *-unique;
rm Item* SellerRating BidderRating UserLocation;
ant clean;