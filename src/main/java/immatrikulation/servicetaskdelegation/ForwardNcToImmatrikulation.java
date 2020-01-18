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

        Map<String, Object> variables = new HashMap<String, Object>();
        Map<String, Long> map = (Map<String, Long>) delegateExecution.getVariable("ccsOfAllCourses");
        variables.put("nc", map);

        RuntimeService rtm = delegateExecution.getProcessEngineServices().getRuntimeService();

        rtm.startProcessInstanceByMessage("ncMap",variables);

        variables.clear();
    }
}
