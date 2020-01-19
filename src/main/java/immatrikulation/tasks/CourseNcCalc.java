package immatrikulation.tasks;

import java.util.Collections;
import java.util.LinkedList;

public class CourseNcCalc {

    public Double calculateNc(LinkedList<Double> bewerberNoten, final Integer freiePlätze){
        Collections.sort(bewerberNoten);
        bewerberNoten = cutListByLastElement(bewerberNoten, freiePlätze);
        Double nc = bewerberNoten.removeFirst();
        return nc;
    }

    private LinkedList<Double> cutListByLastElement(LinkedList<Double> bewerberNoten, final Integer freiePlätze){
        Integer db = bewerberNoten.size();

        if (db != null) {
            if ( bewerberNoten.size()> freiePlätze){
                bewerberNoten.removeFirst();
                cutListByLastElement(bewerberNoten, freiePlätze);
            }
            return bewerberNoten;
        }
        return bewerberNoten;

    }
}
