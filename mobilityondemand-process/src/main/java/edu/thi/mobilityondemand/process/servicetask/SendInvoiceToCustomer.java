/**
 * @author Daniel Schels
 */

package edu.thi.mobilityondemand.process.servicetask;


import edu.thi.mobilityondemand.process.message.InvoiceMessage;
import edu.thi.mobilityondemand.process.queue.MessageQueue;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import java.io.StringWriter;

public class SendInvoiceToCustomer implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        InvoiceMessage invoice = (InvoiceMessage) execution.getVariable("Invoice");

        JAXBContext jc = JAXBContext.newInstance(InvoiceMessage.class);
        Marshaller marshaller = jc.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(invoice, stringWriter);

        MessageQueue mq = new MessageQueue();
        TextMessage tm = mq.createSession().createTextMessage();
        tm.setText(stringWriter.toString());
        mq.sendMessageToQueue("InvoicePrintingQueue", tm);

    }
}
