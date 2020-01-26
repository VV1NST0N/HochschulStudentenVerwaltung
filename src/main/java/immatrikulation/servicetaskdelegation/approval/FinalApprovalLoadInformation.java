package immatrikulation.servicetaskdelegation.approval;

import dataAccess.ImmatrikulationsAntragDao;
import dataAccess.StudentDao;
import dataAccess.StudentStudiengangDao;
import entities.ImmatrikulationsverfahrenStatusEntity;
import entities.StudentEntity;
import entities.StudentStudiengangEntity;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.List;

public class FinalApprovalLoadInformation implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        Integer unterlagenId = (Integer) delegateExecution.getVariable("unterlagenId");
        ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
        Integer matNr = (Integer) delegateExecution.getVariable("matNr");
        StudentDao studentDao = new StudentDao();
        StudentEntity studentEntity = null;
        if (matNr!= null){
            studentEntity = studentDao.getEntryById(matNr);
        }
        ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity = immatrikulationsAntragDao.getImmatByBewerber(bewerberId,unterlagenId);


        if (immatrikulationsverfahrenStatusEntity.getUnterlagenVollstaendig()){
            delegateExecution.setVariable("vollstaendigeUnterlagen", "Unterlagen sind vollständig.");
        }else {
            delegateExecution.setVariable("vollstaendigeUnterlagen", "Unterlagen sind vollständig.");
        }
        if (immatrikulationsverfahrenStatusEntity.getZahlungStatus()){
            delegateExecution.setVariable("zahlungErolgt", "Student hat die Studiengebühren bezahlt.");
        }else {
            delegateExecution.setVariable("zahlungErolgt", "Student hat die Studiengebühren nicht bezahlt.");
        }
        if (immatrikulationsverfahrenStatusEntity.getZulassungStatus()){
            delegateExecution.setVariable("zulassungErteilt", "Student erfüllt die Anforderungen zur Zulassung.");
        }else{
            delegateExecution.setVariable("zulassungErteilt", "Student erfüllt die Anforderungen zur Zulassung nicht.");
        }
        delegateExecution.setVariable("bewerbungsEingang", "Bewerber hat seine Anmeldung am " + immatrikulationsverfahrenStatusEntity.getBewerbungseingang().toString() + " abgeschickt.");
        String bereitsImmatString = "";
        if(studentEntity != null){
            bereitsImmatString += "Der Bewerber ist bereits als Student eingetragen.\n";
            StudentStudiengangDao studentStudiengangDao = new StudentStudiengangDao();
            List<StudentStudiengangEntity> studentStudiengangEntities = studentStudiengangDao.searchForStudentInDatabase(matNr);
            if(studentStudiengangEntities!= null){
                if (studentStudiengangEntities.size() > 0){
                    for (StudentStudiengangEntity studentStudiengangEntity: studentStudiengangEntities) {
                        bereitsImmatString+= "Studiengang: " + studentStudiengangEntity.getStudiengangEntities().getStudiengangName();
                        if (studentStudiengangEntity.getAktivesStudium()){
                            bereitsImmatString+="\nEs handelt sich um ein aktives Studium.";
                        }else{
                            bereitsImmatString+="\nEs handelt sich um kein aktives Studium.";
                        }
                    }
                }
            }
        }else {
            bereitsImmatString+="Der Bewerber ist noch nicht als Student erfasst in unserem System erfasst.";
        }
        delegateExecution.setVariable("aktivesStudium", bereitsImmatString);

    }
}
