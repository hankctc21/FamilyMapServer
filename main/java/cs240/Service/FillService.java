package cs240.Service;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;

import cs240.DAO.EventDao;
import cs240.DAO.Generate;
import cs240.DAO.Generations;
import cs240.DAO.PersonDao;
import cs240.DAO.UserDao;
import cs240.Model.AuthToken;
import cs240.Model.Couple;
import cs240.Model.Event;
import cs240.Model.LocData;
import cs240.Model.Person;
import cs240.Model.User;
import cs240.Result.FillResult;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */


/**
 *  Populates the server's database with generated data for the specified user name.
 The required "username" parameter must be a user already registered with the server. If there is
 any data in the database already associated with the given user name, it is deleted. The
 optional “generations” parameter lets the caller specify the number of generations of ancestors
 to be generated, and must be a non-negative integer (the default is 4, which results in 31 new
 persons each with associated events).
 */
public class FillService {

    Generate gen = new Generate();
    UserDao UD = new UserDao();
    PersonDao PD = new PersonDao();
    EventDao ED = new EventDao();


    String gender = "";

    Generations generations = new Generations();

    ArrayDeque<Couple> queue = new ArrayDeque<Couple>();

    private int genNum;

    public FillService(){
    }

    /**
     * Find user in the server.
     * If there is any data with the user name, delete.
     * @return
     */
    public FillResult fill(String username, int num) throws SQLException {

        FillResult FR = new FillResult();

        UserDao UD = new UserDao();
        User u = new User();
        PersonDao PD = new PersonDao();
        Person p = new Person();
        EventDao ED = new EventDao();
        Event e = new Event();
        int pCount = 0;
        int eCount = 0;
        Couple c = new Couple();



        u.setUserName(username);


        if(UD.findUser(u)){
            u.setUser(UD.getUser(u));
            p.setPersonID(u.getPersonID());
            p.setPerson(PD.getPerson(p));
            e.setPersonID(p.getPersonID());
            e.setEvent(ED.getEvent(e));
        }
        p.setDescendant(u.getUserName());
        e.setDescendant(u.getUserName());

        if(!(PD.removeAllPerson(p))){
        }
        else{
            PD.removeAllPerson(p);
            ED.removeAllEvent(e.getDescendant());

        }

        UD.remove(u);

        try {
            c = SetAndAddUser(u, c);
            pCount++;
            eCount++;
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        generations.persons.clear();
        generations.events.clear();
        if(gender.equals("m")){
            try {
                generations = generateGenerations(num, c.getHusband().getHusbandEvent().getYear(), c);


            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else{
            try {
                generations = generateGenerations(num, c.getWife().getWifeEvent().getYear(), c);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        for(Person person : generations.persons){
            if(!(PD.addPerson(person))){
                throw new SQLException("Error in addPerson");
            }

            pCount++;
        }

        for(Event event : generations.events){
            ED.addEvent(event);
            eCount++;
        }

        FR.setMessage("Successfully added " + pCount + " persons and " + eCount + " events to the database.");


        queue.clear();
        c.setHusband(null);
        c.setWife(null);


        return FR;
    }




    public Couple SetAndAddUser(User r, Couple c ) throws SQLException, IOException {


        Event e = new Event();
        User u = new User();
        Person p = new Person();
        AuthToken a = new AuthToken();

            u.setUserName(r.getUserName());
            u.setPassWord(r.getPassWord());
            u.setEmail(r.getEmail());
            u.setFirstName(r.getFirstName());
            u.setLastName(r.getLastName());
            u.setGender(r.getGender());
            u.setAuthToken(r.getAuthToken());
            u.setPersonID(r.getPersonID());
            if(!(UD.addUser(u))){
                throw new SQLException("Errir in addUser");
            }

            if(u.getGender().equals("m")){
                c.getHusband().setHusbandUser(u);
                gender = "m";
            }
            else{
                c.getWife().setWifeUser(u);
                gender = "f";
            }

            p.setFirstName(u.getFirstName());
            p.setLastName(u.getLastName());
            p.setGender(u.getGender());
            p.setPersonID(u.getPersonID());
            p.setFatherID(gen.getRandomID());
            p.setMotherID(gen.getRandomID());
            p.setSpouseID(gen.getRandomID());
            p.setDescendant(u.getUserName());

        if(!(PD.addPerson(p))){
            throw new SQLException("Error in addPerson");
        }

        if(u.getGender().equals("m")){
                c.getHusband().setHusbandPerson(p);
                gender = "m";
            }
            else{
                c.getWife().setWifePerson(p);
                gender = "f";
            }


            LocData L = gen.getRandLocation();//random location
            e.setEventID(gen.getRandomID());
            e.setDescendant(p.getDescendant());
            e.setPersonID(p.getPersonID());
            e.setLatitude(gen.getRandLocation().getLatitude());
            e.setLongitude(L.getLongitude());
            e.setCity(L.getCity());
            e.setCountry(L.getCountry());
            e.setEventType(gen.getRandEventType());
            e.setYear(gen.getRandYear());
            ED.addEvent(e);
            if(u.getGender().equals("m")){
                c.getHusband().setHusbandEvent(e);
            }
            else{
                c.getWife().setWifeEvent(e);
            }


        return c;
    }

    public Generations generateGenerations(int n, int year, Couple c) throws SQLException, IOException {
        Couple first = gen.randomCouple(year);
        if(gender.equals("m")) {
            first.getHusband().getHusbandPerson().setPersonID(c.getHusband().getHusbandPerson().getFatherID());
            first.getWife().getWifePerson().setPersonID(c.getHusband().getHusbandPerson().getMotherID());
            first.getHusband().getHusbandEvent().setPersonID(first.getHusband().getHusbandPerson().getPersonID());
            first.getWife().getWifeEvent().setPersonID(first.getWife().getWifePerson().getPersonID());
            first.getHusband().getHusbandPerson().setDescendant(c.getHusband().getHusbandPerson().getDescendant());
            first.getWife().getWifePerson().setDescendant(c.getHusband().getHusbandPerson().getDescendant());
            first.getHusband().getHusbandEvent().setDescendant(first.getHusband().getHusbandPerson().getDescendant());
            first.getWife().getWifeEvent().setDescendant(first.getHusband().getHusbandPerson().getDescendant());
            first.getHusband().getHusbandPerson().setLastName(c.getHusband().getHusbandPerson().getLastName());
            first.getWife().getWifePerson().setLastName(first.getHusband().getHusbandPerson().getLastName());
            first.getHusband().getHusbandPerson().setSpouseID(first.getWife().getWifePerson().getPersonID());
            first.getWife().getWifePerson().setSpouseID(first.getHusband().getHusbandPerson().getPersonID());

        }
        else{
            first.getHusband().getHusbandPerson().setPersonID(c.getWife().getWifePerson().getFatherID());
            first.getWife().getWifePerson().setPersonID(c.getWife().getWifePerson().getMotherID());
            first.getHusband().getHusbandEvent().setPersonID(first.getHusband().getHusbandPerson().getPersonID());
            first.getWife().getWifeEvent().setPersonID(first.getWife().getWifePerson().getPersonID());
            first.getHusband().getHusbandPerson().setDescendant(c.getWife().getWifePerson().getDescendant());
            first.getWife().getWifePerson().setDescendant(c.getWife().getWifePerson().getDescendant());
            first.getHusband().getHusbandEvent().setDescendant(first.getWife().getWifePerson().getDescendant());
            first.getWife().getWifeEvent().setDescendant(first.getWife().getWifePerson().getDescendant());
            first.getHusband().getHusbandPerson().setLastName(c.getWife().getWifeUser().getLastName());
            first.getWife().getWifePerson().setLastName(first.getWife().getWifePerson().getLastName());
            first.getHusband().getHusbandPerson().setSpouseID(first.getWife().getWifePerson().getPersonID());
            first.getWife().getWifePerson().setSpouseID(first.getHusband().getHusbandPerson().getPersonID());
        }

        queue.add(first); // Gen 0. Add the first couple to the queue



        int total = generations.personsInGeneration(n);
        while ((generations.persons.size()+queue.size()*2) < total) { // Begin processing queue, adding more people onto the end of the queue
            int generation = generations.currentGenNum();
            Couple couple = queue.poll();



            //Husband's parents------------------------------
            Couple husbandParents = gen.randomCouple(year - (generation * gen.getRandNumber()));
            //set FatherID, MotherID of the parent
            couple.getHusband().getHusbandPerson().setFatherID(husbandParents.getHusband().getHusbandPerson().getPersonID());
            couple.getHusband().getHusbandPerson().setMotherID(husbandParents.getWife().getWifePerson().getPersonID());




            //set descendants of the parent
            husbandParents.getHusband().getHusbandPerson().setDescendant(couple.getHusband().getHusbandPerson().getDescendant());
            husbandParents.getWife().getWifePerson().setDescendant(husbandParents.getHusband().getHusbandPerson().getDescendant());

            //set last name of the parent
            husbandParents.getHusband().getHusbandPerson().setLastName(couple.getHusband().getHusbandPerson().getLastName());
            husbandParents.getWife().getWifePerson().setLastName(husbandParents.getHusband().getHusbandPerson().getLastName());

            //set Event of parent
            husbandParents.getHusband().getHusbandEvent().setDescendant(husbandParents.getHusband().getHusbandPerson().getDescendant());
            husbandParents.getWife().getWifeEvent().setDescendant(husbandParents.getHusband().getHusbandPerson().getDescendant());
            husbandParents.getHusband().getHusbandEvent().setPersonID(husbandParents.getHusband().getHusbandPerson().getPersonID());
            husbandParents.getWife().getWifeEvent().setPersonID(husbandParents.getWife().getWifePerson().getPersonID());

            //add to queue
            queue.add(husbandParents);

            Couple wifeParents = gen.randomCouple(year - (generation * gen.getRandNumber()));
            //set FatherID, MotherID of the parent
            couple.getWife().getWifePerson().setFatherID(wifeParents.getHusband().getHusbandPerson().getPersonID());
            couple.getWife().getWifePerson().setMotherID(wifeParents.getWife().getWifePerson().getPersonID());

            //set descendants of the parent
            wifeParents.getHusband().getHusbandPerson().setDescendant(couple.getWife().getWifePerson().getDescendant());
            wifeParents.getWife().getWifePerson().setDescendant(wifeParents.getHusband().getHusbandPerson().getDescendant());

            //set last name of the parent
            wifeParents.getWife().getWifePerson().setLastName(wifeParents.getHusband().getHusbandPerson().getLastName());

            //set Event of parent
            wifeParents.getHusband().getHusbandEvent().setDescendant(wifeParents.getHusband().getHusbandPerson().getDescendant());
            wifeParents.getWife().getWifeEvent().setDescendant(wifeParents.getHusband().getHusbandPerson().getDescendant());
            wifeParents.getHusband().getHusbandEvent().setPersonID(wifeParents.getHusband().getHusbandPerson().getPersonID());
            wifeParents.getWife().getWifeEvent().setPersonID(wifeParents.getWife().getWifePerson().getPersonID());

            //add to queue
            queue.add(wifeParents);
            generations.persons.add(couple.getHusband().getHusbandPerson());
            generations.persons.add(couple.getWife().getWifePerson());
            generations.events.add(couple.getHusband().getHusbandEvent());
            generations.events.add(couple.getWife().getWifeEvent());
        }
        while (queue.peek() != null) {
            Couple couple = queue.poll();
            generations.persons.add(couple.getHusband().getHusbandPerson());
            generations.persons.add(couple.getWife().getWifePerson());

            generations.events.add(couple.getHusband().getHusbandEvent());
            generations.events.add(couple.getWife().getWifeEvent());
        }
//        c = null;
//        c.setHusband(null);
//        c.setWife(null);
        return generations;
    }


}
