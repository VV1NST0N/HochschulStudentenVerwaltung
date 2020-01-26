package dataAccess;

import entities.BewerbungsunterlagenEntity;
import entities.StudiengangEntity;
import helper.IdGenerator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class UnterlagenDAO extends Dao<BewerbungsunterlagenEntity>{

    public BewerbungsunterlagenEntity createInitialUnterlagen(){
        BewerbungsunterlagenEntity bewerbungsunterlagenEntity = new BewerbungsunterlagenEntity();
        bewerbungsunterlagenEntity.setBewerbungsschreiben(false);
        bewerbungsunterlagenEntity.setHochschulreife(false);
        bewerbungsunterlagenEntity.setImmatrikulationsantrag(false);
        bewerbungsunterlagenEntity.setKrankenversicherung(false);
        bewerbungsunterlagenEntity.setUnterlagenId(IdGenerator.createUniqueIds());
        bewerbungsunterlagenEntity.setPersonalausweis(false);
        return bewerbungsunterlagenEntity;
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

        unterlagen.setKrankenversicherung(unterlagenBool.get("krankenversicherung"));
        unterlagen.setHochschulreife(unterlagenBool.get("hochschulzeugnis"));
        unterlagen.setImmatrikulationsantrag(unterlagenBool.get("immatrikulationsantrag"));
        unterlagen.setBewerbungsschreiben(unterlagenBool.get("bewerbungsschreiben"));
        unterlagen.setPersonalausweis(unterlagenBool.get("personalausweis"));

        updateEntity(unterlagen);
    }

    public void updateUnterlagenLoc(Integer unterlagenId, Map<String, byte[]> unterlagenLocMap) {
       EntityManager entityManager = ConnectionFac.init();
        BewerbungsunterlagenEntity unterlagen = entityManager.find(BewerbungsunterlagenEntity.class, unterlagenId);
        unterlagen.setKrankenversicherungLocation(unterlagenLocMap.get("krankenversicherung"));
        unterlagen.setHochschulreifeLocation(unterlagenLocMap.get("hochschulzeugnis"));
        unterlagen.setImmatrikulationsantragLocation(unterlagenLocMap.get("immatrikulationsantrag"));
        unterlagen.setBewerbungsschreibenLocation(unterlagenLocMap.get("bewerbungsschreiben"));
        unterlagen.setBewerbungsschreibenLocation(unterlagenLocMap.get("personalausweis"));
        updateEntity(unterlagen);
    }

    @Override
    public BewerbungsunterlagenEntity getEntryById(Integer id) {
        EntityManager entityManager = ConnectionFac.init();
        BewerbungsunterlagenEntity bewerbungsunterlagenEntity = entityManager.find(BewerbungsunterlagenEntity.class, id);

        return bewerbungsunterlagenEntity;
    }
}
