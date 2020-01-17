package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.engine.variable.value.TypedValue;
import serializationModels.CoursesList;
import twitter4j.internal.org.json.JSONObject;

import java.util.*;

import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class GetAllCourseNamesAsList implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        StudiengangDAO studiengangDAO = new StudiengangDAO();
        List<StudiengangEntity> studiengänge = studiengangDAO.getStudiengänge();
        CoursesList coursesList = new CoursesList();
        coursesList.setStudiengangNamen(new ArrayList<String>());
        for (StudiengangEntity p : studiengänge) {
            coursesList.addCourse(p.getStudiengangName());
        }
        serializationModels.CoursesList courses = new serializationModels.CoursesList();
        TypedValue studienValue = Variables.objectValue(coursesList.getStudiengangNamen()).serializationDataFormat(Variables.SerializationDataFormats.JSON).create();
        delegateExecution.setVariable("studiengangListe", studienValue);
    }
}
