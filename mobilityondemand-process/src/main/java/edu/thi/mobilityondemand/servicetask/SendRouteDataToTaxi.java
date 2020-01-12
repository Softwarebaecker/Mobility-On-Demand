package edu.thi.mobilityondemand.servicetask;

import edu.thi.mobilityondemand.message.TripDataToTaxiMessage;
import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
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

        TripDataToTaxiMessage messageObject = new TripDataToTaxiMessage();
        messageObject.setTripId(tripId);
        messageObject.setTaxiId(taxiId);
        messageObject.setCustomerId(customerId);
        messageObject.setStartingpoint(startingpoint);
        messageObject.setEndpoint(endpoint);
        messageObject.setKilometers(kilometers);

        JAXBContext jc = JAXBContext.newInstance(TripDataToTaxiMessage.class);
        Marshaller marshaller = jc.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(messageObject, stringWriter);

        MessageQueue mq = new MessageQueue();
        Message message = mq.createSession().createTextMessage(stringWriter.toString());
        mq.sendMessageToQueue("toTaxi", message);
    }
}
