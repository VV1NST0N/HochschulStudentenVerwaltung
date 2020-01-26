package immatrikulation.servicetaskdelegation.applicantRegistration;

import dataAccess.BewerberDAO;
import dataAccess.ImmatrikulationsAntragDao;
import dataAccess.UnterlagenDAO;
import entities.BewerberEntity;
import entities.BewerbungsunterlagenEntity;
import entities.ImmatrikulationsverfahrenStatusEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class DeleteApplicant implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");

        ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();

        BewerbungsunterlagenEntity bewerbungsunterlagenEntity = unterlagenDAO.getEntryById(unterlagenId);
        ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity = immatrikulationsAntragDao.getImmatByBewerber(bewerberId, unterlagenId);

        BewerberDAO bewerberDAO = new BewerberDAO();
        BewerberEntity bewerberEntity = bewerberDAO.getEntryById(bewerberId);

        immatrikulationsAntragDao.deleteEntry(immatrikulationsverfahrenStatusEntity);
        unterlagenDAO.deleteEntry(bewerbungsunterlagenEntity);
        bewerberDAO.deleteEntry(bewerberEntity);

    }
}
