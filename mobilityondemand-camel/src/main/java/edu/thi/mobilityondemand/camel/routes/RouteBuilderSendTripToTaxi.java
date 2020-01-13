package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class RouteBuilderSendTripToTaxi extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:toTaxi");
        Endpoint destination = endpoint("file:./CamundaToTaxi");

        //JacksonXMLDataFormat xmlFormater = new JacksonXMLDataFormat();
        JacksonDataFormat json = new JacksonDataFormat();
        json.setPrettyPrint(true);

        from(source)
                .unmarshal().jacksonxml()     //convert from xml to Java Object
                .marshal(json)
                .log("new Taxi message")
                .to(destination);

    }
}

