package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.BewerberEntity;
import entities.StudiengangEntity;
import immatrikulation.tasks.StudiengangNcBerrechnung;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.*;

public class NcBerrechnen implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer freiePlätze = (Integer) delegateExecution.getVariable("freiePlaetze");
        String studiengang = (String) delegateExecution.getVariable("studiengang");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengang);
        Collection<BewerberEntity> bewerber = studiengangEntity.getBewerbersByStudiengangId();
        List<Integer> noteBewerber = new LinkedList<Integer>();
        for (BewerberEntity bewerberEntity : bewerber) {
            noteBewerber.add( bewerberEntity.getAbiturnote());
        }
        StudiengangNcBerrechnung studiengangNcBerrechnung = new StudiengangNcBerrechnung();
        Integer nc = studiengangNcBerrechnung.berrechneNC(noteBewerber);
        // TODO set NC in database
        delegateExecution.setVariable("numerusclausus", nc);
    }
}
