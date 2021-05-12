package cs240.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cs240.Model.Person;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */

public class PersonDao {
    DAOManager DM = new DAOManager();
    Connection c = DM.getConnection();


    public PersonDao() {
    }

    /**
     * add person or people.
     * @param p a person or list of people.
     */
    public boolean addPerson(Person p) throws SQLException {
        PreparedStatement stmt = null;

        String PID = p.getPersonID();
        String Descendant = p.getDescendant();
        String FirstName  = p.getFirstName();
        String LastName = p.getLastName();
        String Gender = p.getGender();

        String FatherID = p.getFatherID();
        String MotherID = p.getMotherID();
        String SpouseID = p.getSpouseID();
        try {

            try{
                String sql = " INSERT INTO PERSON (PersonID, Descendant, FirstName, LastName, Gender, FatherID, MotherID, SpouseID)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                stmt = c.prepareStatement(sql);
                stmt.setString(1, PID);
                stmt.setString(2, Descendant);
                stmt.setString(3, FirstName);
                stmt.setString(4, LastName);
                stmt.setString(5, Gender);
                stmt.setString(6, FatherID);
                stmt.setString(7, MotherID);
                stmt.setString(8, SpouseID);

                stmt.executeUpdate();
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }

        }catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        System.out.println("PO inserted successfully");
        return true;
    }

    /**
     * remove userName from person class.
     * @param p user name of user.
     */
    public void removePerson(Person p) throws SQLException {

        PreparedStatement stmt = null;
        String PID = p.getPersonID();

            try{

                String sql = " DELETE FROM PERSON WHERE PersonID = ?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, PID);
                stmt.executeUpdate();
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }

        System.out.println("Records deleted successfully");

    }


    public boolean removeAllPerson(Person p) {

        PreparedStatement stmt = null;
        String PID = p.getDescendant();
        try {

            try{

                if(findPerson(p)){
                    String sql = " DELETE FROM PERSON WHERE Descendant = ?";
                    stmt = c.prepareStatement(sql);
                    stmt.setString(1, PID);
                    stmt.executeUpdate();
                    System.out.println("Records deleted successfully");
                    return true;
                }
                else {

                }

            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }

        }catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }

        return false;

    }

    /**
     * find person with ID.
     * @param p of person.
     * @return p.
     */
    public boolean findPerson(Person p) {
        PreparedStatement stmt = null;
        ResultSet results = null;

        String PID = p.getPersonID();
        try {

            try{

                String sql = " SELECT * FROM PERSON WHERE PersonID = ?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, PID);
                results = stmt.executeQuery();


                while (results.next()) {
                    String PrsnID = results.getString(1);

                    if(PID.equals(PrsnID)){
                        System.out.println(PID + " exist!");
                        System.out.println("Person found successfully");
                        return true;
                    }

                }
                results.close();

                stmt.executeQuery();
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }

        }catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println( PID + " doesn't exist!");
        }
        return false;
    }

    public Person getPerson(Person p) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;

        String PID = p.getPersonID();
//        try {

            try{

                String sql = " SELECT * FROM PERSON WHERE PersonID = ?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, PID);
                results = stmt.executeQuery();


                while (results.next()) {
                    String PrsnID = results.getString(1);

                    if(PID.equals(PrsnID)){
                        System.out.println(PID + " exist!");
                        p.setPersonID(PrsnID);
                        p.setDescendant(results.getString(2));
                        p.setFirstName(results.getString(3));
                        p.setLastName(results.getString(4));
                        p.setGender(results.getString(5));
                        p.setFatherID(results.getString(6));
                        p.setMotherID(results.getString(7));
                        p.setSpouseID(results.getString(8));
                        return p;
                    }

                }
                results.close();

                stmt.executeQuery();
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }


        return p;
    }

    public ArrayList<Person> getPersons(ArrayList<Person> PList, Person p) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;
        String d = p.getDescendant();

            try{

                String sql = " SELECT * FROM PERSON WHERE Descendant = ?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, d);
                results = stmt.executeQuery();

                int i = 0;
                while (results.next()) {
                    p = new Person();
                    String descendant = results.getString(2);

                    if(d.equals(descendant)){
                        System.out.println(d + " exist!");
                        p.setPersonID(results.getString(1));
                        p.setDescendant(results.getString(2));
                        p.setFirstName(results.getString(3));
                        p.setLastName(results.getString(4));
                        p.setGender(results.getString(5));
                        p.setFatherID(results.getString(6));
                        p.setMotherID(results.getString(7));
                        p.setSpouseID(results.getString(8));
                        PList.add(p);


                    }


                }

                results.close();

                stmt.executeQuery();
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }


        return PList;
    }

    /**
     * find with username.
     * @param p  name of user.
     * @return Name.
     */
    public Person findUser(Person p){
        PreparedStatement stmt = null;
        ResultSet results = null;

        String firstName = p.getFirstName();
        String lastName = p.getLastName();


        try {

            try{

                String sql = " SELECT * FROM PERSON WHERE FirstName = ? AND LastName = ? ";
                stmt = c.prepareStatement(sql);
                stmt.setString(3, firstName);
                stmt.setString(4, lastName);

                results = stmt.executeQuery();
                while (results.next()) {
                    String FN = results.getString(3);
                    String LN = results.getString(4);
                    if((FN == firstName) && (LN == lastName)){
                        System.out.println(firstName + " " + lastName + " exist!");
                        return p;
                    }
                }
                results.close();



                stmt.executeUpdate();
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }

        }catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println( firstName + " " + lastName +" doesn't exist!");
            System.exit(0);
        }
        System.out.println("Records found successfully");
        return null;
    }
}
