/**
 * @author: Nil Kuchenbäcker
 */

package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class RouteBuilderWeatherToCamunda extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().host("localhost:8080/engine-rest");

        Endpoint source = endpoint("jms:queue:StormyWeatherEventQueue");
        Endpoint destination = endpoint("http://localhost:8080/engine-rest/signal");

        /**
         * Taxi send direct JSON :D
         */
        from(source)
            .setHeader(Exchange.HTTP_METHOD, simple("POST"))
            .setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
            .setBody(simple("{ \"name\" : \"weather_alert\" }"))
            .log("new Weather Alert")
            .to(destination);
    }

}

