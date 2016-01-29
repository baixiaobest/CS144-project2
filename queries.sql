SELECT count(*) 
FROM 
(
	SELECT UserID 
	FROM BidderRating 
	UNION 
	SELECT UserID 
	FROM SellerRating
) as uni;

SELECT count(*)
FROM ItemTable
WHERE BINARY Location="New York";


SELECT count(*)
FROM
(
	SELECT count(*) as count
	FROM ItemCategory
	GROUP BY ItemID
	HAVING count = 4
) as Candidates;


SELECT ItemTable.ItemID
FROM ItemBids
JOIN ItemTable
ON ItemTable.ItemID = ItemBids.ItemID
WHERE Ends > '2001-12-20 00:00:01'
ORDER BY Amount DESC
LIMIT 1;


SELECT count(*)
FROM SellerRating
WHERE Rating > 1000;

SELECT count(*)
FROM SellerRating, BidderRating
WHERE SellerRating.UserID = BidderRating.UserID;


SELECT count(distinct Category)
FROM ItemCategory
JOIN
(
	SELECT *
	FROM ItemBids
	WHERE Amount > 100
) as CandidateBids
ON ItemCategory.ItemID = CandidateBids.ItemID;
