package ncBerechnung

import com.sun.deploy.uitoolkit.impl.awt.AWTDragHelper
import immatrikulation.tasks.StudiengangNcBerrechnung
import spock.lang.Specification

class NcBerechnung extends Specification{

    def "test Nc Berrechnung"(){
        given:
        StudiengangNcBerrechnung studiengangNcBerrechnung =  new StudiengangNcBerrechnung()
        when:
        LinkedList<Long> list = [98,88,65,51,12,43,74,42,23,98,78,99,76,55,77,62,64,78,84,83,83,56,62]
        studiengangNcBerrechnung.berrechneNC(list, 5)
        then:
        true
    }

}
