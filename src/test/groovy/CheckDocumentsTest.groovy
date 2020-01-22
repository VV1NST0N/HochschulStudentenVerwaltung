import entities.BewerbungsunterlagenEntity
import immatrikulation.servicetaskdelegation.applicantRegistration.CheckDocuments
import org.junit.Ignore
import spock.lang.Specification

@Ignore
class CheckDocumentsTest extends Specification {

        def "test check vollständig"(){
        given:
        BewerbungsunterlagenEntity bewerbungsunterlagenEntity = new BewerbungsunterlagenEntity()
        CheckDocuments checkUnterlagen = new CheckDocuments()
            bewerbungsunterlagenEntity.setUnterlagenId(123)
            bewerbungsunterlagenEntity.setKrankenversicherung(true)
            bewerbungsunterlagenEntity.setImmatrikulationsantrag(true)
            bewerbungsunterlagenEntity.setHochschulreife(true)
            bewerbungsunterlagenEntity.setBewerbungsschreiben(true)
        when:
        Boolean test = checkUnterlagen.checkVollständigkeit(bewerbungsunterlagenEntity)
        then:
        test==true
}
}
