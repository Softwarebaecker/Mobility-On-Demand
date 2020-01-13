package edu.thi.mobilityondemand.process.message;

import javax.xml.bind.annotation.XmlRootElement;
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

    public Long getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Long taxiId) {
        this.taxiId = taxiId;
    }
}
