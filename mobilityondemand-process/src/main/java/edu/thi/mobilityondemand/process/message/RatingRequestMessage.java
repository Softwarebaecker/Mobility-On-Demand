// Sandro Käppner

package edu.thi.mobilityondemand.process.message;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ratingRequestMessage")
public class RatingRequestMessage extends Message {
    private static final long serialVersionUID = 1L;

    private Long customerId;
    private Long tripId;

    public RatingRequestMessage(Long customerId, Long tripId) {
        this.customerId = customerId;
        this.tripId = tripId;
        super.setText("Please provide a rating for your trip.");
    }

    public RatingRequestMessage() {}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
