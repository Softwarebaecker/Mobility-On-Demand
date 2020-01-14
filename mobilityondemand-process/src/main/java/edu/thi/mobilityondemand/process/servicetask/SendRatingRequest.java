// Sandro Käppner

package edu.thi.mobilityondemand.process.servicetask;

import edu.thi.mobilityondemand.process.message.RatingRequestMessage;
import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.jms.Message;

public class SendRatingRequest implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long customerId = (Long) execution.getVariable("customerId");
        Long tripId = (Long) execution.getVariable("tripId");

        RatingRequestMessage messageObject = new RatingRequestMessage(customerId, tripId);

        MessageQueue mq = new MessageQueue();
        Message message = mq.createSession().createTextMessage(messageObject.toXml());
        mq.sendMessageToQueue("toCustomer", message);
    }
}
