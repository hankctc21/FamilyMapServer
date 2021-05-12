package cs240.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;

import cs240.DAO.AuthTokenDao;
import cs240.DAO.DAOManager;
import cs240.DAO.EventDao;
import cs240.DAO.Generate;
import cs240.DAO.Generations;
import cs240.DAO.PersonDao;
import cs240.DAO.UserDao;
import cs240.Handler.RegisterHandler;
import cs240.Model.AuthToken;
import cs240.Model.Couple;
import cs240.Model.Event;
import cs240.Model.LocData;
import cs240.Model.Person;
import cs240.Model.User;
import cs240.Request.RegisterRequest;
import cs240.Result.RegisterResult;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */


/**
 * Creates a new user account, generates 4 generations of ancestor data for the new
 user, logs the user in, and returns an auth token
 */
public class RegisterService {
    DAOManager DM = new DAOManager();

    RegisterResult RResult = new RegisterResult();
    RegisterHandler RG = new RegisterHandler();

    Generate gen = new Generate();
    UserDao UD = new UserDao();
    PersonDao PD = new PersonDao();
    EventDao ED = new EventDao();
    AuthTokenDao AD = new AuthTokenDao();

    Couple c = new Couple();
    String gender = "";

    Generations generations = new Generations();

    ArrayDeque<Couple> queue = new ArrayDeque<Couple>();

    public RegisterService() throws SQLException {
    }



