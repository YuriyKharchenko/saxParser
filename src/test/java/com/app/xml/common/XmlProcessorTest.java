package com.app.xml.common;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XmlProcessorTest {

    private static final File resultXMLSAX = new File("resSAX.xml");
    private static final File resultXMLDOM = new File("resDOM.xml");
    private static final File resultXMLXSD = new File("resXSD.xml");


    @Test
    public void testSAX() throws Exception {     // сверяем read and write
        testWriteSAX(XMLMain.getProcessor(0));
        testReadSax(XMLMain.getProcessor(0));
//        InputStream in = getClass().getResourceAsStream("/persons.xml"); // читаем xml из test/resources

    }

    @Ignore
    @Test
    public void testDOM() throws Exception {
        testWriteDOM(XMLMain.getProcessor(1));
        testReadSax(XMLMain.getProcessor(1));
    }

    @Test
    public void testXSD() throws Exception {
        testWriteXSD(XMLMain.getProcessor(2));
        testReadSax(XMLMain.getProcessor(2));
    }


    private void testWriteSAX(XmlProcessor processor){  // для SAX
        List<Person> list = initTestData();
        processor.writeData(list,resultXMLSAX);
    }

    private void testWriteDOM(XmlProcessor processor) {  // для DOM
        List<Person> list = initTestData();
        processor.writeData(list, resultXMLDOM);
    }

    private void testWriteXSD(XmlProcessor processor) {  //
        List<Person> list = initTestData();
        processor.writeData(list, resultXMLXSD);
    }


    private void testReadSax(XmlProcessor processor){
        List<Person> expectedList = initTestData();
        List<Person> actualList = processor.readData();
        assertEquals(expectedList.size(),actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i),actualList.get(i));
        }
        assertTrue(Arrays.equals(expectedList.toArray(),actualList.toArray()));
    }



    private List<Person> initTestData(){
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Person("name"+i,i));
        }
        return list;
    }
}