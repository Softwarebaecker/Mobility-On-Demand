/**
 * @author Sandro Käppner
 */

package edu.thi.mobilityondemand.process.restservice;

import edu.thi.mobilityondemand.process.beans.TripDataServiceBean;
import edu.thi.mobilityondemand.process.jpa.TripData;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("trips")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TripDataRest {
    @Inject
    TripDataServiceBean tripDataService;

    @GET
    public List<TripData> search(@QueryParam("customerId") Long customerId) {
        if(customerId == null) {
            return tripDataService.findAllTrips();
        }
        else {
            return tripDataService.searchCustomerTrips(customerId);
        }
    }

    @GET
    @Path("{id}")
    public TripData read(@PathParam("id") Long id) {
        return tripDataService.read(id);
    }
}
