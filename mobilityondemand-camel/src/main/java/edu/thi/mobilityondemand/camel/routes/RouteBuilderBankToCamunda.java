package edu.thi.mobilityondemand.camel.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import edu.thi.mobilityondemand.camel.processor.BankToCamundaProcessBody;

public class RouteBuilderBankToCamunda extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		restConfiguration().host("localhost:8080/engine-rest");

		Endpoint source = endpoint("jms:queue:IncomingBankTransaction");
		Endpoint destination = endpoint("http://localhost:8080/engine-rest/message");

		from(source)
            .log("new Bank transaction received")
            .process(new BankToCamundaProcessBody())
            .to(destination);    

    }
}
