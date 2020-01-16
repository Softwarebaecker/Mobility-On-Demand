/**
 * @author: Nil Kuchenbäcker
 */

package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class RouteBuilderTaxiToCamunda  extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().host("localhost:8080/engine-rest");

        Endpoint source = endpoint("jms:queue:fromTaxi");
        Endpoint destination = endpoint("http://localhost:8080/engine-rest/message");

        /**
         * Taxi send direct JSON :D
         */
        from(source)
            .setHeader(Exchange.HTTP_METHOD, simple("POST"))
            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
            .log("new Taxi message send to camunda")
            .to(destination);

    }
}

