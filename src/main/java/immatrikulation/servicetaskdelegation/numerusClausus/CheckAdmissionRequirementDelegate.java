
package immatrikulation.servicetaskdelegation.numerusClausus;

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
        Map<String, Integer> coursesWithNC = new LinkedHashMap<>();

        //Prüfung ob ein NC notwendig ist
        for (String course : courses) {
            StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(course);
            if (studiengangEntity.getBewerbersByStudiengangId().size() > studiengangEntity.getStudiengangFreiePlaetze()) {
                studiengangEntity.setNcNotwendig(true);
                studiengangDAO.updateEntity(studiengangEntity);
                Integer freiePlaetze = studiengangEntity.getStudiengangFreiePlaetze();
                coursesWithNC.put(studiengangEntity.getStudiengangName(), freiePlaetze);
            } else {
                studiengangEntity.setNcNotwendig(false);
                studiengangDAO.updateEntity(studiengangEntity);
            }
            TypedValue coursesNc = Variables.objectValue(coursesWithNC).serializationDataFormat(Variables.SerializationDataFormats.JSON).create();
            delegateExecution.setVariable("kurseMitNc", coursesNc);
        }
    }
}
