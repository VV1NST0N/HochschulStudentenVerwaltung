package immatrikulation.servicetaskdelegation.payment;

import dataAccess.ImmatrikulationsAntragDao;
import entities.ImmatrikulationsverfahrenStatusEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ChangePaymentByApplicant implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        Boolean status = (Boolean) delegateExecution.getVariable("zahlungsStatus");
        if(bewerberId != null && unterlagenId != null){
            ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
            ImmatrikulationsverfahrenStatusEntity immat = immatrikulationsAntragDao.getImmatByBewerber(bewerberId, unterlagenId);
            immat.setZahlungStatus(status);
            immatrikulationsAntragDao.updateEntity(immat);
        }

    }
}
