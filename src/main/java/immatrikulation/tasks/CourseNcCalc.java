package immatrikulation.tasks;

import java.util.Collections;
import java.util.LinkedList;

public class CourseNcCalc {

    public Long calculateNc(LinkedList<Long> bewerberNoten, final Integer freiePlätze){
        Collections.sort(bewerberNoten);
        bewerberNoten = cutListByLastElement(bewerberNoten, freiePlätze);
        Long nc = bewerberNoten.removeFirst();
        return nc;
    }

    private LinkedList<Long> cutListByLastElement(LinkedList<Long> bewerberNoten, final Integer freiePlätze){
        if ( bewerberNoten.size()> freiePlätze){
            bewerberNoten.removeFirst();
            cutListByLastElement(bewerberNoten, freiePlätze);
        }
        return bewerberNoten;
    }
}
