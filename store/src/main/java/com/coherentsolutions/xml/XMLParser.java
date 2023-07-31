package com.coherentsolutions.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;



public class XMLParser {
    public Map<String, Sort> parse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new File("config.xml"));

        //retrieving the root element of the xml file
        Element root = doc.getDocumentElement();

        //creating map
        Map<String, Sort> sorter = new LinkedHashMap<>();

        // getting child nodes of the root element
        var nodes = root.getChildNodes();

        // iterating through the child nodes of the root element
        for (int i = 0; i < nodes.getLength(); i++) {
            // checking if the child node is element
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                // if the child node is element - retrieving the name of the element
                var fieldName = nodes.item(i).getNodeName();
                // retrieving the text between element tags - this is the child node of the element
                var fieldValue = nodes.item(i).getFirstChild().getNodeValue();
                // putting the element name and value to the map (converting the element value from String to Sort enum)
                sorter.put(fieldName, Sort.valueOf(fieldValue.toUpperCase()));


//                System.out.print(i + "---");
//                System.out.print(nodes.item(i).getNodeName() + "---");
//                System.out.print(nodes.item(i).getTextContent() + "---");
//                System.out.println(nodes.item(i).getNodeType() + "---");
//                System.out.print(nodes.item(i).getFirstChild().getNodeValue() + "---");
//                System.out.println(nodes.item(i).getFirstChild().getNodeType() + "---");
            }
        }


        return sorter;
    }
}