/*
 * Daniel Schels
 */

package edu.thi.mobilityondemand.servicetask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.thi.mobilityondemand.process.beans.Invoice;

import java.util.Date;

public class CalculateBill implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	
    	// hardcoded bill parameters
	    Double pricePerKilometre = 1.20; 	// TODO Camunda Variable
	    Long basicTravelFee = 5L;			// TODO Camunda Variable
	    
	    // get trip details from execution
	    Double distance = (Double) execution.getVariable("kilometers");
	    int discountPercent = (int) execution.getVariable("discountPercent");
	    Date date = (Date) execution.getVariable("startDate");
	    String departure = (String) execution.getVariable("startingpoint");
	    String destination = (String) execution.getVariable("endpoint");
	    Long tripid = (Long) execution.getVariable("tripDataId");
	    
	    Double amount = (basicTravelFee + (distance * pricePerKilometre)) * discountPercent / 100.;
	    Invoice invoice = new Invoice(tripid, date, departure, destination, distance, amount);
	    
	    System.out.println("Invoic debug:" + invoice.getDeparture() + invoice.getComment());
	        
	    execution.setVariable("Invoice", invoice);
	    
	    System.out.println("Bill calculated: " + amount);

    }
}
