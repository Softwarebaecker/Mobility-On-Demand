package edu.thi.mobilityondemand.process.servicetask;

import edu.thi.mobilityondemand.process.message.TripDataMessage;
import edu.thi.mobilityondemand.process.message.TripDataMessageToTaxi;
import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.jms.Message;
import java.util.Date;

public class SendRouteDataToTaxi implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        long tripId = (Long) delegateExecution.getVariable("tripDataId");
        long taxiId = (Long) delegateExecution.getVariable("taxiId");
        long customerId = (Long) delegateExecution.getVariable("customerId");
        String startingpoint = (String) delegateExecution.getVariable("startingpoint");
        String endpoint = (String) delegateExecution.getVariable("endpoint");
        Double kilometers = (Double) delegateExecution.getVariable("kilometers");
        Date startDate = (Date) delegateExecution.getVariable("startDate");

        TripDataMessage messageObject = new TripDataMessageToTaxi(taxiId, tripId, startingpoint, endpoint, kilometers, customerId, startDate);

        MessageQueue mq = new MessageQueue();
        Message message = mq.createSession().createTextMessage(messageObject.toXml());
        mq.sendMessageToQueue("toTaxi", message);
    }
}
