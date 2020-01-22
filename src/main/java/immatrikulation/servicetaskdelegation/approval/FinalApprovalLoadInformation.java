package immatrikulation.servicetaskdelegation.approval;

import dataAccess.ImmatrikulationsAntragDao;
import entities.ImmatrikulationsverfahrenStatusEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FinalApprovalLoadInformation implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
        ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity = immatrikulationsAntragDao.getImmatByBewerber(bewerberId,unterlagenId);
        if (immatrikulationsverfahrenStatusEntity.getUnterlagenVollstaendig()){
            delegateExecution.setVariable("vollstaendigeUnterlagen", "Unterlagen sind vollständig.");
        }else {
            delegateExecution.setVariable("vollstaendigeUnterlagen", "Unterlagen sind vollständig.");
        }
        if (immatrikulationsverfahrenStatusEntity.getZahlungStatus()){
            delegateExecution.setVariable("zahlungErolgt", "Student hat die Studiengebühren bezahlt.");
        }else {
            delegateExecution.setVariable("zahlungErolgt", "Student hat die Studiengebühren nicht bezahlt.");
        }
        if (immatrikulationsverfahrenStatusEntity.getZulassungStatus()){
            delegateExecution.setVariable("zulassungErteilt", "Student erfüllt die Anforderungen zur Zulassung.");
        }else{
            delegateExecution.setVariable("zulassungErteilt", "Student erfüllt die Anforderungen zur Zulassung nicht.");
        }
        delegateExecution.setVariable("bewerbungsEingang", "Bewerber hat seine Anmeldung am " + immatrikulationsverfahrenStatusEntity.getBewerbungseingang().toString() + " abgeschickt.");

    }
}
