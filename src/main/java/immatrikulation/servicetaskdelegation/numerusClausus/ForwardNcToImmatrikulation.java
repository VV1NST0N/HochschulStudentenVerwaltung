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
        Map<String, Double> map = (Map<String, Double>) delegateExecution.getVariable("ccsOfAllCourses");
        if (map == null){
            variables.put("studiengaengeNC", "noNcsSend");
        }else{
            variables.put("studiengaengeNc", map.keySet());
        }

        RuntimeService rtm = delegateExecution.getProcessEngineServices().getRuntimeService();

        //TODO send different Maps for different approval time ranges
        rtm.createMessageCorrelation("studiengaengeNc")
                .correlateAllWithResult();


        variables.clear();
    }
}
