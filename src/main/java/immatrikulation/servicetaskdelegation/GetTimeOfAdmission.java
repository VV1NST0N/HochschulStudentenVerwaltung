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
        ManagementService managementService = delegateExecution.getProcessEngineServices().getManagementService();
        System.out.println(delegateExecution.getId());
        String admissionDate = studiengangEntity.getZulassungszeitraum().toString() + "T00:00:00";
        delegateExecution.setVariable("zulassungszeitraum", admissionDate);
    }
}
