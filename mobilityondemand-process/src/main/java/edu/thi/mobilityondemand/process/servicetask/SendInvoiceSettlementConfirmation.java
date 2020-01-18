/**
 * @author Daniel Schels
 */

package edu.thi.mobilityondemand.process.servicetask;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendInvoiceSettlementConfirmation implements JavaDelegate{

	 @Override
	    public void execute(DelegateExecution execution) throws Exception {
	        String invoiceid = (String) execution.getVariable("invoiceid");

	        System.out.println("Invoice with ID " + invoiceid + " is settled");

	        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
	        runtimeService.createMessageCorrelation("invoice_settled").setVariable("invoiceid", invoiceid).correlate();
	    }

}
