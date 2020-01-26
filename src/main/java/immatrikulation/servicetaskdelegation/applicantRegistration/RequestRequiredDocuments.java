package immatrikulation.servicetaskdelegation.applicantRegistration;

import dataAccess.UnterlagenDAO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestRequiredDocuments implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        FileValue krankenversicherung_doc = delegateExecution.getVariableTyped("KRANKENVERSICHERUNG_DOC");
        FileValue immatrikulationsantrag_doc = delegateExecution.getVariableTyped("IMMATRIKULATIONSANTRAG_DOC");
        FileValue bewerbungsschreiben_doc = delegateExecution.getVariableTyped("BEWERBUNGSSCHREIBEN_DOC");
        FileValue hochschulzeugnis_doc = delegateExecution.getVariableTyped("HOCHSCHULZEUGNIS_DOC");
        FileValue personalausweis_doc = delegateExecution.getVariableTyped("PERSONALAUSWEIS_DOC");
        Map<String, byte[]> unterlagen = new LinkedHashMap<String, byte[]>();
        InputStream krankenversicherung = krankenversicherung_doc.getValue();
        InputStream bewerbungsschreiben = bewerbungsschreiben_doc.getValue();
        InputStream immatrikulationsantrag = immatrikulationsantrag_doc.getValue();
        InputStream hochschulzeugnis = hochschulzeugnis_doc.getValue();
        InputStream personalausweis = personalausweis_doc.getValue();

        for (String s: unterlagen.keySet()) {
            System.out.println(s);
            System.out.println(unterlagen.get(s));
        }

        unterlagen.put("krankenversicherung", new byte[krankenversicherung.available()]);
        unterlagen.put("bewerbungsschreiben", new byte[bewerbungsschreiben.available()]);
        unterlagen.put("immatrikulationsantrag", new byte[immatrikulationsantrag.available()]);
        unterlagen.put("hochschulzeugnis", new byte[hochschulzeugnis.available()]);
        unterlagen.put("personalausweis", new byte[personalausweis.available()]);
        updateUnterlagen(unterlagenId, unterlagen);

        delegateExecution.setVariable("hochschulzeugnis", false);
        delegateExecution.setVariable("krankenversicherung", false);
        delegateExecution.setVariable("immatrikulationsantrag", false);
        delegateExecution.setVariable("bewerbungsschreiben", false);
        delegateExecution.setVariable("personalausweis", false);
        delegateExecution.setVariable("korrektheitDaten", false);

    }


    private void updateUnterlagen(Integer unterlagenId, Map<String, byte[]> unterlagen) {
        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
        unterlagenDAO.updateUnterlagenLoc(unterlagenId, unterlagen);
    }
}
