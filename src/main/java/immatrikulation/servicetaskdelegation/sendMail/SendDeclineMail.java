package immatrikulation.servicetaskdelegation.sendMail;

import dataAccess.ImmatrikulationsAntragDao;
import entities.ImmatrikulationsverfahrenStatusEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendDeclineMail implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        SendMailTemplateClass sendMailTemplateClass = new SendMailTemplateClass();
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
        ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity = immatrikulationsAntragDao.getImmatByBewerber(bewerberId, unterlagenId);
        String nachname = (String) delegateExecution.getVariable("nachname");
        String gender = (String) delegateExecution.getVariable("gender");
        String mailBody = "Wir bedauern Ihnen mitteilen zu müssen, dass Ihre Bewerbung an unserer Hochschule abgelehnt wurde.\n" + getReason(immatrikulationsverfahrenStatusEntity) + "\n Bei Rückfragen können Sie sich and das Studiensekretariat wenden.";
        sendMailTemplateClass.doSendMail(gender, nachname, mailBody);
    }

    public String getReason(ImmatrikulationsverfahrenStatusEntity immat) {
        if (!immat.getUnterlagenVollstaendig()) {
            return "Leider wurden fehlende Unterlagen nicht innerhalb des dafür vorgesehenen Zeitraums nachgereicht.";
        } else if (!immat.getZulassungStatus()) {
            return "Leider wurden die Zulassungsbedingungen für den Studiengang nicht erreicht";
        } else if (!immat.getZahlungStatus()) {
            return "Leider wurde die Zahlung der Studiengebühren nicht innerhalb der vorgeschriebenen Zeit an die Hochschule Riedtal.";
        } else {
            return "";
        }
    }
}
