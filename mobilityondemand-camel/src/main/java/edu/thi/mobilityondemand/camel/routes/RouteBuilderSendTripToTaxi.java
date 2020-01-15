package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class RouteBuilderSendTripToTaxi extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:toTaxi");

        JacksonDataFormat json = new JacksonDataFormat();
        json.setPrettyPrint(true);

        from(source)
                .unmarshal().jacksonxml()     //convert from xml to Java Object
                .marshal(json)
                .log("new Taxi message")
                .setHeader("tripId",jsonpath("$.tripId", String.class))
                .to("file:./CamundaToTaxi?fileName=TripData_${date:now:yyyy-MM-dd_HH-mm-ss-SS}_ID_${header.tripId}");

    }
}

