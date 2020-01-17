/**
 * @author Nil Kuchenbäcker
 */

package edu.thi.mobilityondemand.process.servicetask;

import edu.thi.mobilityondemand.process.beans.TripDataServiceBean;
import edu.thi.mobilityondemand.process.jpa.TripData;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
@Named
public class TripDataService implements  TripDataServiceLocal{
    @Inject
    TripDataServiceBean tripDataService;

    public void createTrip(DelegateExecution execution) {
        String startingPoint = (String) execution.getVariable("startingpoint");
        String endpoint = (String) execution.getVariable("endpoint");
        Long customerid = (Long) execution.getVariable("customerId");
        Date date = (Date) execution.getVariable("startDate");

        TripData tripData = new TripData();
        tripData.setStartingpoint(startingPoint);
        tripData.setEndpoint(endpoint);
        tripData.setCustomerid(customerid);
        tripData.setAborted(false);
        tripData.setKilometers(Double.valueOf(0));
        tripDataService.create(tripData);
        execution.setVariable("tripDataId", tripData.getTripid());
    }
    public void read(DelegateExecution execution) {
        Long tripDataId = (Long) execution.getVariable("tripDataId");
        TripData tripData = tripDataService.read(tripDataId);
        execution.setVariable("tripData", tripData);
    }

    public void abortTrip(DelegateExecution execution) {
        Long tripDataId = (Long) execution.getVariable("tripDataId");
        TripData tripData = tripDataService.read(tripDataId);
        tripData.setAborted(true);
    }

    public  void search(DelegateExecution execution) {
        Long customerId = (Long) execution.getVariable("customerId");
        List<TripData> tripDataList = tripDataService.searchCustomerTrips(customerId);
        execution.setVariable("tripDataList", tripDataList);
    }

    public void update(DelegateExecution execution) {
        Long tripDataId = (Long) execution.getVariable("tripDataId");
        String startingPoint = (String) execution.getVariable("startingpoint");
        String endpoint = (String) execution.getVariable("endpoint");
        Double kilometers = (Double) execution.getVariable("kilometers");

        TripData tripData = tripDataService.read(tripDataId);

        tripData.setStartingpoint(startingPoint);
        tripData.setEndpoint(endpoint);
        tripData.setKilometers(kilometers);
        execution.setVariable("tripData", tripData);
    }

    // Daniel S
    public void setPrice(DelegateExecution execution) {
    	Double price = (Double) execution.getVariable("price");
     	Long tripDataId = (Long) execution.getVariable("tripDataId");
     	TripData tripData = tripDataService.read(tripDataId);
     	tripData.setPrice(price);
     	execution.setVariable("tripData", tripData);
    }

    // Daniel S
    public void markTripPayed(DelegateExecution execution) {
     	Long tripDataId = (Long) execution.getVariable("tripDataId");
    	TripData tripData = tripDataService.read(tripDataId);
        tripData.setPayed(true);

    }
}
