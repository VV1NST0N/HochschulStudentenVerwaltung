package immatrikulation.servicetaskdelegation.sendMail;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import helper.DateConverter;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;

public class SendReminderEmail implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String studiengang = (String) delegateExecution.getVariable("studiengangName");
        String nachname = (String) delegateExecution.getVariable("nachname");
        String gender = (String) delegateExecution.getVariable("gender");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengang);
        LocalDate date = studiengangEntity.getZulassungszeitraum();

        String mailBody = "\nUns liegen wichtige Dokumente noch nicht vor. \nBitte reichen Sie diese bis zum: "+ date + " nach, sodass wir Ihre Bewerbung schnellstmöglich abschließen können.";

        SendMailTemplateClass sendMailTemplateClass = new SendMailTemplateClass();
        sendMailTemplateClass.doSendMail(gender,nachname,mailBody);
    }
}
