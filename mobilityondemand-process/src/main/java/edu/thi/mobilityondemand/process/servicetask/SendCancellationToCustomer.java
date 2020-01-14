// Sandro Käppner

package edu.thi.mobilityondemand.process.servicetask;

import edu.thi.mobilityondemand.process.message.CancellationMessage;
import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.jms.Message;

public class SendCancellationToCustomer implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long customerId = (Long) execution.getVariable("customerId");

        CancellationMessage messageObject = new CancellationMessage(customerId);

        MessageQueue mq = new MessageQueue();
        Message message = mq.createSession().createTextMessage(messageObject.toXml());
        mq.sendMessageToQueue("toCustomer", message);
    }
}
