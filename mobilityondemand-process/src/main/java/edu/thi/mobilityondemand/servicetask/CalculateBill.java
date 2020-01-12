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
    
    int travelDistance = 180; // TODO Tripdataobject
    int pricePerKilometre = 45; // TODO Camunda Variable
    int basicTravelFee = 5;		// TODO Camunda Variable
    int discountPercent = (int) execution.getVariable("discountPercent");
    Date date = new Date();
    
    double amount = (basicTravelFee + (travelDistance * pricePerKilometre)) * discountPercent / 100.;
    Invoice invoice = new Invoice(date, "Berlin", "Salzburg", 600, amount);
    
    System.out.println("Invoic debug:" + invoice.getDeparture() + invoice.getComment());
        
    execution.setVariable("Invoice", invoice);
    
    System.out.println("Bill calculated: " + amount);

    }
}
