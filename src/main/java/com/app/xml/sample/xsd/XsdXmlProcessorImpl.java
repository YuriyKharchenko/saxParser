package com.app.xml.sample.xsd;

import com.app.xml.common.Person;
import com.app.xml.common.Persons;
import com.app.xml.common.XmlProcessor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XsdXmlProcessorImpl implements XmlProcessor{
    @Override
    public List<Person> readData() {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Persons.class); //кого преобразуем указываем аннотациями // маппинг через аннотации
            Unmarshaller unmarshaller = ctx.createUnmarshaller();             //Маршаллер из JAVA в XML
            File file = new File("resXSD.xml");
            Persons  persons = (Persons) unmarshaller.unmarshal(file);
            return persons.getPersons();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeData(List<Person> data, File file) {    // Преобразуем JAVA в XML
        try {
            JAXBContext ctx = JAXBContext.newInstance(Persons.class); //кого преобразуем указываем аннотациями // маппинг через аннотации
            Marshaller marshaller = ctx.createMarshaller();             //Маршаллер из JAVA в XML
            Persons persons = new Persons(data);     // передаем в него лист из персон

            marshaller.marshal(persons, file);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}