package cs240.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import cs240.DAO.AuthTokenDao;
import cs240.DAO.EventDao;
import cs240.DAO.PersonDao;
import cs240.Model.AuthToken;
import cs240.Model.Event;
import cs240.Result.EventID;
import cs240.Result.EventResult;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-20.
 */

public class EventService {


    public EventID eventResult(String authToken, String eID) throws SQLException {
        EventDao eDao = new EventDao();
        Event p = new Event();
        AuthTokenDao aDao = null;
        aDao = new AuthTokenDao();
        AuthToken a = new AuthToken();
        EventID pids = new EventID();

        a.setAuthToken(authToken);


        if(aDao.findAuthToken(a)){
            p.setEventID(eID);
            String str = aDao.getAuthToken(a).getPersonID();
            System.out.println(str + "*****");
            String str2 = eDao.getEvent(p).getPersonID();
            System.out.println(str2 + "*********");

            if(aDao.getAuthToken(a).getPersonID().equals(str2)){
                if(eDao.findEventID(p)){

                    pids.setPersonID(eDao.getEvent(p).getPersonID());
                    pids.setDescendant(eDao.getEvent(p).getDescendant());
                    pids.setCountry(eDao.getEvent(p).getCountry());
                    pids.setEventID(eDao.getEvent(p).getEventID());
                    pids.setYear(eDao.getEvent(p).getYear());
                    pids.setEventType(eDao.getEvent(p).getEventType());
                    pids.setLatitude(eDao.getEvent(p).getLatitude());
                    pids.setLongitude(eDao.getEvent(p).getLongitude());
                    pids.setCity(eDao.getEvent(p).getCity());
                    return pids;
                }
            }
            else{
                throw new SQLException("Error in getEvents");
            }


        }
        return null;

    }


    public EventResult eventResult(String authToken) throws SQLException {
        EventDao eDao = new EventDao();
        PersonDao pDao = new PersonDao();
        AuthTokenDao aDao = null;
        aDao = new AuthTokenDao();
        AuthToken a = new AuthToken();
        Event e = new Event();
        EventResult er = new EventResult();
        EventID eidr = new EventID();
        ArrayList<Event> newEventList = new ArrayList<>();

        a.setAuthToken(authToken);
        if(aDao.findAuthToken(a)) {
            a = aDao.getAuthToken(a);
            e.setPersonID(a.getPersonID());
            e.setDescendant(a.getUserName());

            if(eDao.getEvents(newEventList, e) != null){
                er.setEventList(eDao.getEvents(newEventList, e));
            }
            else{
                throw new SQLException("Error in getEvents");
            }

            return er;

        }

        return null;


    }
}
