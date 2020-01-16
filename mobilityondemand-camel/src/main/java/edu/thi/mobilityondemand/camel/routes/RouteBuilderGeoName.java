package edu.thi.mobilityondemand.camel.routes;

import edu.thi.mobilityondemand.camel.processor.GeoNameProcessBody;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;

/**
 * Get a request for a position of a City and gives back the Latitude/Longitude
 */
public class RouteBuilderGeoName extends RouteBuilder {
    static final String geoNameApiUrl = "http://api.geonames.org/search";
    @Override
    public void configure() throws Exception {

        Endpoint camunda = endpoint("http://localhost:8080/engine-rest/message");

        from("jms:queue:cityToPositionRequest")
                .recipientList(header("jms:queue:cityToPosition"))
                .log("${date:now:HH:mm:ss:SS}: Location Request received!")
                .setProperty("tripId", simple("${header.tripId}"))
                .setProperty("location", simple("${header.location}"))
                .toD("http://api.geonames.org/searchJSON?username=mond&q=${header.location}&maxRows=1")
                .process(new GeoNameProcessBody())
                .unmarshal().json()
                .marshal().jacksonxml()
                .convertBodyTo(String.class)
//                .log("${body}")
//                .to("jms:queue:test")
        ;
    }
}
