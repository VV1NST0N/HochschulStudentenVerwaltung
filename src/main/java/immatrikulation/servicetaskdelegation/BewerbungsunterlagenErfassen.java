package immatrikulation.servicetaskdelegation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BewerbungsunterlagenErfassen implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //TODO implement File Upload
        System.out.println("Unterlagen erfasst");
    }
}
