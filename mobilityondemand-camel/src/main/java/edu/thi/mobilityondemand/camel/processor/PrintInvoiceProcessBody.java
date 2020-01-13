package edu.thi.mobilityondemand.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PrintInvoiceProcessBody implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
    	
    	String x = (String) exchange.getIn().getBody();
    	
    	exchange.getOut().setBody(x);
    	
    	
    	
    	/*
    	String text = "";
    	text += "### Invoice: " + invoice.getProduct() + " ###";
    	text += " Date: " + invoice.getDate().toString();
    	text += " # From - To: " + invoice.getDeparture() + " - " + invoice.getDestination();
    	text += " # Bill amount (€): " + invoice.getAmount();
    	
    	MessageQueue mq = new MessageQueue();
    	TextMessage tm = mq.createSession().createTextMessage();
    	tm.setText(text);
    	mq.sendMessageToQueue("InvoicePrintingQueue", tm);
    	
        long temp1 = (long) exchange.getIn().getHeader("temp1");
        long temp2 = (long) exchange.getIn().getHeader("temp2");
        long temp3 = (long) exchange.getIn().getHeader("temp3");
        long temp4 = (long) exchange.getIn().getHeader("temp4");
        exchange.getOut().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getOut()
        .setBody(
              "{" 
            +   "\"messageName\":\"tempAlertMsg\","
            +   "\"processVariables\":{" 
            +       "\"temp1\":{"
            +           "\"value\":" + temp1 + "," 
            +           "\"type\":\"Long\"" + "},"  
            +       "\"temp2\":{"
            +           "\"value\":" + temp2 + "," 
            +           "\"type\":\"Long\"" + "},"  
            +       "\"temp3\":{"
            +           "\"value\":" + temp3 + "," 
            +           "\"type\":\"Long\"" + "},"  
            +       "\"temp4\":{"
            +           "\"value\":" + temp4 + "," 
            +           "\"type\":\"Long\"" + "}"  
            +   "}," 
            +   "\"resultEnabled\":true"
            + "}"
            
            
        );
        */
     
    }

}
