package com.example.mypackage.parser;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by patriciaparker on 9/17/17.
 */

public class XMLHandler extends DefaultHandler {
    public static XMLGettersSetters data;
    String elementValue;
    Boolean elementOn = false;

    public static XMLGettersSetters getXMLData() {
        return XMLHandler.data;
    }


    public static void setXMLData(XMLGettersSetters data) {
        XMLHandler.data = data;
    }

    /**
     * This will be called when the tags of the XML starts.
     **/
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        this.elementOn = true;

        if (localName.equals("CATALOG")) {
            XMLHandler.data = new XMLGettersSetters();
        } else if (localName.equals("CD")) {

            try {
                String attributeValue = attributes.getValue("attr");
                XMLHandler.data.setCD(attributeValue);
            }//end try


            //catch error if thrown to avoid possible null pointer exception
            catch (Exception e) {
                Log.i("err on handler   ", e.getMessage());
            }
        }

    }

    /**
     * This will be called when the tags of the XML end.
     **/
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        this.elementOn = false;

        /**
         * Sets the values after retrieving the values from the XML tags
         * */
        if (localName.equalsIgnoreCase("title"))
            XMLHandler.data.setTitle(this.elementValue);
        else if (localName.equalsIgnoreCase("artist"))
            XMLHandler.data.setArtist(this.elementValue);
        else if (localName.equalsIgnoreCase("country"))
            XMLHandler.data.setCountry(this.elementValue);
        else if (localName.equalsIgnoreCase("company"))
            XMLHandler.data.setCompany(this.elementValue);
        else if (localName.equalsIgnoreCase("price"))
            XMLHandler.data.setPrice(this.elementValue);
        else if (localName.equalsIgnoreCase("year"))
            XMLHandler.data.setYear(this.elementValue);
    }

    /**
     * This is called to get the tags value
     **/
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        if (this.elementOn) {
            this.elementValue = new String(ch, start, length);
            this.elementOn = false;
        }

    }
}
