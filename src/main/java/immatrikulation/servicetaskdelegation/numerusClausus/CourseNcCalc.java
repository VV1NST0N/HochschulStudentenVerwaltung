package immatrikulation.servicetaskdelegation.numerusClausus;

import java.util.Collections;
import java.util.LinkedList;

public class CourseNcCalc {

    public Double calculateNc(LinkedList<Double> bewerberNoten, Integer freiePlätze){
        Collections.sort(bewerberNoten);
        bewerberNoten = cutListByLastElement(bewerberNoten, freiePlätze);
        Double nc = bewerberNoten.removeLast();
        return nc;
    }

    private LinkedList<Double> cutListByLastElement(LinkedList<Double> bewerberNoten, Integer freiePlätze){

        if (bewerberNoten != null) {
            if ( bewerberNoten.size() > freiePlätze){
                bewerberNoten.removeLast();
                if(bewerberNoten.size()> freiePlätze){
                    cutListByLastElement(bewerberNoten, freiePlätze);
                }
            }
            return bewerberNoten;
        }
        return bewerberNoten;

    }
}
