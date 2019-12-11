package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ZulassungPrüfen implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String studiengangName = (String) delegateExecution.getVariable("studiengangName");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengangName);
        Boolean zulassung = (Boolean) studiengangEntity.getVorraussetzungTest();
        delegateExecution.setVariable("zulassungstest", zulassung);
        if(studiengangEntity.getNumerusClaususNote() != null && studiengangEntity.getNumerusClaususNote()>0){
            delegateExecution.setVariable("nc", true);
        }else{
            delegateExecution.setVariable("nc", false);
        }
    }
}
