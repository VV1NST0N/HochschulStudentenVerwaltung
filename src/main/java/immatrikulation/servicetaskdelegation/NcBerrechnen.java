package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.BewerberEntity;
import entities.StudiengangEntity;
import immatrikulation.tasks.CourseNcCalc;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.*;

public class NcBerrechnen implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer freiePlätze = (Integer) delegateExecution.getVariable("freiePlaetze");
        List<String> coursesList = (List<String>) delegateExecution.getVariable("studiengangListe");
        Map<String, Long> coursesNcMap = new LinkedHashMap<>();
        CourseNcCalc courseNcCalc = new CourseNcCalc();
        for (String s: coursesList) {
            StudiengangDAO studiengangDAO = new StudiengangDAO();
            StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(s);
            Collection<BewerberEntity> bewerber = studiengangEntity.getBewerbersByStudiengangId();
            LinkedList<Long> grades = new LinkedList<>();
            for (BewerberEntity bewerberEntity : bewerber) {
                grades.add(bewerberEntity.getAbiturnote());
            }
            Long nc = courseNcCalc.calculateNc(grades,freiePlätze);
            studiengangDAO.updateCourseNcNumber(studiengangEntity, nc);
            coursesNcMap.put(s, nc);
        }
        // TODO set NC in database
        TypedValue couresNcVal = Variables.objectValue(coursesNcMap).serializationDataFormat(Variables.SerializationDataFormats.JSON).create();
        delegateExecution.setVariable("ccsOfAllCourses", couresNcVal);
    }
}
