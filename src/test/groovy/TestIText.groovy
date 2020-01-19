import helper.DateConverter
import helper.IdCardGen
import spock.lang.Specification

import java.time.LocalDate

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

    def "new Test"(){
        given:
        LocalDate localDate = new LocalDate(2019,01,01)
        Date admissionDate = DateConverter.convertToDate(localDate);

        when:
        String timeDate = admissionDate.toString();

        print(localDate.toString()+ 'T00:00:00')
        then:
        true
    }
}
