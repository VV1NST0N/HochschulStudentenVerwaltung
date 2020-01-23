package immatrikulation.servicetaskdelegation.applicantRegistration;

import dataAccess.BewerberDAO;
import dataAccess.Exception.CustomBewerberException;
import dataAccess.ImmatrikulationsAntragDao;
import dataAccess.UnterlagenDAO;
import entities.BewerberEntity;
import entities.BewerbungsunterlagenEntity;
import entities.StudentEntity;
import helper.IdGenerator;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ApplicantRegistration implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try{

            BewerberEntity bewerberEntity = checkDatabaseIfExisting(delegateExecution);
            UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
            BewerbungsunterlagenEntity unterlagen = unterlagenDAO.createInitialUnterlagen();
            unterlagenDAO.insertEntity(unterlagen);


            ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
            immatrikulationsAntragDao.createInitialImmat(bewerberEntity, unterlagen);

            delegateExecution.setVariable("bewerberId", bewerberEntity.getBewerberId());
            delegateExecution.setVariable("unterlagenId", unterlagen.getUnterlagenId());
            delegateExecution.setVariable("bewerberErfasst", true);
        } catch (Exception e){
            delegateExecution.setVariable("bewerberErfasst", false);
            delegateExecution.setVariable("Reason", e.getCause());
        }


    }

    private BewerberEntity checkDatabaseIfExisting(DelegateExecution delegateExecution){
        Integer bewerberId = IdGenerator.createUniqueIds();
        BewerberEntity bewerberEntity = new BewerberEntity();
        bewerberEntity.setAdresse((String) delegateExecution.getVariable("adresse"));
        bewerberEntity.setBewerberId(bewerberId);
        bewerberEntity.setVorname((String) delegateExecution.getVariable("vorname"));
        bewerberEntity.setNachname((String)delegateExecution.getVariable("nachname"));
        bewerberEntity.setEmail((String) delegateExecution.getVariable("email"));
        bewerberEntity.setGeburtsort((String) delegateExecution.getVariable("geburtsort"));
        bewerberEntity.setWohnort((String) delegateExecution.getVariable("wohnort"));
        Date date = (Date) delegateExecution.getVariable("geburtsdatum");
        bewerberEntity.setGeburtsdatum( date.toInstant().atZone( ZoneId.of("Europe/Berlin")).toLocalDate());
        bewerberEntity.setNationalitaet((String) delegateExecution.getVariable("nationalitaet"));
        bewerberEntity.setAbiturnote((Double) delegateExecution.getVariable("abiturnote"));

        BewerberDAO bewerberDAO = new BewerberDAO();
        BewerberEntity oldBewerber = null;
        try {
         oldBewerber =   bewerberDAO.getApplicantIfAlreadyExistent(bewerberEntity.getVorname(), bewerberEntity.getNachname(), bewerberEntity.getGeburtsdatum(), bewerberEntity.getGeburtsort());
        } catch (CustomBewerberException e) {
            delegateExecution.setVariable("bewerberErfasst", false);
            delegateExecution.setVariable("Reason", e.getCause());
        }
        if (oldBewerber != null){
            bewerberEntity = oldBewerber;
            if (oldBewerber.getStudiengangByStudiengangId().getStudiengangName().equals(delegateExecution.getVariable("studiengangName"))) {
                delegateExecution.setVariable("bewerberErfasst", false);
                delegateExecution.setVariable("Reason", "Bewerber ist bereits im unter diesem Studiengang eingetragen!");
            }
        }

        bewerberDAO.insertBewerber(bewerberEntity, (String) delegateExecution.getVariable("studiengangName"));
        return bewerberEntity;
    }


}
