// Sandro Käppner

package edu.thi.mobilityondemand.process.message;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "tripDataMessageToCustomer")
public class TripDataMessageToCustomer extends TripDataMessage {

    public TripDataMessageToCustomer(Long tripId, String startingpoint, String endpoint, Double kilometers, Long customerId, Date startDate) {
        super(tripId, startingpoint, endpoint, kilometers, customerId, startDate);
        super.setText("Hereby we confirm your booked trip! Please find the details below: ");
    }

    public TripDataMessageToCustomer() {}
}
