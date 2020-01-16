/*
 * Author: Daniel Schels
 * 
 * objectives: Camel Processor 
 * - incoming messages via activeMQ REST e.g. Postman (accessing header properties)
 */

package edu.thi.mobilityondemand.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PaymentMessageProcessorFromRestApi implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		String transaction_invoiceid = (String) exchange.getIn().getHeader("invoiceid");
		String transaction_value_s = (String) exchange.getIn().getHeader("value");

		Double transaction_value = Double.parseDouble(transaction_value_s);
		

		exchange.setProperty("transaction_invoiceid", transaction_invoiceid);
		exchange.setProperty("transaction_value", transaction_value);

	}

}
