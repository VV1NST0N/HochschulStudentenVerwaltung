package immatrikulation.servicetaskdelegation.applicantRegistration;

import dataAccess.UnterlagenDAO;
import entities.BewerbungsunterlagenEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;
import sun.misc.IOUtils;

import java.io.*;


public class RegistrateApplicantDocuments implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        FileValue krankenversicherung_doc = delegateExecution.getVariableTyped("KRANKENVERSICHERUNG_DOC");
        FileValue immatrikulationsantrag_doc = delegateExecution.getVariableTyped("IMMATRIKULATIONSANTRAG_DOC");
        FileValue bewerbungsschreiben_doc = delegateExecution.getVariableTyped("BEWERBUNGSSCHREIBEN_DOC");
        FileValue hochschulzeugnis_doc = delegateExecution.getVariableTyped("HOCHSCHULZEUGNIS_DOC");
        FileValue personalausweis_doc = delegateExecution.getVariableTyped("PERSONALAUSWEIS_DOC");

        InputStream krankenversicherung = krankenversicherung_doc.getValue();
        InputStream bewerbungsschreiben = bewerbungsschreiben_doc.getValue();
        InputStream immatrikulationsantrag = immatrikulationsantrag_doc.getValue();
        InputStream hochschulzeugnis = hochschulzeugnis_doc.getValue();
        InputStream personalausweis = personalausweis_doc.getValue();


        byte[] krankenBytes = IOUtils.readNBytes(krankenversicherung, krankenversicherung.available());
        byte[] bewerbungsBytes = IOUtils.readNBytes(bewerbungsschreiben, bewerbungsschreiben.available());
        byte[] immatBytes = IOUtils.readNBytes(immatrikulationsantrag, immatrikulationsantrag.available());
        byte[] hochschulBytes = IOUtils.readNBytes(hochschulzeugnis, hochschulzeugnis.available());
        byte[] persoBytes = IOUtils.readNBytes(personalausweis, personalausweis.available());

        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
        BewerbungsunterlagenEntity bewerbungsunterlagenEntity = unterlagenDAO.getEntryById(unterlagenId);
        bewerbungsunterlagenEntity.setBewerbungsschreibenLocation(krankenBytes);
        bewerbungsunterlagenEntity.setImmatrikulationsantragLocation(bewerbungsBytes);
        bewerbungsunterlagenEntity.setKrankenversicherungLocation(immatBytes);
        bewerbungsunterlagenEntity.setHochschulreifeLocation(hochschulBytes);
        bewerbungsunterlagenEntity.setPersonalausweisLocation(persoBytes);
        unterlagenDAO.updateEntity(bewerbungsunterlagenEntity);
    }
}
