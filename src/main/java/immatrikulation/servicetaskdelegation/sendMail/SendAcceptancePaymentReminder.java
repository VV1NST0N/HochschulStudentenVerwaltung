package immatrikulation.servicetaskdelegation.sendMail;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import processingTasks.sendTasks.SendMailTemplateClass;

import java.time.LocalDate;

public class SendAcceptancePaymentReminder implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String studiengang = (String) delegateExecution.getVariable("studiengangName");
        String nachname = (String) delegateExecution.getVariable("nachname");
        String gender = (String) delegateExecution.getVariable("gender");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengang);
        LocalDate date = studiengangEntity.getZulassungszeitraum();
        String mailBody = "\nSie haben die Zulassungsvoraussetzungen für den Studiengang " + studiengang + " erfüllt. Bitte denken Sie daran die Studiengebühren zu überweisen.";

        SendMailTemplateClass sendMailTemplateClass = new SendMailTemplateClass();
        sendMailTemplateClass.doSendMail(gender, nachname,mailBody);
    }
}
