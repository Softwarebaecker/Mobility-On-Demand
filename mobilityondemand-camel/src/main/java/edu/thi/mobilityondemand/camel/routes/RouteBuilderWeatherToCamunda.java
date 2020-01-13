package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class RouteBuilderWeatherToCamunda extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().host("localhost:8080/engine-rest");

        Endpoint source = endpoint("jms:topic:StormyWeatherEventTopic");
        Endpoint destination = endpoint("http://localhost:8080/engine-rest/message");

        /**
         * Taxi send direct JSON :D
         */
        from(source)
            .setHeader(Exchange.HTTP_METHOD, simple("POST"))
//            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//            .log("RawEvent: ${body}")
//            .setBody(String("{\"messageName\" : \"weather_alert\"}"))
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    String body = "{ \"messageName\" : \"weather_alert\" }";
                    exchange.getIn().setHeader(Exchange.CONTENT_TYPE, constant("application/json"));
                    exchange.getIn().setHeader(Exchange.HTTP_METHOD, simple("POST"));
                    exchange.getIn().setBody(body);
                }
            })
            .log("new Weather Alert")
            .to(destination);
    }

}
