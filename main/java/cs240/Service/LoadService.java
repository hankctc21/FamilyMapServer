package cs240.Service;


import java.sql.SQLException;

import cs240.DAO.AuthTokenDao;
import cs240.DAO.DAOManager;
import cs240.DAO.EventDao;
import cs240.DAO.Generate;
import cs240.DAO.PersonDao;
import cs240.DAO.UserDao;
import cs240.Handler.LoadHandler;
import cs240.Model.AuthToken;
import cs240.Model.Event;
import cs240.Model.Person;
import cs240.Model.User;
import cs240.Request.LoadRequest;
import cs240.Result.LoadResult;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-20.
 */

public class LoadService {

    LoadHandler LH = new LoadHandler();

    DAOManager DM = new DAOManager();
    Generate gen = new Generate();
    UserDao UD = new UserDao();
    PersonDao PD = new PersonDao();
    EventDao ED = new EventDao();
    AuthTokenDao AD = new AuthTokenDao();
    User u = new User();
    Person p = new Person();
    Event e = new Event();
    AuthToken a = new AuthToken();

    int uCount = 0;
    int pCount = 0;
    int eCount = 0;

    public LoadService() {

    }

    public void SetAndAddUser(LoadRequest r) throws SQLException {
        for(int i = 0; i < r.getUsers().size(); i++ ){
            u.setUserName(r.getUsers().get(i).getUserName());
            u.setPassWord(r.getUsers().get(i).getPassWord());
            u.setEmail(r.getUsers().get(i).getEmail());
            u.setFirstName(r.getUsers().get(i).getFirstName());
            u.setLastName(r.getUsers().get(i).getLastName());
            u.setGender(r.getUsers().get(i).getGender());
            u.setAuthToken(gen.getRandomID());
            u.setPersonID(r.getUsers().get(i).getPersonID());

            if(!(UD.addUser(u))){
                throw new SQLException("Errir in addUser");
            }
            AD.addAuthToken(u);

            uCount++;
        }
    }

    public void SetAndAddPerson(LoadRequest r ) throws SQLException {
        for(int i = 0; i < r.getPersons().size(); i++){
            p.setPersonID(r.getPersons().get(i).getPersonID());
            p.setDescendant(r.getPersons().get(i).getDescendant());
            p.setFirstName(r.getPersons().get(i).getFirstName());
            p.setLastName(r.getPersons().get(i).getLastName());
            p.setGender(r.getPersons().get(i).getGender());
            p.setFatherID(r.getPersons().get(i).getFatherID());
            p.setMotherID(r.getPersons().get(i).getMotherID());
            p.setSpouseID(r.getPersons().get(i).getSpouseID());
            if(!(PD.addPerson(p))){
                throw new SQLException("Error in addPerson");
            }            pCount++;
        }
    }

    public void SetAndAddEvent(LoadRequest r) throws SQLException {
        for(int i = 0; i < r.getEvents().size(); i++){
            e.setEventID(r.getEvents().get(i).getEventID());
            e.setPersonID(r.getEvents().get(i).getPersonID());
            e.setDescendant(r.getEvents().get(i).getDescendant());
            e.setEventType(r.getEvents().get(i).getEventType());
            e.setLatitude(r.getEvents().get(i).getLatitude());
            e.setLongitude(r.getEvents().get(i).getLongitude());
            e.setCountry(r.getEvents().get(i).getCountry());
            e.setCity(r.getEvents().get(i).getCity());
            e.setEventType(r.getEvents().get(i).getEventType());
            e.setYear(r.getEvents().get(i).getYear());
            ED.addEvent(e);
            eCount++;
        }
    }


    /**
     * Clears all data from the database (just like the /clear API), and then loads the
     posted user, person, and event data into the database.
     * @param r
     * @return
     */
    public LoadResult load(LoadRequest r) throws SQLException {
        LoadResult LR = new LoadResult();

        DM.createTables();

        SetAndAddUser(r);
        SetAndAddPerson(r);
        SetAndAddEvent(r);



        String str = "Successfully added "+ uCount +" users, " + pCount + " persons, and " + eCount + " events to the database.";

        LR.setMessage(str);

        return LR;
    }





}
