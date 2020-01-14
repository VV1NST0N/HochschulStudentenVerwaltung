package immatrikulation.tasks;

import sun.rmi.runtime.Log;

import java.util.Collections;
import java.util.LinkedList;

public class StudiengangNcBerrechnung {

    public Long berrechneNC(LinkedList<Long> bewerberNoten, final Integer freiePlätze){
        Collections.sort(bewerberNoten);
        bewerberNoten = cutListBy(bewerberNoten, freiePlätze);
        Long nc = bewerberNoten.removeFirst();
        return nc;
    }

    private LinkedList<Long> cutListBy(LinkedList<Long> bewerberNoten, final Integer freiePlätze){
        if ( bewerberNoten.size()> freiePlätze){
            bewerberNoten.removeFirst();
            cutListBy(bewerberNoten, freiePlätze);
        }
        return bewerberNoten;
    }
}
