/*
 * Daniel Schels
 */

package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jacksonxml.JacksonXMLDataFormat;

import edu.thi.mobilityondemand.camel.processor.PrintInvoiceProcessBody;

public class RouteBuilderCamundaToInvoicePrinter  extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		Endpoint source = endpoint("jms:queue:InvoicePrintingQueue");
        Endpoint destination = endpoint("file:./InvoicesPrinted");

        JacksonDataFormat json = new JacksonDataFormat();
        json.setPrettyPrint(true);

        from(source)
		        .unmarshal().jacksonxml()     //convert from xml to Java Object
		        .marshal(json)
        		.log("New Invoice delivered")
                //.process(new PrintInvoiceProcessBody()) 	// optional: pdf printer?
                .to(destination);

    }
	
}
