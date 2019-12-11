package dataAccess;

import entities.BewerberEntity;
import entities.StudiengangEntity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class BewerberDAO {

    public void  insertBewerber(BewerberEntity bewerberEntity, String studiengangName){
       EntityManager entityManager = ConnectionFac.init();
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        bewerberEntity.setStudiengangByStudiengangId(studiengangDAO.getStudiengang(studiengangName));
        bewerberEntity.setStudiengangId(bewerberEntity.getStudiengangByStudiengangId().getStudiengangId());

        entityManager.getTransaction().begin();
        entityManager.persist(bewerberEntity);
        entityManager.getTransaction().commit();

    }
}
