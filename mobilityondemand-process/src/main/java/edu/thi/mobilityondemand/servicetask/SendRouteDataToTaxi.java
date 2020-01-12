package edu.thi.mobilityondemand.servicetask;

import edu.thi.mobilityondemand.message.TripDataMessage;
import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.jms.Message;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class SendRouteDataToTaxi implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        long tripId = (Long) delegateExecution.getVariable("tripDataId");
        long taxiId = (Long) delegateExecution.getVariable("taxiId");
        long customerId = (Long) delegateExecution.getVariable("customerId");
        String startingpoint = (String) delegateExecution.getVariable("startingpoint");
        String endpoint = (String) delegateExecution.getVariable("endpoint");
        Double kilometers = (Double) delegateExecution.getVariable("kilometers");

        TripDataMessage messageObject = new TripDataMessage();
        messageObject.setTripId(tripId);
        messageObject.setTaxiId(taxiId);
        messageObject.setCustomerId(customerId);
        messageObject.setStartingpoint(startingpoint);
        messageObject.setEndpoint(endpoint);
        messageObject.setKilometers(kilometers);
        messageObject.setText("newRouteData");

        MessageQueue mq = new MessageQueue();
        Message message = mq.createSession().createTextMessage(messageObject.toXml());
        mq.sendMessageToQueue("toTaxi", message);
    }
}
