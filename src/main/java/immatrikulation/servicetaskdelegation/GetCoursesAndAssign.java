package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;
import serializationModels.CoursesList;

import java.util.ArrayList;
import java.util.List;


public class GetCoursesAndAssign implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        StudiengangDAO studiengangDAO = new StudiengangDAO();
        List<StudiengangEntity> studiengänge = studiengangDAO.getStudiengänge();
        CoursesList coursesList = new CoursesList();
        coursesList.setStudiengangNamen(new ArrayList<String>());
        for (StudiengangEntity p : studiengänge) {
            coursesList.addCourse(p.getStudiengangName());
        }
        IdentityService identityService = delegateExecution.getProcessEngineServices().getIdentityService();

        String userId = identityService.getCurrentAuthentication().getUserId();

        TypedValue studienValue = Variables.objectValue(coursesList.getStudiengangNamen()).serializationDataFormat(Variables.SerializationDataFormats.JSON).create();
        delegateExecution.setVariable("studiengangListe", studienValue);
        delegateExecution.setVariable("assignee", userId);
    }


}
