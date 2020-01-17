import helper.IdCardGen
import spock.lang.Specification

class TestIText extends Specification{

    def "test I Text Working"(){
        given:
        IdCardGen idCardGen = new IdCardGen()
        String[] text = ["Vorname: aasd", "Nachname: blabla"]
        when:
        idCardGen.convertToFile(text)
        then:
        true
    }
}
