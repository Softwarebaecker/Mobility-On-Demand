/**
 * @auther Sandro Käppner
 */

package edu.thi.mobilityondemand.process.servicetask;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendRatingToAggregator implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long ratingId = (Long) execution.getVariable("ratingId");

        System.out.println("New bad rating with ID: " + ratingId + ". Sending Message...");

        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation("new_bad_rating").setVariable("ratingId", ratingId).correlate();
    }
}
