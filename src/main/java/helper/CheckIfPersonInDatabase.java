package helper;

import dataAccess.Dao;
import entities.BewerberEntity;
import entities.Person;

public class CheckIfPersonInDatabase{

    public Boolean  studentOrBewerberAlreadyInDatabase(Integer id, Person person, Dao<Person> dao){
        Person newPerson = dao.getEntryById(id);
        if (newPerson.getVorname() != null){

        }else {
            return false;
        }
        return true;
    }

    public Boolean studentIsSamePerson(Integer id, Person person, Dao<Person> dao){
        Person newPerson = dao.getEntryById(id);
        if (newPerson.getVorname().equals(person.getVorname()) && newPerson.getNachname().equals(person.getNachname())){
            return true;
        }else {
            return false;
        }
    }
}
