package edu.thi.mobilityondemand.servicetask;

import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.jms.Message;

public class SendRatingRequest implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        long customerId = (long) delegateExecution.getVariable("customerId");

        MessageQueue mq = new MessageQueue();
        Message message = mq.createSession().createMessage();
        message.setLongProperty("customerId", customerId);
        mq.sendMessageToQueue("RequestRatingQueue", message);
    }
}
