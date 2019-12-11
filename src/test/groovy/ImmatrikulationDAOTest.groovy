import dataAccess.ConnectionFac
import entities.BewerberEntity
import entities.BewerbungsunterlagenEntity
import entities.ImmatrikulationsverfahrenStatusEntity
import entities.ImmatrikulationsverfahrenStatusEntityPK
import spock.lang.Specification

import javax.persistence.EntityManager
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class ImmatrikulationDAOTest extends Specification{

    def "test insert Immatrikulation"(){
        given:
            BewerbungsunterlagenEntity bewerbungsunterlagenEntity = new BewerbungsunterlagenEntity()
        bewerbungsunterlagenEntity.setBewerbungsschreiben(false)
        bewerbungsunterlagenEntity.setHochschulreife(false)
        bewerbungsunterlagenEntity.setImmatrikulationsantrag(false)
        bewerbungsunterlagenEntity.setKrankenversicherung(false)
        bewerbungsunterlagenEntity.setUnterlagenId(123)

        BewerberEntity bewerber = new BewerberEntity()
        bewerber.setAdresse("test")
        bewerber.setBewerberId(132)
        bewerber.setEmail("test")
        LocalDate localDate = Instant.now().atZone(ZoneId.of("Europe/Berlin")).toLocalDate();
        bewerber.setGeburtsdatum(localDate)
        bewerber.setNachname("test")
        bewerber.setNationalitaet("test")
        bewerber.setGeburtsort("test")
        bewerber.setVorname("test")
        bewerber.setWohnort("test")

        ImmatrikulationsverfahrenStatusEntity immatrikulationsverfahrenStatusEntity = new ImmatrikulationsverfahrenStatusEntity()
        immatrikulationsverfahrenStatusEntity.setBewerbungseingang(localDate)
        immatrikulationsverfahrenStatusEntity.setUnterlagenVollstaendig(false)
        immatrikulationsverfahrenStatusEntity.setZahlungStatus(false)
        immatrikulationsverfahrenStatusEntity.setZulassungStatus(false)
        immatrikulationsverfahrenStatusEntity.setBewerberByBewerberId(bewerber)
        immatrikulationsverfahrenStatusEntity.setBewerbungsunterlagenByUnterlagenId(bewerbungsunterlagenEntity)

        EntityManager entityManager = ConnectionFac.init()
        when:
        entityManager.getTransaction().begin();
        entityManager.persist(bewerbungsunterlagenEntity);
        entityManager.persist(bewerber)
        entityManager.persist(immatrikulationsverfahrenStatusEntity)
        entityManager.getTransaction().commit();
        then:
        true
    }
}
