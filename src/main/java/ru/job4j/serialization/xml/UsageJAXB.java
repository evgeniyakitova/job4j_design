package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Contact;
import ru.job4j.serialization.json.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

public class UsageJAXB {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Person result = (Person) unmarshaller.unmarshal(new File("data/person.xml"));
        System.out.println(result);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(result, writer);
        System.out.println(writer);
    }
}
