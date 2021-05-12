package cs240.Request;

import java.util.ArrayList;

import cs240.Model.Event;
import cs240.Model.Person;
import cs240.Model.User;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-13.
 */

/**
 * store load info
 */
public class LoadRequest {
    private ArrayList<User> users;
    private ArrayList<Person> persons;
    private ArrayList<Event> events;

    public LoadRequest(){
        users = new ArrayList<>();
        persons = new ArrayList<>();
        events = new ArrayList<>();
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


    public String toString() {
        ArrayList<String> s = new ArrayList<>();

        s.add("\n");
        s.add(users.toString());
        s.add("\n");
        s.add(persons.toString());
        s.add("\n");
        s.add(events.toString());
        s.add("\n");
        return s.toString();
    }
}


