package immatrikulation.servicetaskdelegation.applicantRegistration;

import dataAccess.BewerberDAO;
import dataAccess.ImmatrikulationsAntragDao;
import dataAccess.UnterlagenDAO;
import entities.BewerberEntity;
import entities.BewerbungsunterlagenEntity;
import helper.IdGenerator;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.ZoneId;
import java.util.Date;

public class ApplicantRegistration implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try{
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
            bewerberDAO.insertBewerber(bewerberEntity, (String) delegateExecution.getVariable("studiengangName"));

            UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
            BewerbungsunterlagenEntity unterlagen = unterlagenDAO.createInitialUnterlagen();
            unterlagenDAO.insertUnterlagen(unterlagen);


            ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
            immatrikulationsAntragDao.createInitialImmat(bewerberEntity, unterlagen);

            delegateExecution.setVariable("bewerberId", bewerberId);
            delegateExecution.setVariable("unterlagenId", unterlagen.getUnterlagenId());
            delegateExecution.setVariable("bewerberErfasst", true);
        } catch (Exception e){
            delegateExecution.setVariable("bewerberErfasst", false);
            delegateExecution.setVariable("Reason", e.getCause());
        }


    }


}
