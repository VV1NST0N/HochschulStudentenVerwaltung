package immatrikulation.servicetaskdelegation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
public class ForwardNcToImmatrikulation implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Map<String, Object> variables = new HashMap<String, Object>();
        Map<String, Double> map = (Map<String, Double>) delegateExecution.getVariable("ccsOfAllCourses");
        if (map == null){
            variables.put("studiengaengeNC", "noNcsSend");
        }else{
            variables.put("studiengaengeNc", map.keySet());
        }

        RuntimeService rtm = delegateExecution.getProcessEngineServices().getRuntimeService();

        //TODO send different Maps for different admission time ranges
        List<MessageCorrelationResult> results = rtm
                .createMessageCorrelation("studiengaengeNc")
                .correlateAllWithResult();


        variables.clear();
    }
}
