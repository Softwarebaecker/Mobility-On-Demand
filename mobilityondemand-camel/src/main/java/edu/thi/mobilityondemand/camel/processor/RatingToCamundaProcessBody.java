//Sandro Käppner

package edu.thi.mobilityondemand.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RatingToCamundaProcessBody implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String customerId = (String) exchange.getIn().getHeader("customerId");
        String tripId = (String) exchange.getIn().getHeader("tripId");
        String numberOfStars = (String) exchange.getIn().getHeader("numberOfStars");
        String comment = (String) exchange.getIn().getHeader("comment");

        exchange.getOut().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getOut().setBody(
            "{"
            +   "\"messageName\" : \"new_rating\","
            +   "\"correlationKeys\" : {"
            +       "\"customerId\" : {\"value\" : " + customerId + ", \"type\" : \"Long\"},"
            +       "\"tripId\" : {\"value\" : " + tripId + ", \"type\" : \"Long\"}"
            +   "},"
            +   "\"processVariables\" : {"
            +       "\"numberOfStars\" : {\"value\" : " + numberOfStars + ", \"type\" : \"Integer\"},"
            +       "\"comment\" : {\"value\" : \"" + comment + "\", \"type\" : \"String\"}"
            +   "}"
            + "}"
        );
    }
}
