package immatrikulation.servicetaskdelegation.studentCreation;

import dataAccess.BewerberDAO;
import dataAccess.StudentDao;
import entities.BewerberEntity;
import entities.StudentEntity;
import helper.DateConverter;
import helper.IdGenerator;
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
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        LocalDate geburtsdatumLocal = DateConverter.convertToLocalDate(geburtsdatum);

        createMatNrForBewerber(vorname, nachname, geburtsdatumLocal, delegateExecution, bewerberId);
    }

    private void createMatNrForBewerber(String vorname, String nachname, LocalDate geburtsdatum, DelegateExecution delegateExecution, Integer bewerberId){
        StudentDao studentDao = new StudentDao();
        StudentEntity student = studentDao.searchForStudentInDatabase(vorname, nachname, geburtsdatum);
        if(student != null ){
            delegateExecution.setVariable("studentExistiert", true);
            delegateExecution.setVariable("matNr", student.getMatNr());
        }else{
            delegateExecution.setVariable("studentExistiert", false);
            Integer matNr = generatMatNr(studentDao);
            BewerberDAO bewerberDAO = new BewerberDAO();
            BewerberEntity bewerberEntity = bewerberDAO.getEntryById(bewerberId);
            bewerberEntity.setMatNr(matNr);
            bewerberDAO.updateEntity(bewerberEntity);
            delegateExecution.setVariable("matNr", matNr);
        }
    }

    private Integer generatMatNr(StudentDao studentDao){
        Integer matNr = IdGenerator.createUniqueIds();
        if (studentDao.getEntryById(matNr) != null){
            generatMatNr(studentDao);
        }else {
            return matNr;
        }
        return null;
    }
}
