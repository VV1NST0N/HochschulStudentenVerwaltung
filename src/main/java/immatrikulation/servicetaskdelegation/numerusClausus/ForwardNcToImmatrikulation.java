package immatrikulation.servicetaskdelegation.numerusClausus;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class ForwardNcToImmatrikulation implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("studiengaengeNc", true);

        RuntimeService rtm = delegateExecution.getProcessEngineServices().getRuntimeService();

        rtm.createMessageCorrelation("studiengaengeNc")
                .correlateAllWithResult();

        variables.clear();
    }
}
