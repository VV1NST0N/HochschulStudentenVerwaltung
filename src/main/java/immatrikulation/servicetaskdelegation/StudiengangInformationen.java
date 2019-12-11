package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import helper.DateConverter;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.util.Date;

public class StudiengangInformationen implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang("Maschinenbau");
        Integer freiePlätze = studiengangEntity.getStudiengangFreiePlaetze();
        Date fristEnde = DateConverter.convertToDate(studiengangEntity.getZulassungszeitraum());
        delegateExecution.setVariable("fristEnde", fristEnde);
        delegateExecution.setVariable("freieplaetze", freiePlätze);
    }
}
