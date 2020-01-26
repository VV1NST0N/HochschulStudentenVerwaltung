package immatrikulation.servicetaskdelegation.sendMail;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;

public class SendAcceptanceMail implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String studiengang = (String) delegateExecution.getVariable("studiengangName");
        String nachname = (String) delegateExecution.getVariable("nachname");
        String gender = (String) delegateExecution.getVariable("gender");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengang);
        LocalDate date = studiengangEntity.getZulassungszeitraum();
        String mailBody = "\nHerzlichen Glückwunsch! Sie wurden für den Studiengang " + studiengang + " zugelassen.";

        SendMailTemplateClass sendMailTemplateClass = new SendMailTemplateClass();
        sendMailTemplateClass.doSendMail(gender, nachname,mailBody);
    }
}
