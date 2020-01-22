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

    private static final String HOST = "smtp.googlemail.com";
    private static final String USER = "bernd.camunda@gmail.com";
    private static final String PWD = "12345camunda";
    private static final Integer PORT = 465;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Email email = new SimpleEmail();
        String studiengang = (String) delegateExecution.getVariable("studiengangName");
        String nachname = (String) delegateExecution.getVariable("nachname");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengang);
        LocalDate date = studiengangEntity.getZulassungszeitraum();

        email.setCharset("utf-8");
        email.setHostName(HOST);
        email.setAuthentication(USER, PWD);
        email.setSmtpPort(PORT);
        email.setSSLOnConnect(true);

        email.setFrom("noreply@learnbpm.org");
        email.setSubject("Hochschule Riedtal");

        email.setMsg("Sehr geehrte/r " + nachname + "\nUns liegen wichtige Dokumente noch nicht vor. Bitte bis zum:\n "+ date + " nachreichen. \nIhr Team der Hochschule Riedberg");

        email.addTo("bernd.camunda@gmail.com");

        email.send();
    }
}
