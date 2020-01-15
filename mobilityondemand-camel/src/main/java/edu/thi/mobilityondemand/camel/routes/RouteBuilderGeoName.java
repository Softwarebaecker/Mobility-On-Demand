package edu.thi.mobilityondemand.camel.routes;

import edu.thi.mobilityondemand.camel.processor.GeoNameProcessBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;


public class RouteBuilderGeoName extends RouteBuilder {
    static final String geoNameApiUrl = "http://api.geonames.org/search";
    @Override
    public void configure() throws Exception {

        Endpoint camunda = endpoint("http://localhost:8080/engine-rest/message");

        from("jms:queue:cityToPositionRequest")
                .recipientList(header("jms:queue:cityToPosition"))
                .log("Location Request recieved!")
//                .log("${header.location}")
//                .log("${header.q}")
//                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
//                .setHeader("http_querry", simple("username=mond"))
//                .setHeader("Accept", constant("application/json"))
//                .setHeader("http_querry", simple("maxRows=1"))
//                .setHeader("http_querry", simple("name_equals=${header.location}"))
//                .to(geoNameApiUrl + "?username=mond&name=${header.location}")
//                .setHeader(HTTP_METHOD, simple("GET"))
//                .setHeader(CONTENT_TYPE, constant("application/json"))
                .setProperty("tripId", simple("${header.tripId}"))
                .setProperty("location", simple("${header.location}"))
                .toD("http://api.geonames.org/searchJSON?username=mond&q=${header.location}&maxRows=1")
                .process(new GeoNameProcessBody())
                .to("jms:queue:cityToPositionResponse")
        ;
    }
}
