/*
 * Author: Daniel Schels
 * 
 * objectives: Camel Processor 
 * - with integration of backend system with specific behaviour -> customized parser
 * - building body of Message Correlation
 */

package edu.thi.mobilityondemand.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PaymentMessageProcessorFromFilesystem implements Processor {
	
	@Override
	public void process(Exchange exchange) throws Exception {

		String transaction_invoiceid = "";
		Double transaction_value = -1D;
		
		String message = exchange.getIn().getBody(String.class);
		String line[] = message.split("\\r?\\n");
		for(int i = 0; i < line.length; i++) {
			if(line[i].contains("invoiceid")) {
				String split_invoiceid_line [] = line[i].split("=");
				transaction_invoiceid = split_invoiceid_line[1].replace("\"", "");
			}else if(line[i].contains("value")) {
				String split_value_line [] = line[i].split("=");
				String transaction_value_s = split_value_line[1].replace("\"", "");
				transaction_value = Double.parseDouble(transaction_value_s);
			}
		}
		
		if(transaction_invoiceid != "" && transaction_value > 0)
			System.out.println("Payment from _filesystem_ parsed - invoiceid: " + transaction_invoiceid + " - value: " + transaction_value);
		else
			System.out.println("Payment from _filesystem_ could not be parsed -  invoiceid and/or value missing");
		
		exchange.getOut().setHeader(Exchange.HTTP_METHOD, "POST");
		exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
		exchange.getOut().setBody(
			   "{" 
              +   	"\"messageName\":\"IncomingBankTransaction\","
	          +	  	"\"correlationKeys\":{" 
              +       	"\"invoiceid\":{"
              +          	"\"value\": \"" + transaction_invoiceid + "\"," 
              +           	"\"type\":\"String\"" + "}" 
              +     "},"
              +     "\"processVariables\":{" 
              +     	"\"transactionValue\":{"
              +             "\"value\":" + transaction_value + "," 
              +             "\"type\":\"Double\"" + "}" 
              +     "}," 
              +     "\"resultEnabled\":true"
              + "}"
              );

	}
}
