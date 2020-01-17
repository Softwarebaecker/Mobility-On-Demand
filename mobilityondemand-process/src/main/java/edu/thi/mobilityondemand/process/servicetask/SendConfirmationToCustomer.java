/**
 * @author Sandro Käppner
 */

package edu.thi.mobilityondemand.process.servicetask;

import edu.thi.mobilityondemand.process.message.TripDataMessage;
import edu.thi.mobilityondemand.process.message.TripDataMessageToCustomer;
import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.jms.Message;
import java.util.Date;

public class SendConfirmationToCustomer implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        long tripId = (Long) execution.getVariable("tripDataId");
        long customerId = (Long) execution.getVariable("customerId");
        String startingpoint = (String) execution.getVariable("startingpoint");
        String endpoint = (String) execution.getVariable("endpoint");
        Double kilometers = (Double) execution.getVariable("kilometers");
        Date startDate = (Date) execution.getVariable("startDate");

        TripDataMessage messageObject = new TripDataMessageToCustomer(tripId, startingpoint, endpoint, kilometers, customerId, startDate);

        MessageQueue mq = new MessageQueue();
        Message message = mq.createSession().createTextMessage(messageObject.toXml());
        mq.sendMessageToQueue("toCustomer", message);
    }
}
