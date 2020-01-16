/*
 * Author: Daniel Schels
 * 
 * objectives: Camel Route
 * - import data from file system to queue (= Simulates message of external backend system)
 */

package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

public class RouteBuilderPaymentFromFilesystemToQueue extends RouteBuilder{
	@Override
	public void configure() throws Exception {
		Endpoint source = endpoint("file:PaymentInbox?noop=true");
        Endpoint destination = endpoint("jms:queue:IncomingBankTransaction");

        from(source)
        		.log("New Payment received")
                .to(destination);

    }
}
