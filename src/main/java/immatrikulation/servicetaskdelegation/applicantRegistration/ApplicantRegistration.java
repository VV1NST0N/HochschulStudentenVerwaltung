package immatrikulation.servicetaskdelegation.applicantRegistration;

import dataAccess.BewerberDAO;
import dataAccess.ImmatrikulationsAntragDao;
import dataAccess.UnterlagenDAO;
import entities.BewerberEntity;
import entities.BewerbungsunterlagenEntity;
import helper.IdGenerator;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.hibernate.DuplicateMappingException;

import java.time.ZoneId;
import java.util.Date;

public class ApplicantRegistration implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {

            BewerberEntity bewerberEntity = checkDatabaseIfExisting(delegateExecution);
            UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
            System.out.println("Bewerber: " + bewerberEntity.getVorname());
            BewerbungsunterlagenEntity unterlagen = unterlagenDAO.createInitialUnterlagen();
            unterlagenDAO.insertEntity(unterlagen);


            ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
            immatrikulationsAntragDao.createInitialImmat(bewerberEntity, unterlagen);

            delegateExecution.setVariable("bewerberId", bewerberEntity.getBewerberId());
            delegateExecution.setVariable("unterlagenId", unterlagen.getUnterlagenId());
            delegateExecution.setVariable("bewerberErfasst", true);
        } catch (Exception e) {
            delegateExecution.setVariable("bewerberErfasst", false);
            delegateExecution.setVariable("Reason", "Bei der Anmeldung ist etwas schief gegangen versuchen Sie es bitte noch einmal.");
            throw e;
        }


    }

    // Diese Methode ist aktuell nur bedingt teilweise nützlich, da eine Bewerbung an zwei unterschiedlichen Studiengängen noch nicht technisch möglich ist
    private BewerberEntity checkDatabaseIfExisting(DelegateExecution delegateExecution) throws DuplicateMappingException {
        Integer bewerberId = IdGenerator.createUniqueIds();
        BewerberEntity bewerberEntity = new BewerberEntity();
        bewerberEntity.setAdresse((String) delegateExecution.getVariable("adresse"));
        bewerberEntity.setBewerberId(bewerberId);
        bewerberEntity.setVorname((String) delegateExecution.getVariable("vorname"));
        bewerberEntity.setNachname((String) delegateExecution.getVariable("nachname"));
        bewerberEntity.setEmail((String) delegateExecution.getVariable("email"));
        bewerberEntity.setGeburtsort((String) delegateExecution.getVariable("geburtsort"));
        bewerberEntity.setWohnort((String) delegateExecution.getVariable("wohnort"));
        Date date = (Date) delegateExecution.getVariable("geburtsdatum");
        bewerberEntity.setGeburtsdatum(date.toInstant().atZone(ZoneId.of("Europe/Berlin")).toLocalDate());
        bewerberEntity.setNationalitaet((String) delegateExecution.getVariable("nationalitaet"));
        bewerberEntity.setAbiturnote((Double) delegateExecution.getVariable("abiturnote"));

        BewerberDAO bewerberDAO = new BewerberDAO();
        BewerberEntity oldBewerber = null;
        try {
            oldBewerber = bewerberDAO.getApplicantIfAlreadyExistent(bewerberEntity.getVorname(), bewerberEntity.getNachname(), bewerberEntity.getGeburtsdatum(), bewerberEntity.getGeburtsort());
        } catch (Exception e) {
        }
        if (oldBewerber != null) {
            Integer newCourse = bewerberEntity.getStudiengangId();
            bewerberEntity = oldBewerber;
            if (oldBewerber.getStudiengangByStudiengangId().getStudiengangName().equals(delegateExecution.getVariable("studiengangName"))) {
                delegateExecution.setVariable("bewerberErfasst", false);
                delegateExecution.setVariable("Reason", "Bewerber ist bereits im unter diesem Studiengang eingetragen!");
                throw new DuplicateMappingException(DuplicateMappingException.Type.ENTITY, "Bewerber ist bereits im unter diesem Studiengang eingetragen!");
            } else {
                bewerberEntity.setStudiengangId(newCourse);
            }
        } else {
            bewerberDAO.insertBewerber(bewerberEntity, (String) delegateExecution.getVariable("studiengangName"));
        }
        return bewerberEntity;
    }
}
