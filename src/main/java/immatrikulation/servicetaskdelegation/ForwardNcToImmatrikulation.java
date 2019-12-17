package immatrikulation.servicetaskdelegation;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
public class ForwardNcToImmatrikulation implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // TODO get from process later
        //Integer numerusClausus = (Integer) execution.getVariable("nc");
        Integer numerusClausus = 2;

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("nc", numerusClausus);

        RuntimeService rtm = delegateExecution.getProcessEngineServices().getRuntimeService();

        rtm.startProcessInstanceByMessage("NcSenden",variables);

        variables.clear();
    }


}
