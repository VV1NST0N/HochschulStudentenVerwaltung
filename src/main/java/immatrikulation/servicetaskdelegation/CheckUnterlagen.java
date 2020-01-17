package immatrikulation.servicetaskdelegation;

import dataAccess.ImmatrikulationsAntragDao;
import dataAccess.UnterlagenDAO;
import entities.BewerbungsunterlagenEntity;
import entities.ImmatrikulationsverfahrenStatusEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckUnterlagen implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
        BewerbungsunterlagenEntity unterlagenEntity = unterlagenDAO.getUnterlagenById(unterlagenId);
        Boolean vollstaendig = checkVollständigkeit(unterlagenEntity);
        
        delegateExecution.setVariable("vollstaendig", vollstaendig);
        if(vollstaendig.equals(false)){
            delegateExecution.setVariable("hochschulzeugnis", false);
            delegateExecution.setVariable("krankenversicherung", false);
            delegateExecution.setVariable("immatrikulationsantrag", false);
            delegateExecution.setVariable("bewerbungsschreiben", false);
        }
    }

    private Boolean checkVollständigkeit(BewerbungsunterlagenEntity unterlagenEntity ) {
        if (unterlagenEntity.getBewerbungsschreiben().equals(true) && unterlagenEntity.getHochschulreife().equals(true) && unterlagenEntity.getImmatrikulationsantrag().equals(true) && unterlagenEntity.getKrankenversicherung().equals(true)){
            return true;
        }else {
            return false;
        }
    }

    private Boolean checkVollständigkeit2(BewerbungsunterlagenEntity unterlagenEntity, ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity) {
        if (unterlagenEntity.getBewerbungsschreiben().equals(true) && unterlagenEntity.getHochschulreife().equals(true) && unterlagenEntity.getImmatrikulationsantrag().equals(true) && unterlagenEntity.getKrankenversicherung().equals(true)){
            ImmatrikulationsAntragDao immatrikulationsverfahrenDao = new ImmatrikulationsAntragDao();
            immatrikulationsverfahrenStatusEntity.setUnterlagenVollstaendig(true);
            immatrikulationsverfahrenDao.insertDocumentsComplete(immatrikulationsverfahrenStatusEntity);
            return true;
        }else {
            return false;
        }
    }


}
