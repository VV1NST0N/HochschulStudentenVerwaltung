package immatrikulation.servicetaskdelegation;

import dataAccess.Dao;
import entities.StudentEntity;
import helper.DateConverter;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.util.Date;

public class GenerateMatNr implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String vorname = (String) delegateExecution.getVariable( "vorname");
        String nachname = (String) delegateExecution.getVariable("nachname");
        Date geburtsdatum = (Date) delegateExecution.getVariable("geburtsdatum");
        LocalDate geburtsdatumLocal = DateConverter.convertToLocalDate(geburtsdatum);

        //TODO studentBy Vorname Nachname Geburtsdatum
    }
}
