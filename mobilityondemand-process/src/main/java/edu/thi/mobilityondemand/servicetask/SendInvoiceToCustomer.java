/*
 * Daniel Schels
 */


package edu.thi.mobilityondemand.servicetask;


import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Date;
import edu.thi.mobilityondemand.process.beans.Invoice;

public class SendInvoiceToCustomer implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	
        Date date = new Date();
        
        Invoice invoice = new Invoice(date, "München", "Nürnberg", 180, 56.3);
        
    	//Invoice invoice = (Invoice) execution.getVariable("Invoice");
    	
    	String text = "";
    	text += "### Invoice: " + invoice.getProduct() + " ###";
    	text += " Date: " + invoice.getDate().toString();
    	text += " # From - To: " + invoice.getDeparture() + " - " + invoice.getDestination();
    	text += " # Bill amount (€): " + invoice.getAmount();
    	
    	/*
    	System.out.println("Send invoice to customer:");
    	System.out.println("##########Invoice: " + invoice.getProduct() + " ######");
    	System.out.println("   Date........... " + invoice.getDate().toString());
    	System.out.println("   From - To...... " + invoice.getDeparture() 
    										  	 + " - " + invoice.getDestination());
    	System.out.println("   Bill amount.... " + invoice.getAmount());
    	*/
    	
    
    	MessageQueue mq = new MessageQueue();
    	TextMessage tm = mq.createSession().createTextMessage();
    	tm.setText(text);
    	mq.sendMessageToQueue("InvoicePrintingQueue", tm);
    }
}
