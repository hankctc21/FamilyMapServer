package cs240.Result;

import java.util.ArrayList;

import cs240.Model.Person;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-14.
 */

public class PersonResult {

    private ArrayList<Person> data;


    public PersonResult(){
        data = new ArrayList<>();
    }

    public ArrayList<Person> getPersonsList() {
        return data;
    }

    public void setPersonsList(ArrayList<Person> persons) {
        this.data = persons;
    }

    public String toStringList(){
        for(Person p : data){
            return p.toString();
        }
        return null;
    }

}
