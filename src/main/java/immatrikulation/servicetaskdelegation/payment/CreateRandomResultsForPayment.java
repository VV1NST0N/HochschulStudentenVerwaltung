package immatrikulation.servicetaskdelegation.payment;

import dataAccess.ImmatrikulationsAntragDao;
import entities.ImmatrikulationsverfahrenStatusEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.util.List;

public class CreateRandomResultsForPayment implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ImmatrikulationsAntragDao immatrikulationsAntragDao = new ImmatrikulationsAntragDao();
        List<ImmatrikulationsverfahrenStatusEntity> immats = immatrikulationsAntragDao.getImmats();
        for (ImmatrikulationsverfahrenStatusEntity immat : immats) {
            immat.setZahlungStatus(createRandomResult());
            System.out.println(immat.getZahlungStatus());
            try {
                immatrikulationsAntragDao.updateEntity(immat);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // Dieser Abschnitt dient als Ersatz für das eigentliche Zahlungssystem, dass zukünftig über eine API oder einen Message Broker angesprochen werden könnte
    // Es werden zufällige Bestätigungen oder Ablehnungen geschickt
    private Boolean createRandomResult() {
        Double randomDouble = Math.random();
        if (randomDouble < 0.9) {
            System.out.println("Random Double Val: " + randomDouble);
            return true;
        } else if (randomDouble >= 0.9) {
            return false;
        } else {
            return true;
        }
    }
}
