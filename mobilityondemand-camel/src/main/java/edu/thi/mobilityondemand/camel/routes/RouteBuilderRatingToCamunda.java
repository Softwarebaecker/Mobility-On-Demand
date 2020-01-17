/**
 * @author Sandro Käppner
 */
package edu.thi.mobilityondemand.camel.routes;

import edu.thi.mobilityondemand.camel.processor.RatingToCamundaProcessBody;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

public class RouteBuilderRatingToCamunda extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Endpoint source = endpoint("jms:queue:fromCustomer");
        Endpoint destination = endpoint("http://localhost:8080/engine-rest/message");

        from(source)
                .log("new Rating received")
                .process(new RatingToCamundaProcessBody())
                .to(destination);
    }
}
