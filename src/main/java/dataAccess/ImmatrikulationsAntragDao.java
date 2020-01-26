package dataAccess;

import entities.BewerberEntity;
import entities.BewerbungsunterlagenEntity;
import entities.ImmatrikulationsverfahrenStatusEntity;
import entities.StudiengangEntity;
import helper.IdGenerator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ImmatrikulationsAntragDao extends Dao<ImmatrikulationsverfahrenStatusEntity> {

    public void createInitialImmat(BewerberEntity bewerber, BewerbungsunterlagenEntity unterlagen) {
        ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity = new ImmatrikulationsverfahrenStatusEntity();
        immatrikulationsverfahrenStatusEntity.setUnterlagenVollstaendig(false);
        immatrikulationsverfahrenStatusEntity.setZahlungStatus(false);
        immatrikulationsverfahrenStatusEntity.setZulassungStatus(false);
        LocalDate localDate = Instant.now().atZone(ZoneId.of("Europe/Berlin")).toLocalDate();
        immatrikulationsverfahrenStatusEntity.setBewerbungseingang(localDate);
        immatrikulationsverfahrenStatusEntity.setBewerberByBewerberId(bewerber);
        immatrikulationsverfahrenStatusEntity.setBewerbungsunterlagenByUnterlagenId(unterlagen);
        immatrikulationsverfahrenStatusEntity.setImmatId(IdGenerator.createUniqueIds());

        insertImmatrikulation(immatrikulationsverfahrenStatusEntity);
    }

    public void insertImmatrikulation(ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity) {
        EntityManager entityManager = ConnectionFac.init();
        entityManager.getTransaction().begin();
        entityManager.persist(immatrikulationsverfahrenStatusEntity);
        entityManager.getTransaction().commit();
    }

    public ImmatrikulationsverfahrenStatusEntity getImmatByBewerber(Integer bewerberId, Integer unterlagenId) {
        EntityManager em = ConnectionFac.init();
        Query query = em.createQuery("SELECT p FROM ImmatrikulationsverfahrenStatusEntity p WHERE p.bewerberByBewerberId.bewerberId = :name AND p.bewerbungsunterlagenByUnterlagenId.unterlagenId = :unterlagen");
        query.setParameter("name", bewerberId);
        query.setParameter("unterlagen", unterlagenId);

        List<ImmatrikulationsverfahrenStatusEntity> resultList = query.getResultList();
        for (ImmatrikulationsverfahrenStatusEntity p : resultList) {
            if (p.getBewerberByBewerberId().getBewerberId().equals(bewerberId)) {
                return p;
            }
        }
        return null;

    }

    @Override
    public ImmatrikulationsverfahrenStatusEntity getEntryById(Integer id) {
        EntityManager entityManager = ConnectionFac.init();
        return entityManager.find(ImmatrikulationsverfahrenStatusEntity.class, id);
    }

    public List<ImmatrikulationsverfahrenStatusEntity> getImmats() {

        EntityManager em = ConnectionFac.init();
        Query query = em.createQuery("SELECT c FROM ImmatrikulationsverfahrenStatusEntity c");
        List<ImmatrikulationsverfahrenStatusEntity> resultList = query.getResultList();
        return resultList;
    }
}
