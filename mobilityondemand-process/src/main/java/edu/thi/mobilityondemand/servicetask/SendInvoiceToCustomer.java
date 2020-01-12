/*
 * Daniel Schels
 */


package edu.thi.mobilityondemand.servicetask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Date;
import edu.thi.mobilityondemand.process.beans.Invoice;

public class SendInvoiceToCustomer implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	Invoice invoice = (Invoice) execution.getVariable("Invoice");
    	
    	System.out.println("Send invoice to customer:");
    	System.out.println("##########Invoice: " + invoice.getProduct() + " ######");
    	System.out.println("   Date........... " + invoice.getDate().toString());
    	System.out.println("   From - To...... " + invoice.getDeparture() 
    										  	 + " - " + invoice.getDestination());
    	System.out.println("   Bill amount.... " + invoice.getAmount());
    }
}
