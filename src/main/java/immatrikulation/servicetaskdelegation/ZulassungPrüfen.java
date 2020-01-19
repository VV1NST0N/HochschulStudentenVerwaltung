package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.List;
import java.util.Map;

public class ZulassungPrüfen implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Double> ncCourses = (Map<String, Double>) delegateExecution.getVariable("ccsOfAllCourses");
        String studiengangName = (String)  delegateExecution.getVariable("studiengangName");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengangName);
        Boolean zulassung = (Boolean) studiengangEntity.getVorraussetzungTest();
        Boolean nc = (Boolean) studiengangEntity.getNcNotwendig();

        System.out.println("Message Invokation die Erste");
        delegateExecution.setVariable("zulassungstest", zulassung);
        delegateExecution.setVariable("nc", nc);
    }
}
