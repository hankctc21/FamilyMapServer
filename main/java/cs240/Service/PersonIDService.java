package cs240.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import cs240.DAO.AuthTokenDao;
import cs240.DAO.PersonDao;
import cs240.Model.AuthToken;
import cs240.Model.Person;
import cs240.Result.PersonIDResult;
import cs240.Result.PersonResult;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-20.
 */



public class PersonIDService {

    PersonIDResult PIDResult = new PersonIDResult();


    /**
     * Returns the single Person object with the specified ID.
     */


    public PersonIDResult pIDResult(String authToken, String pID) throws SQLException {
        PersonDao pDao = new PersonDao();
        Person p = new Person();
        AuthTokenDao aDao = null;
        aDao = new AuthTokenDao();
        AuthToken a = new AuthToken();
        PersonIDResult pids = new PersonIDResult();

        a.setAuthToken(authToken);
        p.setPersonID(pID);
        String str = pDao.getPerson(p).getDescendant();



        if(aDao.findAuthToken(a)){
            if(aDao.getAuthToken(a).getUserName().equals(str)){
                if(pDao.findPerson(p)){
                    pids.setPersonID(pDao.getPerson(p).getPersonID());
                    pids.setDescendant(pDao.getPerson(p).getDescendant());
                    pids.setFirstName(pDao.getPerson(p).getFirstName());
                    pids.setLastName(pDao.getPerson(p).getLastName());
                    pids.setGender(pDao.getPerson(p).getGender());
                    pids.setFatherID(pDao.getPerson(p).getFatherID());
                    pids.setMotherID(pDao.getPerson(p).getMotherID());
                    pids.setSpouseID(pDao.getPerson(p).getSpouseID());
                    return pids;
                }
            }


        }


        return null;
    }

    public PersonResult pResult(String authToken) throws SQLException {
        PersonDao pDao = new PersonDao();
        AuthTokenDao aDao = null;
        aDao = new AuthTokenDao();
        AuthToken a = new AuthToken();
        Person p = new Person();
        PersonResult pr = new PersonResult();
        PersonIDResult pidr = new PersonIDResult();
        ArrayList<Person> newPersonsList = new ArrayList<>();

        //find authToken
        a.setAuthToken(authToken);
        if(aDao.findAuthToken(a)) {
            a = aDao.getAuthToken(a);
            p.setPersonID(a.getPersonID());

            if (pDao.findPerson(p)) {
                pidr.setPerson(pDao.getPerson(p));
                pr.setPersonsList(pDao.getPersons(newPersonsList, pidr.getPerson()));

                return pr;
            }
        }

        return null;
    }
}
