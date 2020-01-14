/**
 * @auther Sandro Käppner
 */

package edu.thi.mobilityondemand.process.message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.Serializable;
import java.io.StringWriter;

public abstract class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;

    public Message() {}

    public String toXml() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(this.getClass());
        Marshaller marshaller = jc.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(this, stringWriter);
        return stringWriter.toString();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
