
package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheckAdmissionRequirementDelegate implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<String> courses = (List<String>) delegateExecution.getVariable("studiengangListe");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        Map<String, Map<String,Integer>> coursesWithNC = new LinkedHashMap<>();
        for (String course: courses) {
            StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(course);
            if (studiengangEntity.getBewerbersByStudiengangId().size() > studiengangEntity.getStudiengangFreiePlaetze()){
                studiengangDAO.updateCourseNc(studiengangEntity, true);
                Map<String, Integer> courseInformation = new LinkedHashMap<>();
                courseInformation.put("bewerberAnzahl", studiengangEntity.getBewerbersByStudiengangId().size());
                courseInformation.put("freiePlaetze", studiengangEntity.getStudiengangFreiePlaetze());
                coursesWithNC.put(studiengangEntity.getStudiengangName(),courseInformation);
            }else{
                studiengangDAO.updateCourseNc(studiengangEntity, false);
            }
            TypedValue coursesNc = Variables.objectValue(coursesWithNC).serializationDataFormat(Variables.SerializationDataFormats.JSON).create();
            delegateExecution.setVariable("kurseMitNc", coursesNc);
        }
    }
}
