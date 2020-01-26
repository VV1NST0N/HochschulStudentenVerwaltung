import dataAccess.StudentDao
import entities.StudentEntity
import helper.DateConverter

import spock.lang.Specification

import java.time.LocalDate

class TestIText extends Specification{

    def "test I Text Working"(){
        given:
        //IdCardGen idCardGen = new IdCardGen()
        String[] text = ["Vorname: aasd", "Nachname: blabla"]
        when:
        idCardGen.convertToFile(text)
        then:
        true
    }

    def "new Test"(){
        given:
        LocalDate localDate = new LocalDate(1920,06,11)
        Date admissionDate = DateConverter.convertToDate(localDate);

        when:
        String timeDate = admissionDate.toString();
        Integer localHash = localDate.hashCode()
        println(localHash)
        print(localDate.toString()+ 'T00:00:00')
        then:
        true
    }

    def "test answer for get Student"(){
        given:
        StudentDao studentDao = new StudentDao()
        when:
        StudentEntity studentEntity = studentDao.getEntryById(93427237)
        then:
        studentEntity == null
    }
}
