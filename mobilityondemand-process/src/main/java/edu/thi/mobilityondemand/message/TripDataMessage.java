package edu.thi.mobilityondemand.message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;

@XmlRootElement(name = "TripDataMessage")
public class TripDataMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long taxiId;
    private Long tripId;
    private String startingpoint;
    private String endpoint;
    private Double kilometers;
    private Long customerId;
    private Date startDate;
    private String text;

    public TripDataMessage(Long taxiId, Long tripId, String startingpoint, String endpoint, Double kilometers, Long customerId, Date startDate, String text) {
        this.taxiId = taxiId;
        this.tripId = tripId;
        this.startingpoint = startingpoint;
        this.endpoint = endpoint;
        this.kilometers = kilometers;
        this.customerId = customerId;
        this.startDate = startDate;
        this.text = text;
    }

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

    @XmlElement
    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    @XmlElement
    public String getStartingpoint() {
        return startingpoint;
    }

    public void setStartingpoint(String startingpoint) {
        this.startingpoint = startingpoint;
    }

    @XmlElement
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @XmlElement
    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    @XmlElement
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
