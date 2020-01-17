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
        Boolean nc = (Boolean) studiengangEntity.getNcNotwendig();
        delegateExecution.setVariable("zulassungstest", zulassung);
        delegateExecution.setVariable("nc", nc);
    }
}
