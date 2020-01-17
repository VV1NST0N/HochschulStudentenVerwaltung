package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;

public class GetTimeOfAdmission implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang("Mathematik");
        LocalDate admissionDate = studiengangEntity.getZulassungszeitraum();
        delegateExecution.setVariable("zulassungszeitraum", admissionDate);
    }
}
