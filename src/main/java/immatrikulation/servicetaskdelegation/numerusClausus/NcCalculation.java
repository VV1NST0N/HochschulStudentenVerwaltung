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
        Integer freiePlätze = (Integer) delegateExecution.getVariable("freiePlaetze");
        Map<String, Map<String,Integer>> coursesWithNC = (Map<String, Map<String, Integer>>) delegateExecution.getVariable("kurseMitNc");
        Map<String, Double> coursesNcMap = new HashMap<>();
        CourseNcCalc courseNcCalc = new CourseNcCalc();
        for ( String s : coursesWithNC.keySet()) {
            StudiengangDAO studiengangDAO = new StudiengangDAO();
            StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(s);
            Collection<BewerberEntity> bewerber = studiengangEntity.getBewerbersByStudiengangId();
            LinkedList<Double> grades = new LinkedList<>();
            for (BewerberEntity bewerberEntity : bewerber) {
                grades.add(bewerberEntity.getAbiturnote());
            }
            Double nc = courseNcCalc.calculateNc(grades,freiePlätze);
            studiengangDAO.updateCourseNcNumber(studiengangEntity, nc);
            coursesNcMap.put(s, nc);
        }
        // TODO set NC in database
        TypedValue couresNcVal = Variables.objectValue(coursesNcMap).serializationDataFormat(Variables.SerializationDataFormats.JSON).create();
        delegateExecution.setVariable("ccsOfAllCourses", couresNcVal);
    }
}
