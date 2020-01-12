/*
 * Daniel Schels
 */


package edu.thi.mobilityondemand.servicetask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RetrieveCustomerGroup implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String customerGroup = "student"; // TODO get value from customer DB
		
		execution.setVariable("customerGroup", customerGroup);
		
		System.out.println("Retrieved Customer Group: " + customerGroup);
	}

}
