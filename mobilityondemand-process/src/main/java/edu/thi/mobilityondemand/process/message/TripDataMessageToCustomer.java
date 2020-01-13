package edu.thi.mobilityondemand.process.message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.Date;

@XmlRootElement(name = "tripDataMessageToCustomer")
public class TripDataMessageToCustomer extends TripDataMessage {

    public TripDataMessageToCustomer(Long tripId, String startingpoint, String endpoint, Double kilometers, Long customerId, Date startDate) {
        super(tripId, startingpoint, endpoint, kilometers, customerId, startDate);
        super.setText("Hereby we confirm your booked trip! Please find the details below: ");
    }

    public TripDataMessageToCustomer() {}

    public String toXml() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(this.getClass());
        Marshaller marshaller = jc.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(this, stringWriter);
        return stringWriter.toString();
    }
}
