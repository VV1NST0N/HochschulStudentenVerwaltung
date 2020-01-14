package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import org.camunda.bpm.engine.variable.Variables;
import twitter4j.internal.org.json.JSONObject;

import java.util.*;

import static org.camunda.bpm.engine.variable.Variables.objectValue;

public class GetStudienGaengeForBewerbung implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        StudiengangDAO studiengangDAO = new StudiengangDAO();
        List<StudiengangEntity> studiengänge = studiengangDAO.getStudiengänge();
        List<String> studienGangNamen = new ArrayList<String>();
        for (StudiengangEntity p : studiengänge) {

           studienGangNamen.add(p.getStudiengangName());
        }
        delegateExecution.setVariable("test", "Maschinenbau");

        delegateExecution.setVariable("studiengangListe", studienGangNamen);
    }
}
