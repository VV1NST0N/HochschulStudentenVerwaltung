package serializationModels;

import entities.BewerberEntity;
import entities.StudentStudiengangEntity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class CoursesList {

    private List<String> studiengangNamen;

    public List<String> getStudiengangNamen() {
        return studiengangNamen;
    }

    public void setStudiengangNamen(List<String> studiengangNamen) {
        this.studiengangNamen = studiengangNamen;
    }

    public void addCourse(String name){
        this.studiengangNamen.add(name);
    }

    public String toString(){
        return "Studiengangliste: [namen: "+ studiengangNamen + "]";
    }



}
