package processingTasks.calcNc;

import java.util.Collections;
import java.util.LinkedList;

public class CourseNcCalc {

    // An dieser Stelle werden die Noten sortiert und über eine rekursive Methode auf die Anzahl freier Plätze begrenzt anschließend ist das letzte Element der Numerus Clausus
    public Double calculateNc(LinkedList<Double> bewerberNoten, Integer freiePlätze) {
        Collections.sort(bewerberNoten);
        bewerberNoten = cutListByLastElement(bewerberNoten, freiePlätze);
        Double nc = bewerberNoten.removeLast();
        return nc;
    }

    private LinkedList<Double> cutListByLastElement(LinkedList<Double> bewerberNoten, Integer freiePlätze) {

        if (bewerberNoten != null) {
            if (bewerberNoten.size() > freiePlätze) {
                bewerberNoten.removeLast();
                if (bewerberNoten.size() > freiePlätze) {
                    cutListByLastElement(bewerberNoten, freiePlätze);
                }
            }
            return bewerberNoten;
        }
        return bewerberNoten;

    }
}
