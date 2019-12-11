package immatrikulation.servicetaskdelegation;

import dataAccess.UnterlagenDAO;
import entities.BewerbungsunterlagenEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckUnterlagen implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
        BewerbungsunterlagenEntity unterlagenEntity = unterlagenDAO.getUnterlagenById(unterlagenId);
        Boolean vollstaendig = checkVollständigkeit(unterlagenEntity);
        Boolean digitaleDokumente = checkDigitaleDokumente(unterlagenEntity);
        Boolean hochschulreife = checkHochschulreife(unterlagenEntity);
        delegateExecution.setVariable("vollstaendig", vollstaendig);
        delegateExecution.setVariable("digitaleDokumente", digitaleDokumente);
        delegateExecution.setVariable("hochschulreife", hochschulreife);

        delegateExecution.setVariable("hochschulzeugnis", false);
        delegateExecution.setVariable("krankenversicherung", false);
        delegateExecution.setVariable("immatrikulationsantrag", false);
        delegateExecution.setVariable("bewerbungsschreiben", false);

    }

    private Boolean checkHochschulreife(BewerbungsunterlagenEntity unterlagenEntity) {
        if(unterlagenEntity.getHochschulreife().equals(true)){
            return true;
        }else{
            return false;
        }
    }

    private Boolean checkDigitaleDokumente(BewerbungsunterlagenEntity unterlagenEntity) {
        if (unterlagenEntity.getBewerbungsschreiben().equals(true) && unterlagenEntity.getImmatrikulationsantrag().equals(true) && unterlagenEntity.getKrankenversicherung().equals(true)){
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkVollständigkeit(BewerbungsunterlagenEntity unterlagenEntity) {
        if (unterlagenEntity.getBewerbungsschreiben().equals(true) && unterlagenEntity.getHochschulreife().equals(true) && unterlagenEntity.getImmatrikulationsantrag().equals(true) && unterlagenEntity.getKrankenversicherung().equals(true)){
            return true;
        }else {
            return false;
        }
    }


}
