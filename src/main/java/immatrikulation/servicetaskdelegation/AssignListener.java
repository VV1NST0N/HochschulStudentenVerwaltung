package immatrikulation.servicetaskdelegation;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class AssignListener implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {
        String userID = (String) delegateTask.getVariable("assignee");
        delegateTask.setAssignee(userID);
    }
}
