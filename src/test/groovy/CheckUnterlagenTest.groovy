import entities.BewerbungsunterlagenEntity
import immatrikulation.servicetaskdelegation.CheckUnterlagen
import spock.lang.Specification

class CheckUnterlagenTest extends Specification {

        def "test check vollständig"(){
        given:
        BewerbungsunterlagenEntity bewerbungsunterlagenEntity = new BewerbungsunterlagenEntity()
            CheckUnterlagen checkUnterlagen = new CheckUnterlagen()
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
