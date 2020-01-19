package immatrikulation.servicetaskdelegation;

import dataAccess.UnterlagenDAO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class FehlendeUnterlagenAnfordern implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String BASE_PATH = System.getProperty("user.home") + "\\Documents\\Bewerbungsunterlagen\\";
        Integer userId = (Integer) delegateExecution.getVariable("bewerberId");
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        FileValue krankenversicherung_doc = delegateExecution.getVariableTyped("KRANKENVERSICHERUNG_DOC");
        FileValue immatrikulationsantrag_doc = delegateExecution.getVariableTyped("IMMATRIKULATIONSANTRAG_DOC");
        FileValue bewerbungsschreiben_doc = delegateExecution.getVariableTyped("BEWERBUNGSSCHREIBEN_DOC");
        FileValue hochschulzeugnis_doc = delegateExecution.getVariableTyped("HOCHSCHULZEUGNIS_DOC");
        Map<String, String> unterlagen = new LinkedHashMap<String, String>();
        InputStream krankenversicherung = krankenversicherung_doc.getValue();
        InputStream bewerbungsschreiben = bewerbungsschreiben_doc.getValue();
        InputStream immatrikulationsantrag = immatrikulationsantrag_doc.getValue();
        InputStream hochschulzeugnis = hochschulzeugnis_doc.getValue();


        String krankenversicherungName = BASE_PATH + "_krankenversicherung_" + unterlagenId + krankenversicherung_doc.getFilename();
        String bewerbungsschreibenName = BASE_PATH + "_bewerbungsschreiben_" + unterlagenId + krankenversicherung_doc.getFilename();
        String immatrikulationsantragName = BASE_PATH + "_immatrikulationsantrag_" + unterlagenId + krankenversicherung_doc.getFilename();
        String hochschulzeugnisName = BASE_PATH + "_hochschulzeugnis_" + unterlagenId + krankenversicherung_doc.getFilename();
        System.out.println("Zeugnis: " + hochschulzeugnisName);
        unterlagen.put("krankenversicherungName", krankenversicherungName);
        unterlagen.put("bewerbungsschreibenName", bewerbungsschreibenName);
        unterlagen.put("immatrikulationsantragName", immatrikulationsantragName);
        unterlagen.put("hochschulzeugnisName", hochschulzeugnisName);
        updateUnterlagen(unterlagenId, unterlagen);
        System.out.println(krankenversicherungName);

        writeFile(krankenversicherung, krankenversicherungName);
        writeFile(bewerbungsschreiben, bewerbungsschreibenName);
        writeFile(immatrikulationsantrag, immatrikulationsantragName);
        writeFile(hochschulzeugnis, hochschulzeugnisName);

        delegateExecution.setVariable("hochschulzeugnis", false);
        delegateExecution.setVariable("krankenversicherung", false);
        delegateExecution.setVariable("immatrikulationsantrag", false);
        delegateExecution.setVariable("bewerbungsschreiben", false);

    }


    private void updateUnterlagen(Integer unterlagenId, Map<String, String> unterlagen) {
        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
        unterlagenDAO.updateUnterlagenLoc(unterlagenId, unterlagen);
    }

    private void writeFile(InputStream file, String path) throws IOException {
        byte[] buffer = new byte[file.available()];
        file.read(buffer);
        File targetFile = new File(path);
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);
    }
}
