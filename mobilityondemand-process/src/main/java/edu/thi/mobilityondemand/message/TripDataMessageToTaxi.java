package edu.thi.mobilityondemand.message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.Date;

@XmlRootElement(name = "TripDataMessageToTaxi")
public class TripDataMessageToTaxi extends TripDataMessage {
    private static final long serialVersionUID = 1L;

    private Long taxiId;

    public TripDataMessageToTaxi(Long taxiId, Long tripId, String startingpoint, String endpoint, Double kilometers, Long customerId, Date startDate) {
        super(tripId, startingpoint, endpoint, kilometers, customerId, startDate);
        this.taxiId = taxiId;
        super.setText("newRouteData");
    }

    public TripDataMessageToTaxi() {}

    public String toXml() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(this.getClass());
        Marshaller marshaller = jc.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(this, stringWriter);
        return stringWriter.toString();
    }

    @XmlElement
    public Long getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Long taxiId) {
        this.taxiId = taxiId;
    }
}
