package immatrikulation.servicetaskdelegation.numerusClausus;

import dataAccess.StudiengangDAO;
import entities.BewerberEntity;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.*;

public class NcCalculation implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String,Integer> coursesWithNC = (Map<String, Integer>) delegateExecution.getVariable("kurseMitNc");
        CourseNcCalc courseNcCalc = new CourseNcCalc();
        for ( String s : coursesWithNC.keySet()) {
            StudiengangDAO studiengangDAO = new StudiengangDAO();
            StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(s);
            Integer freiePlätze = studiengangEntity.getStudiengangFreiePlaetze();
            Collection<BewerberEntity> bewerber = studiengangEntity.getBewerbersByStudiengangId();
            LinkedList<Double> grades = new LinkedList<>();
            for (BewerberEntity bewerberEntity : bewerber) {
                grades.add(bewerberEntity.getAbiturnote());
            }
            Double nc = courseNcCalc.calculateNc(grades,freiePlätze);
            grades.clear();
            studiengangEntity.setNumerusClaususNote(nc);
            studiengangDAO.updateEntity(studiengangEntity);
        }
    }
}
