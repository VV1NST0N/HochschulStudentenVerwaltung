package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import helper.DateConverter;
import org.camunda.bpm.dmn.engine.impl.type.DateDataTypeTransformer;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.ClockUtil;
import org.camunda.bpm.model.bpmn.instance.TimeDate;
import org.camunda.bpm.model.cmmn.impl.instance.TimerEventImpl;
import org.camunda.bpm.model.cmmn.instance.TimerEvent;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;

import java.time.LocalDate;
import java.util.Date;

public class GetTimeOfAdmission implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang("Mathematik");

        String admissionDate = studiengangEntity.getZulassungszeitraum().toString() + "T00:00:00";
        String paymendDate = studiengangEntity.getZahlungszeitraum().minusDays(14).toString() + "T00:00:00";
        String admissionDatePrior = studiengangEntity.getZulassungszeitraum().minusDays(20).toString() +  "T00:00:00";
        delegateExecution.setVariable("zulassungszeitraum", admissionDate);
        delegateExecution.setVariable("zulassungszeitraumVorher", admissionDatePrior);
        delegateExecution.setVariable("zahlungsZeitraum", paymendDate);
    }
}
