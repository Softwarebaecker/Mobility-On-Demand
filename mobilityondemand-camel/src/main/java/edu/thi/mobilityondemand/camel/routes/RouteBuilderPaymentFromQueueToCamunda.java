/*
 * Daniel Schels
 * 
 * objectives: Camel Route
 * - calling Camel Processors
 * - Content based Routing to different Camel processors
 */

package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

import edu.thi.mobilityondemand.camel.processor.PaymentMessageProcessorFromFilesystem;
import edu.thi.mobilityondemand.camel.processor.PaymentMessageProcessorFromRestApi;

public class RouteBuilderPaymentFromQueueToCamunda extends RouteBuilder {

	
	@Override
	public void configure() throws Exception {
		restConfiguration().host("localhost:8080/engine-rest");

		Endpoint source = endpoint("jms:queue:IncomingBankTransaction");
		Endpoint destination = endpoint("http://localhost:8080/engine-rest/message");

		from(source)
		    .log("new Bank transaction received")
			.choice()
				.when(header("CamelFileName").isNotNull())
					.log("Received via Backend integration (File System)")
					.process(new PaymentMessageProcessorFromFilesystem())
					.to(destination) 
				.otherwise()
					.log("Received via REST Frontend (Postman)")
					.process(new PaymentMessageProcessorFromRestApi())
				    .to(destination)
					;

    }
}
