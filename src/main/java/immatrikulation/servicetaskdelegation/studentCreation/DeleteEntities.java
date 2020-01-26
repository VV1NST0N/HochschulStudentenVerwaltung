package immatrikulation.servicetaskdelegation.studentCreation;

import dataAccess.BewerberDAO;
import dataAccess.ImmatrikulationsAntragDao;
import dataAccess.UnterlagenDAO;
import entities.BewerbungsunterlagenEntity;
import entities.ImmatrikulationsverfahrenStatusEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class DeleteEntities implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");

        ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();

        BewerbungsunterlagenEntity bewerbungsunterlagenEntity = unterlagenDAO.getEntryById(unterlagenId);
        ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity = immatrikulationsAntragDao.getImmatByBewerber(bewerberId, unterlagenId);

        immatrikulationsAntragDao.deleteEntry(immatrikulationsverfahrenStatusEntity);
        unterlagenDAO.deleteEntry(bewerbungsunterlagenEntity);

    }
}
