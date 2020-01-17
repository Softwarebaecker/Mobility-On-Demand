/**
 * @author Nil Kuchenbäcker
 */

package edu.thi.mobilityondemand.process.message;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "TripDataMessageToTaxi")
public class TripDataMessageToTaxi extends TripDataMessage {
    private static final long serialVersionUID = 1L;


    public TripDataMessageToTaxi(Long tripId, String startingpoint, String endpoint, Double kilometers, Long customerId, Date startDate) {
        super(tripId, startingpoint, endpoint, kilometers, customerId, startDate);
        super.setText("newRouteData");
    }

    public TripDataMessageToTaxi() {
    }
}
