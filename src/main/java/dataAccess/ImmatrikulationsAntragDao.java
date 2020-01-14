package dataAccess;

import entities.BewerberEntity;
import entities.BewerbungsunterlagenEntity;
import entities.ImmatrikulationsverfahrenStatusEntity;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ImmatrikulationsAntragDao {

    public ImmatrikulationsverfahrenStatusEntity createInitialImmat(BewerberEntity bewerber, BewerbungsunterlagenEntity unterlagen){
        ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity = new ImmatrikulationsverfahrenStatusEntity();
        immatrikulationsverfahrenStatusEntity.setUnterlagenVollstaendig(false);
        immatrikulationsverfahrenStatusEntity.setZahlungStatus(false);
        immatrikulationsverfahrenStatusEntity.setZulassungStatus(false);
        LocalDate localDate = Instant.now().atZone(ZoneId.of("Europe/Berlin")).toLocalDate();
        immatrikulationsverfahrenStatusEntity.setBewerbungseingang( localDate);
        immatrikulationsverfahrenStatusEntity.setBewerberByBewerberId(bewerber);
        immatrikulationsverfahrenStatusEntity.setBewerbungsunterlagenByUnterlagenId(unterlagen);
        immatrikulationsverfahrenStatusEntity.setBewerberId(immatrikulationsverfahrenStatusEntity.getBewerberByBewerberId().getBewerberId());
        immatrikulationsverfahrenStatusEntity.setUnterlagenId(immatrikulationsverfahrenStatusEntity.getBewerbungsunterlagenByUnterlagenId().getUnterlagenId());

        return immatrikulationsverfahrenStatusEntity;
    }

    public void insertImmatrikulation(ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity){
        EntityManager entityManager = ConnectionFac.init();
        entityManager.getTransaction().begin();
        entityManager.persist(immatrikulationsverfahrenStatusEntity);
        entityManager.getTransaction().commit();

    }
}
