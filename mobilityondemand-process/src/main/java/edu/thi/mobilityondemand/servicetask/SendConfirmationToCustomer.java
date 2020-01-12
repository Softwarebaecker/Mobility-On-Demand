package edu.thi.mobilityondemand.servicetask;

import edu.thi.mobilityondemand.message.TripDataMessage;
import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.jms.Message;

public class SendConfirmationToCustomer implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        long tripId = (Long) execution.getVariable("tripDataId");
        long taxiId = (Long) execution.getVariable("taxiId");
        long customerId = (Long) execution.getVariable("customerId");
        String startingpoint = (String) execution.getVariable("startingpoint");
        String endpoint = (String) execution.getVariable("endpoint");
        Double kilometers = (Double) execution.getVariable("kilometers");

        TripDataMessage messageObject = new TripDataMessage();
        messageObject.setTripId(tripId);
        messageObject.setTaxiId(taxiId);
        messageObject.setCustomerId(customerId);
        messageObject.setStartingpoint(startingpoint);
        messageObject.setEndpoint(endpoint);
        messageObject.setKilometers(kilometers);
        messageObject.setText("Hereby we confirm your booked trip! Please find the details below: ");

        MessageQueue mq = new MessageQueue();
        Message message = mq.createSession().createTextMessage(messageObject.toXml());
        mq.sendMessageToQueue("toCustomer", message);
    }
}