    /**
     * Creates a new user account.
     *
    /**
     * register all the information of person and get auth token
     * @param r
     * @return
     */
    public RegisterResult register(RegisterRequest r) throws SQLException, IOException {
        AuthToken at = new AuthToken();
        User u = new User();
        Person p = new Person();
        RegisterResult rrst = new RegisterResult();
        generations.persons.clear();
        generations.events.clear();
        queue.clear();


        try {
            if(!DM.createTables()){
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }

        try {
            SetAndAddUser(r);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;


        }
        generations.persons.clear();
        generations.events.clear();

        if(gender.equals("m")){
            generations = generateGenerations(4, c.getHusband().getHusbandEvent().getYear());
        }
        else{
            generations = generateGenerations(4, c.getWife().getWifeEvent().getYear());
        }

        for(Person person : generations.persons){
            if(!(PD.addPerson(person))){
                return null;

            }
        }

        for(Event event : generations.events){
            ED.addEvent(event);
        }
        u.setUserName(r.getUserName());
        String tmpAuth = UD.getUser(u).getAuthToken();
        rrst.setPersonID(UD.getUser(u).getPersonID());
        rrst.setAuthToken(tmpAuth);
        rrst.setUserName(u.getUserName());

        return rrst;
    }

    public void SetAndAddUser(RegisterRequest r) throws SQLException, IOException {


        Event e = new Event();
        User u = new User();
        Person p = new Person();
        AuthToken a = new AuthToken();

        String str = "";

        if(r.getUsers().size() > 1){
            for(int i = 0; i < r.getUsers().size(); i++ ) {
                u.setUserName(r.getUsers().get(i).getUserName());
                u.setPassWord(r.getUsers().get(i).getPassWord());
                u.setEmail(r.getUsers().get(i).getEmail());
                u.setFirstName(r.getUsers().get(i).getFirstName());
                u.setLastName(r.getUsers().get(i).getLastName());
                u.setGender(r.getUsers().get(i).getGender());
                u.setAuthToken(gen.getRandomID());
                if (r.getUsers().get(i).getPersonID().equals(str)) {
                    u.setPersonID(gen.getRandomID());
                } else {
                    u.setPersonID(r.getUsers().get(i).getPersonID());
                }

                if(!(UD.addUser(u))){
                    throw new SQLException("Errir in addUser");
                }
                if(!(AD.addAuthToken(u))){
                    throw new SQLException("Error with SQL");
                }
                if (u.getGender().equals("m")) {
                    c.getHusband().setHusbandUser(u);
                    gender = "m";
                } else if (u.getGender().equals("f")) {
                    c.getWife().setWifeUser(u);
                    gender = "f";
                }
                else{
                    c.getHusband().setHusbandUser(u);
                    gender = "m";
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

                if (u.getGender().equals("m")) {
                    c.getHusband().setHusbandPerson(p);
                    gender = "m";
                } else {
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
                if (u.getGender().equals("m")) {
                    c.getHusband().setHusbandEvent(e);
                } else {
                    c.getWife().setWifeEvent(e);
                }
            }
        }
        else{
            u.setUserName(r.getUserName());
            u.setPassWord(r.getPassWord());
            u.setEmail(r.getEmail());
            u.setFirstName(r.getFirstName());
            u.setLastName(r.getLastName());
            u.setGender(String.valueOf(r.getGender()));
            u.setAuthToken(gen.getRandomID());
            if (r.getPersonID().equals(str)) {
                u.setPersonID(gen.getRandomID());
            } else {
                u.setPersonID(r.getPersonID());
            }

            if(!(UD.addUser(u))){
                throw new SQLException("Error in addUser");
            }

            if(!(AD.addAuthToken(u))){
                throw new SQLException("Error with SQL");
            }

            if (u.getGender().equals("m")) {
                c.getHusband().setHusbandUser(u);
                gender = "m";
            } else {
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

            if (u.getGender().equals("m")) {
                c.getHusband().setHusbandPerson(p);
                gender = "m";
            } else {
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
            if (u.getGender().equals("m")) {
                c.getHusband().setHusbandEvent(e);
            } else {
                c.getWife().setWifeEvent(e);
            }
        }


    }

    public void SetAndAddSpouse() throws SQLException, IOException {
        LocData L = gen.getRandLocation();
        if(gender.equals("f")){//if input gender is female
            //set husband
            c.getHusband().getHusbandUser().setUserName(gen.getRandUserName());
            c.getHusband().getHusbandUser().setFirstName(c.getHusband().getHusbandUser().getUserName());
            c.getHusband().getHusbandUser().setLastName(gen.getRandLastName());
            c.getHusband().getHusbandUser().setPersonID(c.getWife().getWifePerson().getSpouseID());
            c.getHusband().getHusbandUser().setEmail(gen.getRandEmail(c.getHusband().getHusbandUser().getUserName()));
            c.getHusband().getHusbandUser().setPassWord(gen.getRandomID());
            c.getHusband().getHusbandUser().setAuthToken(gen.getRandomID());
            c.getHusband().getHusbandUser().setGender("m");



            c.getHusband().getHusbandPerson().setPersonID(c.getHusband().getHusbandUser().getPersonID());
            c.getHusband().getHusbandPerson().setFirstName(c.getHusband().getHusbandUser().getFirstName());
            c.getHusband().getHusbandPerson().setLastName(c.getWife().getWifeUser().getLastName());
            c.getHusband().getHusbandPerson().setFatherID(gen.getRandomID());
            c.getHusband().getHusbandPerson().setMotherID(gen.getRandomID());
            c.getHusband().getHusbandPerson().setSpouseID(c.getWife().getWifeUser().getPersonID());
            c.getHusband().getHusbandPerson().setGender(c.getHusband().getHusbandUser().getGender());
            c.getHusband().getHusbandPerson().setDescendant(c.getWife().getWifePerson().getDescendant());


            c.getHusband().getHusbandEvent().setPersonID(c.getHusband().getHusbandPerson().getPersonID());
            c.getHusband().getHusbandEvent().setDescendant(c.getHusband().getHusbandPerson().getDescendant());
            c.getHusband().getHusbandEvent().setCountry(L.getCountry());
            c.getHusband().getHusbandEvent().setCity(L.getCity());
            c.getHusband().getHusbandEvent().setLatitude(L.getLatitude());
            c.getHusband().getHusbandEvent().setLongitude(L.getLongitude());
            c.getHusband().getHusbandEvent().setYear(c.getWife().getWifeEvent().getYear()+gen.getRandNumber2());
            c.getHusband().getHusbandEvent().setEventType(gen.getRandEventType());
            c.getHusband().getHusbandEvent().setEventID(gen.getRandomID());
        }
        else{//if input gender is male
            //set wife
            c.getWife().getWifeUser().setUserName(gen.getRandUserName());
            c.getWife().getWifeUser().setFirstName(c.getWife().getWifeUser().getUserName());
            c.getWife().getWifeUser().setLastName(gen.getRandLastName());
            c.getWife().getWifeUser().setPersonID(c.getHusband().getHusbandPerson().getSpouseID());
            c.getWife().getWifeUser().setEmail(gen.getRandEmail(c.getWife().getWifeUser().getUserName()));
            c.getWife().getWifeUser().setPassWord(gen.getRandomID());
            c.getWife().getWifeUser().setAuthToken(gen.getRandomID());
            c.getWife().getWifeUser().setGender("f");

            c.getWife().getWifePerson().setPersonID(c.getWife().getWifeUser().getPersonID());
            c.getWife().getWifePerson().setFirstName(c.getWife().getWifeUser().getFirstName());
            c.getWife().getWifePerson().setLastName(c.getHusband().getHusbandUser().getLastName());
            c.getWife().getWifePerson().setFatherID(gen.getRandomID());
            c.getWife().getWifePerson().setMotherID(gen.getRandomID());
            c.getWife().getWifePerson().setSpouseID(c.getHusband().getHusbandUser().getPersonID());
            c.getWife().getWifePerson().setGender(c.getWife().getWifeUser().getGender());
            c.getWife().getWifePerson().setDescendant(c.getHusband().getHusbandPerson().getDescendant());

            c.getWife().getWifeEvent().setPersonID(c.getWife().getWifePerson().getPersonID());
            c.getWife().getWifeEvent().setDescendant(c.getWife().getWifePerson().getDescendant());
            c.getWife().getWifeEvent().setCountry(L.getCountry());
            c.getWife().getWifeEvent().setCity(L.getCity());
            c.getWife().getWifeEvent().setLatitude(L.getLatitude());
            c.getWife().getWifeEvent().setLongitude(L.getLongitude());
            c.getWife().getWifeEvent().setYear(c.getHusband().getHusbandEvent().getYear()+gen.getRandNumber2());
            c.getWife().getWifeEvent().setEventType(gen.getRandEventType());
            c.getWife().getWifeEvent().setEventID(gen.getRandomID());
        }
    }

    public Generations generateGenerations(int n, int year) throws SQLException, IOException {

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




            //Wife's parents----------------------------------
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




            //add to generations
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
        return generations;
    }
}


