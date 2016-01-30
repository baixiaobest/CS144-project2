/* CS144
 *
 * Parser skeleton for processing item-???.xml files. Must be compiled in
 * JDK 1.5 or above.
 *
 * Instructions:
 *
 * This program processes all files passed on the command line (to parse
 * an entire diectory, type "java MyParser myFiles/*.xml" at the shell).
 *
 * At the point noted below, an individual XML file has been parsed into a
 * DOM Document node. You should fill in code to process the node. Java's
 * interface for the Document Object Model (DOM) is in package
 * org.w3c.dom. The documentation is available online at
 *
 * http://java.sun.com/j2se/1.5.0/docs/api/index.html
 *
 * A tutorial of Java's XML Parsing can be found at:
 *
 * http://java.sun.com/webservices/jaxp/
 *
 * Some auxiliary methods have been written for you. You may find them
 * useful.
 */

package edu.ucla.cs.cs144;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;


class MyParser {
    
    static final String columnSeparator = "|*|";
    static DocumentBuilder builder;
    
    static final String[] typeName = {
	"none",
	"Element",
	"Attr",
	"Text",
	"CDATA",
	"EntityRef",
	"Entity",
	"ProcInstr",
	"Comment",
	"Document",
	"DocType",
	"DocFragment",
	"Notation",
    };
    
    static PrintWriter itemTable;
    static PrintWriter itemCategory;
    static PrintWriter itemBuyPrice;
    static PrintWriter itemBids;
    static PrintWriter itemPosition;
    static PrintWriter sellerRating;
    static PrintWriter bidderRating;
    static PrintWriter userLocation;
    
    static class MyErrorHandler implements ErrorHandler {
        
        public void warning(SAXParseException exception)
        throws SAXException {
            fatalError(exception);
        }
        
        public void error(SAXParseException exception)
        throws SAXException {
            fatalError(exception);
        }
        
        public void fatalError(SAXParseException exception)
        throws SAXException {
            exception.printStackTrace();
            System.out.println("There should be no errors " +
                               "in the supplied XML files.");
            System.exit(3);
        }
        
    }
    
