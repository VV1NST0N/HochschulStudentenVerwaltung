package ncBerechnung

import dataAccess.BewerberDAO
import immatrikulation.servicetaskdelegation.numerusClausus.CourseNcCalc
import spock.lang.Specification

class NcBerechnung extends Specification{

    def "test Nc Berrechnung"(){
        given:
        CourseNcCalc studiengangNcBerrechnung =  new CourseNcCalc()
        when:
        LinkedList<Double> list = [98,88,65,51,12,43,74,42,23,98,78,99,76,55,77,62,64,78,84,83,83,56,62]
        studiengangNcBerrechnung.calculateNc(list, 5)
        then:
        true
    }

    def "test get grades"(){
        BewerberDAO bewerberDAO = new BewerberDAO();
        List<Double> gradesList = bewerberDAO.getBewerberByStudiengang("Maschinenbau");
    }

}
