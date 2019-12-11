package dataAccess;

import entities.BewerberEntity;
import entities.BewerbungsunterlagenEntity;
import helper.IdGenerator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class UnterlagenDAO {

    public BewerbungsunterlagenEntity createInitialUnterlagen(){
        BewerbungsunterlagenEntity bewerbungsunterlagenEntity = new BewerbungsunterlagenEntity();
        bewerbungsunterlagenEntity.setBewerbungsschreiben(false);
        bewerbungsunterlagenEntity.setHochschulreife(false);
        bewerbungsunterlagenEntity.setImmatrikulationsantrag(false);
        bewerbungsunterlagenEntity.setKrankenversicherung(false);
        bewerbungsunterlagenEntity.setUnterlagenId(IdGenerator.createUniqueIds());
        return bewerbungsunterlagenEntity;
    }

    public void  insertUnterlagen(BewerbungsunterlagenEntity bewerbungsunterlagenEntity){
        EntityManager entityManager = ConnectionFac.init();

        entityManager.getTransaction().begin();
        entityManager.persist(bewerbungsunterlagenEntity);
        entityManager.getTransaction().commit();

    }

    public BewerbungsunterlagenEntity getUnterlagenById(Integer unterlagenId){
            EntityManager em = ConnectionFac.init();
            Query query = em.createQuery("SELECT p FROM BewerbungsunterlagenEntity p WHERE p.unterlagenId = :name");
            query.setParameter("name", unterlagenId);
            List<BewerbungsunterlagenEntity> resultList = query.getResultList();
            for (BewerbungsunterlagenEntity p : resultList) {
                if(p.getUnterlagenId().equals(unterlagenId) ){
                    return p;
                }
            }
            return null;
    }

    public void updateUnterlagen(Integer unterlagenId, Map<String, Boolean> unterlagenBool) {
        EntityManager entityManager = ConnectionFac.init();
        BewerbungsunterlagenEntity unterlagen = entityManager.find(BewerbungsunterlagenEntity.class, unterlagenId);

        entityManager.getTransaction().begin();
        unterlagen.setKrankenversicherung(unterlagenBool.get("krankenversicherung"));
        unterlagen.setHochschulreife(unterlagenBool.get("hochschulzeugnis"));
        unterlagen.setImmatrikulationsantrag(unterlagenBool.get("immatrikulationsantrag"));
        unterlagen.setBewerbungsschreiben(unterlagenBool.get("bewerbungsschreiben"));
        entityManager.getTransaction().commit();
    }
}
