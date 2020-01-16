/*
 * Author: Daniel Schels
 * 
 * objectives: Camel Processor 
 * - incoming messages via activeMQ REST e.g. Postman (accessing header properties)
 * - building body of Message Correlation
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
		 
		 
	       /* // Example json for Message Correlation:
	        * // see: https://docs.camunda.org/manual/latest/reference/rest/message/post-message/
	        * 
	        * {
	        *     "correlationKeys": {
	        *         "invoiceid": {
	        *             "type": "String",
	        *             "value": "MOD_1"
	        *         }
	        *     },
	        *     "messageName": "IncomingBankTransaction",
	        *     "processVariables": {
	        *         "price": {
	        *             "type": "Double",
	        *             "value": 2.2
	        *         }
	        *     },
	        *     "resultEnabled": true
	        * }
	        */
	}

}
