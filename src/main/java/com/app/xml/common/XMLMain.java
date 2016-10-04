package com.app.xml.common;

import com.app.xml.sample.sax.SaxXmlProcessorImpl;
import com.app.xml.sample.xsd.XsdXmlProcessorImpl;

public class XMLMain {
    public static void main(String[] args) {
        XmlProcessor xmlProcessor = getProcessor(0);




    }
    public static XmlProcessor getProcessor(int type){
        switch (type){
            case 0:
                return new SaxXmlProcessorImpl();
            case 1:
                return null;
            case 2:
                return new XsdXmlProcessorImpl();
            default: throw new RuntimeException("Incorrect type:" + type);
        }
    }
}
