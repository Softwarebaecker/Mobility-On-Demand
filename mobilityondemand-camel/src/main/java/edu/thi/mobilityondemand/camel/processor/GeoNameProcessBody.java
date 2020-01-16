/**
 * @author: Nil Kuchenbäcker
 */

package edu.thi.mobilityondemand.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeoNameProcessBody implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        Long tripId = (Long) exchange.getProperty("tripId");
        String location = (String) exchange.getProperty("location");

        JSONObject jObject = new JSONObject(body);
        JSONArray geonames = jObject.getJSONArray("geonames");
        if (!geonames.isEmpty()) {
            double latitude = geonames.getJSONObject(0).getDouble("lat");
            double longitude = geonames.getJSONObject(0).getDouble("lng");
            System.out.println("tripId = " + tripId);
            System.out.println("location = " + location);
            System.out.println("latitude = " + latitude);
            System.out.println("longitude = " + longitude);

            String newBody = "{"
                    + "\"tripId\":" + tripId + ","
                    + "\"Location\":\"" + location + "\","
                    + "\"latitude\":" + latitude + ","
                    + "\"longitude\":" + longitude
                    + "}";
            exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "application/json");
            exchange.getMessage().setBody(newBody);

        }
    }
}