package immatrikulation.servicetaskdelegation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FehlendeUnterlagenAnfordern implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //TODO Via Mail and Userform
        // TODO enter new Files
    }
}
