package immatrikulation.servicetaskdelegation.payment;

import dataAccess.ImmatrikulationsAntragDao;
import entities.ImmatrikulationsverfahrenStatusEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.List;
import java.util.Random;

public class CreateRandomResultsForPayment implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
        List<ImmatrikulationsverfahrenStatusEntity> immats = immatrikulationsAntragDao.getImmats();
        for (ImmatrikulationsverfahrenStatusEntity immat : immats) {


            //Dieser Abschnitt dient als Ersatz für das eigentliche Zahlungssystem, dass zukünftig über eine API angesprochen werden soll
            // Es werden zufällige Bestätigungen oder Ablehnungen geschickt
            Random random = new Random();
            Boolean randomBool = random.nextBoolean();
            immat.setZahlungStatus(randomBool);
            try {
                immatrikulationsAntragDao.updateEntity(immat);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
