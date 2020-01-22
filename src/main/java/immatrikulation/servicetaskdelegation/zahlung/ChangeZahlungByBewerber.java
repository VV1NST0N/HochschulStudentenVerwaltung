package immatrikulation.servicetaskdelegation.zahlung;

import dataAccess.FremdSystemDao;
import dataAccess.ImmatrikulationsAntragDao;
import entities.ImmatrikulationsverfahrenStatusEntity;
import entities.ZahlungsstatusfremdsystemEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ChangeZahlungByBewerber implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        Boolean status = (Boolean) delegateExecution.getVariable("zahlungsStatus");
        ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
        ImmatrikulationsverfahrenStatusEntity immat = immatrikulationsAntragDao.getImmatByBewerber(bewerberId, unterlagenId);
        immat.setZahlungStatus(status);
        immatrikulationsAntragDao.updateEntity(immat);
    }
}
