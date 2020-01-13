package edu.thi.mobilityondemand.servicetask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;


public class CalculateRoute implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String startingposition = (String) delegateExecution.getVariable("startingpoint");
        String endposition = (String) delegateExecution.getVariable("endpoint");

        GeoPosition geoStartingposition = GeoPosition.getPosition(startingposition);
        GeoPosition geoEndposition = GeoPosition.getPosition(endposition);
        double distance = GeoPosition.distFrom(geoStartingposition, geoEndposition);

        delegateExecution.setVariable("kilometers", distance);

//        delegateExecution.setVariable("kilometers", Double.valueOf(200));

    }

    public static class GeoPosition {
        public double latitude;
        public double longitude;

        public static GeoPosition getPosition(String cityName) {

            GeoPosition position = new GeoPosition();
            /**
             * mostly copied from https://www.geonames.org/source-code/
             */
            WebService.setUserName("MonD");
            ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
            searchCriteria.setQ(cityName);
            try {
                ToponymSearchResult searchResult = WebService.search(searchCriteria);


                // get first Position (it is only for dynamic)
                for (Toponym toponym : searchResult.getToponyms()) {
                    position.latitude = toponym.getLatitude();
                    position.longitude = toponym.getLongitude();
                    System.out.println(toponym.getName() + " " + toponym.getCountryName());
                    break;
                    //TODO: remove loop
                }
            }
            catch (Exception exeption) {
                System.out.println("Exeption for Postion " + cityName);
                exeption.printStackTrace();
                position.latitude = 0;
                position.longitude = 0;
            };

            return position;
        }

        /**
         * Give back the distance of two GeoPositions
         * Copied from https://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
         *      adapted to Class GeoPosition
         * @param positinA position A
         * @param positionB position B
         * @return distance in kilometers
         */
        public static double distFrom(GeoPosition positinA, GeoPosition positionB) {
            double earthRadius = 6371000; //meters
            double dLat = Math.toRadians(positionB.latitude - positinA.latitude);
            double dLng = Math.toRadians(positionB.longitude - positinA.longitude);
            double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.cos(Math.toRadians(positinA.latitude)) * Math.cos(Math.toRadians(positionB.latitude)) *
                            Math.sin(dLng/2) * Math.sin(dLng/2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            double dist = (earthRadius * c);
            dist = dist / 1000; // convert to kilometers

            return dist;
        }
    }



}
