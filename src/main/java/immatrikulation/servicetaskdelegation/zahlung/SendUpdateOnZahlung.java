package immatrikulation.servicetaskdelegation.zahlung;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendUpdateOnZahlung implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("zahlungErfolgt", true);
        RuntimeService rtm = delegateExecution.getProcessEngineServices().getRuntimeService();
        List<MessageCorrelationResult> results = rtm
                .createMessageCorrelation("zahlungErfolgt")
                .correlateAllWithResult();

        variables.clear();
    }
}
