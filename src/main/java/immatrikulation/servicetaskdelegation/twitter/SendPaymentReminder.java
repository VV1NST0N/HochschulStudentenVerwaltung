package immatrikulation.servicetaskdelegation.twitter;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import processingTasks.sendTasks.SendToTwitter;

import java.time.LocalDate;

public class SendPaymentReminder implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang("Informatik");
        Double random = Math.random();
        LocalDate date = studiengangEntity.getZahlungszeitraum();
        String content = random + "\nDie Zahlungsfrist für das Sommersemster 2020 ist noch bis zum " + date.toString() + " offen. " +
                "\nBitte denken Sie daran Ihre Überweisung bis zum Ende der Frist zu tätigen.";
        SendToTwitter sendToTwitter = new SendToTwitter();
        sendToTwitter.doSendTwitter(content);
    }
}