    /* Non-recursive (NR) version of Node.getElementsByTagName(...)
     */
    static Element[] getElementsByTagNameNR(Element e, String tagName) {
        Vector< Element > elements = new Vector< Element >();
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
            {
                elements.add( (Element)child );
            }
            child = child.getNextSibling();
        }
        Element[] result = new Element[elements.size()];
        elements.copyInto(result);
        return result;
    }
    
    /* Returns the first subelement of e matching the given tagName, or
     * null if one does not exist. NR means Non-Recursive.
     */
    static Element getElementByTagNameNR(Element e, String tagName) {
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
                return (Element) child;
            child = child.getNextSibling();
        }
        return null;
    }
    
    /* Returns the text associated with the given element (which must have
     * type #PCDATA) as child, or "" if it contains no text.
     */
    static String getElementText(Element e) {
        if (e.getChildNodes().getLength() == 1) {
            Text elementText = (Text) e.getFirstChild();
            return elementText.getNodeValue();
        }
        else
            return "";
    }
    
    /* Returns the text (#PCDATA) associated with the first subelement X
     * of e with the given tagName. If no such X exists or X contains no
     * text, "" is returned. NR means Non-Recursive.
     */
    static String getElementTextByTagNameNR(Element e, String tagName) {
        Element elem = getElementByTagNameNR(e, tagName);
        if (elem != null)
            return getElementText(elem);
        else
            return "";
    }
    
    /* Returns the amount (in XXXXX.xx format) denoted by a money-string
     * like $3,453.23. Returns the input if the input is an empty string.
     */
    static String strip(String money) {
        if (money.equals(""))
            return money;
        else {
            double am = 0.0;
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            try { am = nf.parse(money).doubleValue(); }
            catch (ParseException e) {
                System.out.println("This method should work for all " +
                                   "money values you find in our data.");
                System.exit(20);
            }
            nf.setGroupingUsed(false);
            return nf.format(am).substring(1);
        }
    }
    
    /* Process one items-???.xml file.
     */
    static void processFile(File xmlFile){
        Document doc = null;
        try {
            doc = builder.parse(xmlFile);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
        catch (SAXException e) {
            System.out.println("Parsing error on file " + xmlFile);
            System.out.println("  (not supposed to happen with supplied XML files)");
            e.printStackTrace();
            System.exit(3);
        }
        
        /* At this point 'doc' contains a DOM representation of an 'Items' XML
         * file. Use doc.getDocumentElement() to get the root Element. */
        
        
        /* Fill in code here (you will probably need to write auxiliary
            methods). */
        
        //search for <Items>
        Node n = doc;
        while(n.getNodeName()!="Items"){
            org.w3c.dom.NodeList nlist = n.getChildNodes();
            n = nlist.item(0);
        }
        
        ScanItems(n);
        System.out.println("Successfully parsed - " + xmlFile);
    }
    
    public static void ScanItems(Node n){
            org.w3c.dom.NodeList itemsList = n.getChildNodes();
            for(int i=0; i<itemsList.getLength(); i++){
                if(itemsList.item(i).getNodeName()=="Item"){
                    ScanItem(itemsList.item(i), itemTable, itemCategory,itemBuyPrice ,itemBids, itemPosition, sellerRating, bidderRating, userLocation);
                }
            }
        
    }
    
    public static void ScanItem(Node n, PrintWriter itemTable, PrintWriter itemCategory,PrintWriter itemBuyPrice,PrintWriter itemBids,PrintWriter itemPosition, PrintWriter sellerRating,PrintWriter bidderRating,PrintWriter userLocation){
        org.w3c.dom.NamedNodeMap nattrib = n.getAttributes();
        String itemID = nattrib.item(0).getNodeValue();
        itemTable.print(itemID+"\t");
        
        org.w3c.dom.NodeList itemChild = n.getChildNodes();
        for(int i=0; i<itemChild.getLength(); i++){
            String name = itemChild.item(i).getNodeName();
            if(name == "Name"){
                String itemName = itemChild.item(i).getChildNodes().item(0).getNodeValue();
                itemTable.print(itemName+"\t");
            }else if(name == "Category"){ //Category in ItemCategory Table
                String category = itemChild.item(i).getChildNodes().item(0).getNodeValue();
                itemCategory.print(itemID+"\t"+category+"\n");
            }else if(name == "Currently"){
                String currently = strip(itemChild.item(i).getChildNodes().item(0).getNodeValue());
                itemTable.print(currently+"\t");
            }else if(name == "Buy_Price"){
                String buyPrice = strip(itemChild.item(i).getChildNodes().item(0).getNodeValue());
                itemBuyPrice.print(itemID+"\t"+buyPrice+"\n");
            }else if(name == "First_Bid"){
                String firstBid = strip(itemChild.item(i).getChildNodes().item(0).getNodeValue());
                itemTable.print(firstBid+"\t");
            }else if(name == "Number_of_Bids"){
                String numOfBids = itemChild.item(i).getChildNodes().item(0).getNodeValue();
                itemTable.print(numOfBids+"\t");
            }else if(name == "Bids"){
                ScanBid(itemChild.item(i), itemID, itemBids, bidderRating, userLocation);
            }else if(name == "Location"){ //latitude and longitude int ItemPosition table
                String Location = itemChild.item(i).getChildNodes().item(0).getNodeValue();
                itemTable.print(Location+"\t");
                org.w3c.dom.NamedNodeMap attList = itemChild.item(i).getAttributes();
                if(attList.getLength()>0){
                	String latitude = attList.item(0).getNodeValue();
                	String longitude = attList.item(1).getNodeValue();
                    itemPosition.print(itemID+"\t"+latitude+"\t"+longitude+"\n");
                }
            }else if(name == "Country"){
                String Country = itemChild.item(i).getChildNodes().item(0).getNodeValue();
                itemTable.print(Country+"\t");
            }else if(name == "Started" || name == "Ends"){ //convert to date
                	String dateString = itemChild.item(i).getChildNodes().item(0).getNodeValue();
                	itemTable.print(reformatDate(dateString)+"\t");
            }else if(name == "Seller"){ //id, rating
                org.w3c.dom.NamedNodeMap sellerAtt = itemChild.item(i).getAttributes();
                String rating = sellerAtt.item(0).getNodeValue();
                String ID = sellerAtt.item(1).getNodeValue();
                itemTable.print(ID+"\t");
                sellerRating.print(ID+"\t"+rating+"\n");
            }else if(name == "Description"){
                if(itemChild.item(i).getChildNodes().getLength()>0){
            		String description = itemChild.item(i).getChildNodes().item(0).getNodeValue();
                	itemTable.println(description);
                }else{
                    itemTable.print("\n");
                }
            }
        }
    }
    
    public static void ScanBid(Node n, String itemID, PrintWriter itemBids, PrintWriter bidderRating, PrintWriter userLocation){
    	org.w3c.dom.NodeList bids = n.getChildNodes();
        for(int i=0; i<bids.getLength(); i++){
            if(bids.item(i).getNodeName() == "Bid"){
                Node bid = bids.item(i);
        		//get bidder, time, amount
        		Node bidder = lookFor(bid, "Bidder");
        		String time = reformatDate( lookFor(bid, "Time").getFirstChild().getNodeValue() );
        		String amount = strip(lookFor(bid, "Amount").getFirstChild().getNodeValue());
        		//get bidder rating and id
        		String rating = bidder.getAttributes().item(0).getNodeValue();
        		String id = bidder.getAttributes().item(1).getNodeValue();
        		//get bidder location and country
                Node locationNode = lookFor(bidder, "Location");
                Node countryNode = lookFor(bidder, "Country");
                String location = locationNode == null ? "" : locationNode.getFirstChild().getNodeValue();
        		String country = countryNode == null ? "" : countryNode.getFirstChild().getNodeValue();
        
        		itemBids.print(itemID+"\t"+id+"\t"+time+"\t"+amount+"\n");
        		bidderRating.print(id+"\t"+rating+"\n");
        		userLocation.print(id+"\t"+location+"\t"+country+"\n");
            }
        }


    }
    
    public static Node lookFor(Node n, String tag){
        org.w3c.dom.NodeList children = n.getChildNodes();
        for(int i=0; i<children.getLength(); i++){
        	if(children.item(i).getNodeName()==tag)
                return children.item(i);
        }
        //System.out.println(tag+" Not found");
        return null;
    }
    
    public static String reformatDate(String dateString){
        try{
        	SimpleDateFormat inputFormat = new SimpleDateFormat("MMM-dd-yy HH:mm:ss");
        	SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date parsed = inputFormat.parse(dateString);
        	return outputFormat.format(parsed);
        }catch(ParseException e){
            System.out.println("Date parse failed");
            return "";
        }
    }
    
    public static void main (String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java MyParser [file] [file] ...");
            System.exit(1);
        }
        
        /* Initialize parser. */
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(true);      
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new MyErrorHandler());
        }
        catch (FactoryConfigurationError e) {
            System.out.println("unable to get a document builder factory");
            System.exit(2);
        } 
        catch (ParserConfigurationException e) {
            System.out.println("parser was unable to be configured");
            System.exit(2);
        }
        
        try{
        	itemTable = new PrintWriter("ItemTable", "UTF-8");
        	itemCategory = new PrintWriter("ItemCategory", "UTF-8");
        	itemBuyPrice = new PrintWriter("ItemBuyPrice", "UTF-8");
        	itemBids = new PrintWriter("ItemBids", "UTF-8");
        	itemPosition = new PrintWriter("ItemPosition", "UTF-8");
        	sellerRating = new PrintWriter("SellerRating", "UTF-8");
        	bidderRating = new PrintWriter("BidderRating", "UTF-8");
        	userLocation = new PrintWriter("UserLocation", "UTF-8");
        
        	/* Process all files listed on command line. */
        	for (int i = 0; i < args.length; i++) {
            	File currentFile = new File(args[i]);
            	processFile(currentFile);
        	}
            
            itemTable.close();
            itemCategory.close();
            itemBuyPrice.close();
            itemBids.close();
            itemPosition.close();
            sellerRating.close();
            bidderRating.close();
            userLocation.close();
        
        }catch(FileNotFoundException e){
            
        }
        catch(UnsupportedEncodingException e){
            
        }
    }
}
