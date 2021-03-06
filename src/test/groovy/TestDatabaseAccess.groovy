import dataAccess.StudiengangDAO
import entities.StudiengangEntity
import dataAccess.ConnectionFac
import immatrikulation.servicetaskdelegation.twitter.SendToTwitter
import org.junit.Ignore
import spock.lang.Specification
import twitter4j.Twitter

import javax.persistence.EntityManager
import javax.persistence.Query
import java.time.Instant
import java.time.LocalDate

class TestDatabaseAccess extends Specification {

    def "test insert Studiengang for testing"(){
        given:
        Class.forName("com.mysql.jdbc.Driver")
        EntityManager entityManager = ConnectionFac.init()
        when:

        StudiengangEntity studiengangEntity = new StudiengangEntity()
        studiengangEntity.setStudiengangId(111)
        studiengangEntity.setStudiengangName("Mathematik")
        studiengangEntity.setNumerusClaususNote(0)
        studiengangEntity.setStudiengangFreiePlaetze(120)
        studiengangEntity.setVorraussetzungTest(true)
        studiengangEntity.setZulassungszeitraum(new LocalDate(2020,8,5))
        studiengangEntity.setStudiengangPlatzzahl(350)

        entityManager.getTransaction().begin()
        entityManager.persist(studiengangEntity)
        entityManager.getTransaction().commit()
        then:
        true
    }

    def "test insert Bewerber is working"(){
        given:
        Date date = new Date()
        when:
        Instant test =  date.toInstant()
        then:
        true
    }

    def "test dir"(){
        given:
        String path = System.getProperty("user.home")
        when:
        System.out.println(path)
        then:
        true
    }
    def "test Database get Studiengang is working"(){
        given:
    EntityManager em = ConnectionFac.init();
        when:
    Query query = em.createQuery("SELECT p FROM StudiengangEntity p WHERE p.studiengangName = :name");
    query.setParameter("name", "Mathematik");
        StudiengangEntity result = null
    List<StudiengangEntity> resultList = query.getResultList();
    for (StudiengangEntity p : resultList) {
        if(p.getStudiengangName() == "Mathematik"){
            result = p;
        }
    }
    then:
    result != null
    }

    def "test Twitter is working"(){
        given:
        SendToTwitter send = new SendToTwitter()
        String content = "Die Bewerbungsfrist für das Sommersemster 2020 ist noch bis zum " + 123 + " offen. " +
                "\n  Bitte denken Sie daran alle Unterlagen bis zum Ende der Frist einzureichen"
        when:
        send.doSendTwitter(content)
        then:
        true
    }

    def "test get Studiengänge"(){
        given:
        StudiengangDAO studiengangDAO = new StudiengangDAO()
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang("Informatik")

        when:
        Integer bewerberAnzahl = studiengangEntity.getBewerbersByStudiengangId().size()
        Integer freiePlaetze = studiengangEntity.getStudiengangFreiePlaetze();
        println("Bewerber: $bewerberAnzahl \n freie Plätze: $freiePlaetze")
        then:
        bewerberAnzahl > 0
        freiePlaetze > 0

    }

    /*def "test Database insert Student is working"(){
        given:
        Student student = new Student("Max", "Mustermann", "Musterstrasse 4", 123, new Date(19990101), "Musterstadt", "mustermail", "deutsch")
        StudentDao dao = new StudentDao()
        when:
        boolean succes = dao.insert(student)
        then:
        succes == true
    }

    def "test Database insert Bewerber is working"(){
        given:
        Bewerber bewerber = new Bewerber(123,"Max", "Mustermann", "Musterstrasse 4", new Date(19990101), "Musterstadt", "mustermail", "deutsch", new Studiengang(123))
        BewerberDao dao = new BewerberDao()
        when:
        boolean succes = dao.insert(bewerber)
        then:
        succes == true
    }

    def "test Database update Student is working"(){
        given:
        Student student = new Student("Max", "Mustermann", "Musterstrasse 5", 123, new Date(19990101), "Musterstadt", "mustermail", "amerikanisch")
        StudentDao dao = new StudentDao()
        when:
        boolean succes = dao.update(student, ["adresse", "nationalitaet"])
        then:
        succes == true
    }

    def "test Database get Student is working"(){
        given:
        StudentDao dao = new StudentDao()
        when:
        Student student = dao.get(123)
        System.out.println(student.toString())
        then:
        student.getMat_nr() == 123
        student.getVorname() == "Max"
        student.getNachname() == "Mustermann"
    }

    def "test Database getAll Student is working"(){
        given:
        StudentDao dao = new StudentDao()
        when:
        Map<Integer, Student> students = dao.getAll()
        students.each {
            System.out.println(it)
        }
        then:
        students.size() == 1
        students[123].nachname == "Mustermann"
    }*/
}
