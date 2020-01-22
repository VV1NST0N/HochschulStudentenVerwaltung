package immatrikulation.servicetaskdelegation.applicantRegistration;

import dataAccess.UnterlagenDAO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.LinkedHashMap;
import java.util.Map;

public class CheckApplication implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Boolean> unterlagenBool = new LinkedHashMap<String, Boolean>();
        unterlagenBool.put("hochschulzeugnis", (Boolean) delegateExecution.getVariable("hochschulzeugnis"));
        unterlagenBool.put("krankenversicherung", (Boolean) delegateExecution.getVariable("krankenversicherung"));
        unterlagenBool.put("immatrikulationsantrag", (Boolean) delegateExecution.getVariable("immatrikulationsantrag"));
        unterlagenBool.put("bewerbungsschreiben", (Boolean) delegateExecution.getVariable("bewerbungsschreiben"));
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
        unterlagenDAO.updateUnterlagen(unterlagenId, unterlagenBool);
    }
}
