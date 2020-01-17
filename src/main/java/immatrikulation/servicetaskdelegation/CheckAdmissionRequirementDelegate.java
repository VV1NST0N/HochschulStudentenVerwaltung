
package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.List;

public class CheckAdmissionRequirementDelegate implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<String> courses = (List<String>) delegateExecution.getVariable("studiengangListe");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        for (String course: courses) {
            StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(course);
            if (studiengangEntity.getBewerbersByStudiengangId().size() > studiengangEntity.getStudiengangFreiePlaetze()){
                studiengangDAO.updateCourseNc(studiengangEntity, true);
            }else{
                studiengangDAO.updateCourseNc(studiengangEntity, true);
            }
        }
    }
}
