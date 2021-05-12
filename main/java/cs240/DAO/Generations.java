package cs240.DAO;

import java.util.ArrayList;
import java.util.List;

import cs240.Model.Event;
import cs240.Model.Person;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-08.
 */


public class Generations {
    /** List of persons in the generations */
    public List<Person> persons;

    /** List of events in the generations */
    public List<Event> events;

    public Generations() {
        this.events = new ArrayList<Event>();
        this.persons = new ArrayList<Person>();
    }

    /**
     * Get the first male from the generations.
     * @return String
     */
    public String firstFather() {
        assert(persons.size() > 0);
        assert(persons.get(0).getGender() == "m");
        return persons.get(0).getPersonID();
    }

    /**
     * Get the first female from the generations.
     * @return String
     */
    public String firstMother() {
        assert(persons.size() > 0);
        assert(persons.get(1).getGender() == "f");
        return persons.get(1).getPersonID();
    }

    /**
     * Get the current number of generations from the number of people.
     * @return int
     */
    public int currentGenNum() {
        return binLog(persons.size() + 2);
    }

    /**
     * Log base 2; used for calculating generations generated based
     * on the number of persons in the generation.
     * @param x int
     * @return int
     */
    public int binLog(int x) {
        if (x == 0) {
            return 0;
        }
        return (int) (Math.log(x)/Math.log(2));
    }

    /**
     * Get the number of persons that should be generated for given number
     * of generations.
     * @param x int number of generations.
     * @return int number of persons that should be in x generations.
     */
    public int personsInGeneration(int x) {
        return 2*(int)Math.pow(2, x) - 2;
    }

    /**
     * Get list representing a single generation. Takes subset of persons
     * @param num number of generation to get.
     * @return List of PersonModels.
     */
    public List<Person> generation(int num) {
        return persons.subList(personsInGeneration(num - 1), personsInGeneration(num));
    }

    public String toString(){
        return persons.toString();
    }
}
