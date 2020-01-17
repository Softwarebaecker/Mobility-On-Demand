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

        // hardcoded bill parameters
        Double pricePerKilometre = 1.20;     // TODO Camunda Variable
        Long basicTravelFee = 5L;            // TODO Camunda Variable

        // get trip details from execution
        Customer c = (Customer) execution.getVariable("customer_obj");

        Double distance = (Double) execution.getVariable("kilometers");
        int discountPercent = (int) execution.getVariable("discountPercent");
        Long tripid = (Long) execution.getVariable("tripDataId");
        Double amount = (basicTravelFee + (distance * pricePerKilometre)) * discountPercent / 100.;

        InvoiceMessage invoice = new InvoiceMessage(tripid);
        invoice.setPostalAdress(c.getFirstname(), c.getLastname(), c.getAdress());
        invoice.setDiscount(discountPercent);
        invoice.setAmount(amount);
        invoice.setDeparture((String) execution.getVariable("startingpoint"));
        invoice.setDestination((String) execution.getVariable("endpoint"));
        invoice.setDate((Date) execution.getVariable("startDate"));
        invoice.setDistance(distance);

        execution.setVariable("Invoice", invoice);

        System.out.println("Bill calculated: " + amount);

    }
}
