package immatrikulation.servicetaskdelegation.applicantRegistration;

import dataAccess.UnterlagenDAO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.LinkedHashMap;
import java.util.Map;


public class CheckApplication implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Boolean> documentsMap = new LinkedHashMap<String, Boolean>();
        documentsMap.put("hochschulzeugnis", (Boolean) delegateExecution.getVariable("hochschulzeugnis"));
        documentsMap.put("krankenversicherung", (Boolean) delegateExecution.getVariable("krankenversicherung"));
        documentsMap.put("immatrikulationsantrag", (Boolean) delegateExecution.getVariable("immatrikulationsantrag"));
        documentsMap.put("bewerbungsschreiben", (Boolean) delegateExecution.getVariable("bewerbungsschreiben"));
        documentsMap.put("personalausweis", (Boolean) delegateExecution.getVariable("personalausweis"));
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
        unterlagenDAO.updateUnterlagen(unterlagenId, documentsMap);
    }
}
