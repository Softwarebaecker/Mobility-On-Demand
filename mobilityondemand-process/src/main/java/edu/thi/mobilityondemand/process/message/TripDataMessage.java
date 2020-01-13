package edu.thi.mobilityondemand.process.message;

import javax.xml.bind.JAXBException;
import java.io.Serializable;
import java.util.Date;

public abstract class TripDataMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long tripId;
    private String startingpoint;
    private String endpoint;
    private Double kilometers;
    private Long customerId;
    private Date startDate;
    private String text;

    public TripDataMessage(Long tripId, String startingpoint, String endpoint, Double kilometers, Long customerId, Date startDate) {
        this.tripId = tripId;
        this.startingpoint = startingpoint;
        this.endpoint = endpoint;
        this.kilometers = kilometers;
        this.customerId = customerId;
        this.startDate = startDate;
        this.text = "";
    }

    public TripDataMessage() {}

    public abstract String toXml() throws JAXBException;

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getStartingpoint() {
        return startingpoint;
    }

    public void setStartingpoint(String startingpoint) {
        this.startingpoint = startingpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

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
