/**
 * @author Daniel Schels
 *
 * objectives: Camel Processor
 * - building body of Message Correlation
 */

package edu.thi.mobilityondemand.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PaymentMessageProcessorCorrelation implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        String transaction_invoiceid = exchange.getProperty("transaction_invoiceid", String.class);
        Double transaction_value = exchange.getProperty("transaction_value", Double.class);

        exchange.getMessage().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getMessage().setBody(
                "{"
				+ "\"messageName\":\"incoming_bank_transition\","
				+ "\"correlationKeys\":{"
				+ "\"invoiceid\":{"
				+ "\"value\": \"" + transaction_invoiceid + "\","
				+ "\"type\":\"String\"" + "}"
				+ "},"
				+ "\"processVariables\":{"
				+ "\"transactionValue\":{"
				+ "\"value\":" + transaction_value + ","
				+ "\"type\":\"Double\"" + "}"
				+ "},"
				+ "\"resultEnabled\":true"
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
