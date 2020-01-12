package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;

public class RouteBuilderCamundaToCustomer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:toCustomer");
        Endpoint destination = endpoint("file:./CamundaToCustomersMessages");

        //JacksonXMLDataFormat xmlFormater = new JacksonXMLDataFormat();
        JacksonDataFormat json = new JacksonDataFormat();
        json.setPrettyPrint(true);

        from(source)
                .unmarshal().jacksonxml()     //convert from xml to Java Object
                .marshal(json)
                .to(destination);

    }
}
