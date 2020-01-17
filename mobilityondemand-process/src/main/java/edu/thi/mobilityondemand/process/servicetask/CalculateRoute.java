/**
 * @author Nil Kuchenbäcker
 */

package edu.thi.mobilityondemand.process.servicetask;

import edu.thi.mobilityondemand.process.message.GeoDataMessage;
import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


import javax.jms.*;


public class CalculateRoute implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String startingposition = (String) delegateExecution.getVariable("startingpoint");
        String endposition = (String) delegateExecution.getVariable("endpoint");
        Long tripDataId = (Long) delegateExecution.getVariable("tripDataId");

        GeoPosition geoStartingposition = GeoPosition.getPosition(startingposition, tripDataId);
        GeoPosition geoEndposition = GeoPosition.getPosition(endposition, tripDataId);
        if (geoStartingposition == null || geoEndposition == null) {
            throw new BpmnError("NO_ROUTE_FOUND");
        }


        double distance = GeoPosition.distFrom(geoStartingposition, geoEndposition);

        if (distance > 1000) {
            throw new BpmnError("NO_ROUTE_FOUND");
        } else {
            delegateExecution.setVariable("kilometers", distance);
        }

    }

    public static class GeoPosition {
        public double latitude;
        public double longitude;

        public static GeoPosition getPosition(String cityName, Long tripDataId) {
            GeoPosition position = new GeoPosition();
            try {
                MessageQueue mq = new MessageQueue();
                Session session = mq.createSession();
                // temporary destination for receive the message
                Destination tempDest = session.createTemporaryQueue();
                Destination destination = session.createQueue("cityToPositionRequest");
                MessageProducer producer = session.createProducer(destination);

                //create Message
                Message message = session.createMessage();
                message.setLongProperty("tripId", tripDataId);
                message.setStringProperty("location", cityName);
                message.setJMSCorrelationID(tripDataId.toString());
                message.setJMSReplyTo(tempDest);

                producer.send(message);

                //receive Message
                MessageConsumer consumer = session.createConsumer(tempDest);
                Message receiveMessage = consumer.receive();
                session.close();

                if (receiveMessage instanceof TextMessage) {
                    String body = ((TextMessage) receiveMessage).getText();
                    GeoDataMessage responseData = GeoDataMessage.fromString(body);
                    position.latitude = responseData.getLatitude();
                    position.longitude = responseData.getLongitude();
                } else {
                    System.out.println("Catcher: Calculate Route no Text Message");
                    return null;
                }


            } catch (Exception exception) {
                System.out.println("Catcher: Calculate Route");
                exception.printStackTrace();
                return null;
            }

            return position;

        }

        /**
         * Give back the distance of two GeoPositions
         * Copied from https://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
         * adapted to Class GeoPosition
         *
         * @param positionA position A
         * @param positionB position B
         * @return distance in kilometers
         */
        public static double distFrom(GeoPosition positionA, GeoPosition positionB) {
            double earthRadius = 6371000; //meters
            double dLat = Math.toRadians(positionB.latitude - positionA.latitude);
            double dLng = Math.toRadians(positionB.longitude - positionA.longitude);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(positionA.latitude)) * Math.cos(Math.toRadians(positionB.latitude)) *
                            Math.sin(dLng / 2) * Math.sin(dLng / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double dist = (earthRadius * c);
            dist = dist / 1000; // convert to kilometers

            return dist;
        }

    }


}
