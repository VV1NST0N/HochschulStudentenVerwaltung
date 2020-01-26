package immatrikulation.servicetaskdelegation.sendMail;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;

public class SendPaymentReminderMail implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String studiengang = (String) delegateExecution.getVariable("studiengangName");
        String nachname = (String) delegateExecution.getVariable("nachname");
        String gender = (String) delegateExecution.getVariable("gender");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengang);
        LocalDate date = studiengangEntity.getZulassungszeitraum();
        String mailBody = "\nBitte denken Sie daran bis zum " + date.toString() + " Ihre Studiengebühren zu überweisen.";

        SendMailTemplateClass sendMailTemplateClass = new SendMailTemplateClass();
        sendMailTemplateClass.doSendMail(gender, nachname,mailBody);
    }
}
