package immatrikulation.servicetaskdelegation;

import dataAccess.BewerberDAO;
import dataAccess.ImmatrikulationsAntragDao;
import dataAccess.UnterlagenDAO;
import entities.BewerberEntity;
import entities.BewerbungsunterlagenEntity;
import entities.ImmatrikulationsverfahrenStatusEntity;
import helper.IdGenerator;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.ZoneId;
import java.util.Date;

public class BewerberErfassung implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer bewerberId = IdGenerator.createUniqueIds();
        System.out.println(delegateExecution.getVariable("adresse"));
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

        /*String information = (String) delegateExecution.getVariable("sonstigeInformationen");
        if (information!= null){
            bewerberEntity.setSonstigeInformationen(information);
        }*/
        //TODO studiengangname enum in camunda
        BewerberDAO bewerberDAO = new BewerberDAO();
        bewerberDAO.insertBewerber(bewerberEntity, (String) delegateExecution.getVariable("studiengangName"));

        UnterlagenDAO unterlagenDAO = new UnterlagenDAO();
        BewerbungsunterlagenEntity unterlagen = unterlagenDAO.createInitialUnterlagen();
        unterlagenDAO.insertUnterlagen(unterlagen);

        ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
        ImmatrikulationsverfahrenStatusEntity immatrikulation = immatrikulationsAntragDao.createInitialImmat(bewerberEntity, unterlagen);
        immatrikulationsAntragDao.insertImmatrikulation(immatrikulation);

        delegateExecution.setVariable("bewerberId", bewerberId);
        delegateExecution.setVariable("unterlagenId", unterlagen.getUnterlagenId());

    }


}
