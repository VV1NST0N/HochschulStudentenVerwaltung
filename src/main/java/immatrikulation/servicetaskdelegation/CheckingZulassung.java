package immatrikulation.servicetaskdelegation;

import dataAccess.ImmatrikulationsAntragDao;
import entities.ImmatrikulationsverfahrenStatusEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckingZulassung implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
       Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
       Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");

        ImmatrikulationsAntragDao immatrikulationsAntragDao= new ImmatrikulationsAntragDao();
        ImmatrikulationsverfahrenStatusEntity immat = immatrikulationsAntragDao.getImmatByBewerber(bewerberId, unterlagenId);

        if (immat.getUnterlagenVollstaendig() == true){
            immat.setZulassungStatus(true);
            immatrikulationsAntragDao.updateImmat(immat);
        }
    }
}
