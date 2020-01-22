import dataAccess.BewerberDAO
import entities.BewerberEntity
import spock.lang.Specification

class testGetBewerberIds extends Specification {

    def "test get Bewerber Ids"(){
        given:
        BewerberDAO dao = new BewerberDAO()
        when:
        List<Integer> bewerberEntity = dao.getBewerberIds()
        then:
        bewerberEntity.each {println(it)}
        true
    }
}
