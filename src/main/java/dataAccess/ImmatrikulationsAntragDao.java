package dataAccess;

import entities.BewerberEntity;
import entities.BewerbungsunterlagenEntity;
import entities.ImmatrikulationsverfahrenStatusEntity;
import entities.ImmatrikulationsverfahrenStatusEntityPK;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ImmatrikulationsAntragDao {

    public void createInitialImmat(BewerberEntity bewerber, BewerbungsunterlagenEntity unterlagen){
        ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity = new ImmatrikulationsverfahrenStatusEntity();
        immatrikulationsverfahrenStatusEntity.setUnterlagenVollstaendig(false);
        immatrikulationsverfahrenStatusEntity.setZahlungStatus(false);
        immatrikulationsverfahrenStatusEntity.setZulassungStatus(false);
        LocalDate localDate = Instant.now().atZone(ZoneId.of("Europe/Berlin")).toLocalDate();
        immatrikulationsverfahrenStatusEntity.setBewerbungseingang( localDate);
        immatrikulationsverfahrenStatusEntity.setBewerberByBewerberId(bewerber);
        immatrikulationsverfahrenStatusEntity.setBewerbungsunterlagenByUnterlagenId(unterlagen);
        ImmatrikulationsverfahrenStatusEntityPK immatrikulationsverfahrenStatusEntityPK = new ImmatrikulationsverfahrenStatusEntityPK();
        immatrikulationsverfahrenStatusEntityPK.setBewerberId(bewerber.getBewerberId());
        immatrikulationsverfahrenStatusEntityPK.setUnterlagenId(unterlagen.getUnterlagenId());
        immatrikulationsverfahrenStatusEntity.setBewerberId(immatrikulationsverfahrenStatusEntity.getBewerberByBewerberId().getBewerberId());
        immatrikulationsverfahrenStatusEntity.setUnterlagenId(immatrikulationsverfahrenStatusEntity.getBewerbungsunterlagenByUnterlagenId().getUnterlagenId());

        insertImmatrikulation(immatrikulationsverfahrenStatusEntity, immatrikulationsverfahrenStatusEntityPK);
    }

    public void insertImmatrikulation(ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity, ImmatrikulationsverfahrenStatusEntityPK immatrikulationsverfahrenStatusEntityPK){
        EntityManager entityManager = ConnectionFac.init();
        entityManager.getTransaction().begin();
        entityManager.persist(immatrikulationsverfahrenStatusEntityPK);
        entityManager.persist(immatrikulationsverfahrenStatusEntity);
        entityManager.getTransaction().commit();

    }

    public void  insertDocumentsComplete(ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity){
        EntityManager entityManager = ConnectionFac.init();


        entityManager.getTransaction().begin();
        entityManager.persist(immatrikulationsverfahrenStatusEntity);
        entityManager.getTransaction().commit();

    }
}
