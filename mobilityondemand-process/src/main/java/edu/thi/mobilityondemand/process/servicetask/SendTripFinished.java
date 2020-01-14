/**
 * @auther Sandro Käppner
 */

package edu.thi.mobilityondemand.process.servicetask;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class SendTripFinished implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long customerId = (Long) execution.getVariable("customerId");
        Long tripId = (Long) execution.getVariable("tripDataId");

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerId", customerId);
        variables.put("tripId", tripId);

        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation("trip_finished").setVariables(variables).correlate();
    }
}
