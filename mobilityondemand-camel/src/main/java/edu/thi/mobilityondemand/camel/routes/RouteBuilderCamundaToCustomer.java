package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class RouteBuilderCamundaToCustomer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:toCustomer");

        JacksonDataFormat json = new JacksonDataFormat();
        json.setPrettyPrint(true);

        from(source)
                .setHeader("customerId",xpath("/*/customerId/text()", String.class))
                .choice().when(xpath("/tripDataMessageToCustomer"))
                    .unmarshal().jacksonxml()     //convert from xml to Java Object
                    .marshal(json)
                    .log("New Trip Confirmation Message to Customer ${header.customerId}")
                    .to("file:./CamundaToCustomersMessages?fileName=TripConfirmation_${date:now:yyyy-MM-dd_HH-mm-ss-SS}_Customer_${header.customerId}.txt")
                .when(xpath("/cancellationMessage"))
                    .unmarshal().jacksonxml()     //convert from xml to Java Object
                    .marshal(json)
                    .log("New Cancellation Message to Customer ${header.customerId}")
                    .to("file:./CamundaToCustomersMessages?fileName=CancellationMessage${date:now:yyyy-MM-dd_HH-mm-ss-SS}_Customer_${header.customerId}.txt")
                .when(xpath("/ratingRequestMessage"))
                    .unmarshal().jacksonxml()     //convert from xml to Java Object
                    .marshal(json)
                    .log("New Rating Request to Customer ${header.customerId}")
                    .to("file:./CamundaToCustomersMessages?fileName=RatingRequestMessage${date:now:yyyy-MM-dd_HH-mm-ss-SS}_Customer_${header.customerId}.txt")
                .otherwise()
                    .log("unknown Message: ${body}");
    }
}
