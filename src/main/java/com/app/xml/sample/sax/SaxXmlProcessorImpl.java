package com.app.xml.sample.sax;

import com.app.xml.common.Person;
import com.app.xml.common.XmlProcessor;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SaxXmlProcessorImpl implements XmlProcessor {

    @Override
    public List<Person> readData() {
        try {
            List<Person> list = new ArrayList<>();

            SAXParserFactory factory = SAXParserFactory.newInstance(); // создаем SAX парсер
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File("resSAX.xml"), new DefaultHandler() {

                private boolean inName;
                private boolean inAge;
                private Person currentPerson;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//                    System.out.println("Start :" + qName);
                    if ("person".equalsIgnoreCase(qName)){
                        currentPerson = new Person();
                    }
                    if ("name".equalsIgnoreCase(qName)){
                        inName=true;
                    }
                    if ("age".equalsIgnoreCase(qName)){
                        inAge=true;
                    }

                    if (attributes.getLength()>0){
                        for (int i = 0; i < attributes.getLength(); i++) {
                            String key = attributes.getQName(i); //type
                            String value = attributes.getValue(key);

                            System.out.println("key = " + key);
                            System.out.println("val = " + value);

                        }
                    }

                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    String val = new String(ch,start,length);
                    //                    System.out.println(val);


                    if (inAge){
                     currentPerson.setAge(Integer.parseInt(val));
                 }
                 if (inName){
                        currentPerson.setName(val);
                    }

                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
//                    System.out.println("End :" + qName);

                    if ("person".equalsIgnoreCase(qName)){
                        list.add(currentPerson);
                    }

                    if ("name".equalsIgnoreCase(qName)){
                        inName=false;
                    }
                    if ("age".equalsIgnoreCase(qName)){
                        inAge=false;
                    }

                }

            });
            return list;

        } catch (Exception ex) {
            throw  new RuntimeException(ex);
        }


    }




    @Override
    public void writeData(List<Person> data, File file) {
        StringBuilder sb = new StringBuilder();
        sb.append("<persons>\n");

        for (Person p : data) {
            String s = String.format("\t<person type =\"%s\" salary = \"%s\">\n", "type"+p.getName(), p.getAge());
//            sb.append("\t<person>\n");
            sb.append(s);
            sb.append(String.format("\t\t<name>%s</name>",p.getName()));
            sb.append(String.format("\t\t<age>%s</age>",p.getAge()));
            sb.append("\t</person>\n");

        }


        sb.append("</persons>");

        try(
        OutputStream out = new FileOutputStream(file);){
            out.write(sb.toString().getBytes());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
