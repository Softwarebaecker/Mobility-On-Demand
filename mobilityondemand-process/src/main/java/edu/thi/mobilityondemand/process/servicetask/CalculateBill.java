/**
 * @author Daniel Schels
 */

package edu.thi.mobilityondemand.process.servicetask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.thi.mobilityondemand.process.jpa.Customer;
import edu.thi.mobilityondemand.process.message.InvoiceMessage;

import java.util.Date;

public class CalculateBill implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // Parameters can be adjusted in BPM Model
        Double pricePerKilometre = (Double) execution.getVariable("pricePerKilometre");
        Double basicTravelFee = (Double) execution.getVariable("pricePerKilometre");

        Customer c = (Customer) execution.getVariable("customer_obj");

        Double distance = (Double) execution.getVariable("kilometers");
        distance = Math.round(distance * 100.0) / 100.0;
        int discountPercent = (int) execution.getVariable("discountPercent");
        Long tripid = (Long) execution.getVariable("tripDataId");
        Double amount = (basicTravelFee + (distance * pricePerKilometre)) * (1.0 - discountPercent / 100.);
        amount = Math.round(amount * 100.0) / 100.0;

        InvoiceMessage invoice = new InvoiceMessage(tripid);
        invoice.setPostalAdress(c.getFirstname(), c.getLastname(), c.getAdress());
        invoice.setDiscountPercent(discountPercent);
        invoice.setPrice(amount);
        invoice.setDeparture((String) execution.getVariable("startingpoint"));
        invoice.setDestination((String) execution.getVariable("endpoint"));
        invoice.setDate((Date) execution.getVariable("startDate"));
        invoice.setDistanceKilometres(distance);

        execution.setVariable("Invoice", invoice);

        System.out.println("Bill calculated: " + amount);

    }
}
