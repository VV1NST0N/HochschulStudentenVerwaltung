package immatrikulation.servicetaskdelegation.approval;

import dataAccess.ImmatrikulationsAntragDao;
import dataAccess.UnterlagenDAO;
import entities.BewerbungsunterlagenEntity;
import entities.ImmatrikulationsverfahrenStatusEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckDocuments implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
        BewerbungsunterlagenEntity unterlagenEntity = unterlagenDAO.getUnterlagenById(unterlagenId);
        ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
        ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity = immatrikulationsAntragDao.getImmatByBewerber(bewerberId, unterlagenId);
        Boolean vollstaendig = checkVollständigkeit(unterlagenEntity, immatrikulationsverfahrenStatusEntity );
        System.out.println("Boolean vollsätndig:" + vollstaendig);
        delegateExecution.setVariable("vollstaendig", vollstaendig);
    }

    private Boolean checkVollständigkeit(BewerbungsunterlagenEntity unterlagenEntity, ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity) {
        if (unterlagenEntity.getBewerbungsschreiben().equals(true) && unterlagenEntity.getHochschulreife().equals(true) && unterlagenEntity.getImmatrikulationsantrag().equals(true) && unterlagenEntity.getKrankenversicherung().equals(true) && unterlagenEntity.getPersonalausweis().equals(true)){
            ImmatrikulationsAntragDao immatrikulationsverfahrenDao = new ImmatrikulationsAntragDao();
            immatrikulationsverfahrenStatusEntity.setUnterlagenVollstaendig(true);
            immatrikulationsverfahrenDao.updateEntity(immatrikulationsverfahrenStatusEntity);
            return true;
        }else {
            return false;
        }
    }


}
