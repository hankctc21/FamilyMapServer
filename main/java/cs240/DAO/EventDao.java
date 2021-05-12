package cs240.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import cs240.Model.Event;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */

public class EventDao {
    DAOManager DM = new DAOManager();
    Connection c = DM.getConnection();

    public EventDao() {
    }

    /**
     * add events to model.
     *
     * @param e event of user.
     */
    public boolean addEvent(Event e) throws SQLException {
        PreparedStatement stmt = null;
        DecimalFormat df = new DecimalFormat("#.####");

        String EventID = e.getEventID();
        String PersonID = e.getPersonID();
        String Descendant = e.getDescendant();
        double Latitude = Double.valueOf(df.format(e.getLatitude()));
        double Longitude = Double.valueOf(df.format(e.getLongitude()));

        String Country = e.getCountry();
        String City = e.getCity();
        int Year = e.getYear();
        String eventType = e.getEventType();
        try {
            try{
                String sql = " INSERT INTO EVENT (EventID, PersonID, Descendant, Latitude, Longitude, Country, City, eventType, Year)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stmt = c.prepareStatement(sql);

                stmt.setString(1, EventID);
                stmt.setString(2, PersonID);
                stmt.setString(3, Descendant);
                stmt.setDouble(4, Latitude);
                stmt.setDouble(5, Longitude);
                stmt.setString(6, Country);
                stmt.setString(7, City);
                stmt.setString(8, eventType);
                stmt.setInt(9, Year);

                stmt.executeUpdate();
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }catch ( SQLException excp ) {
            System.err.println( excp.getClass().getName() + ": " + excp.getMessage() );
            return false;
        }
        System.out.println("EO inserted successfully");
        return true;
    }

    /**
     * remove events from model.
     * @param userName user name of user.
     */
    public void removeEvent(String userName){
        PreparedStatement stmt = null;

        try {
            try{
                String sql = " DELETE FROM EVENT WHERE Username = ?";

                stmt = c.prepareStatement(sql);
                stmt.setString(1, userName);

                stmt.executeUpdate();
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records deleted successfully");
    }

    public boolean removeAllEvent(String descendant) throws SQLException {
        PreparedStatement stmt = null;

        try {
            try{
                String sql = " DELETE FROM EVENT WHERE Descendant = ?";

                stmt = c.prepareStatement(sql);
                stmt.setString(1, descendant);

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
        System.out.println("Records deleted successfully");
        return true;
    }

    /**
     * find with event ID.
     * @param p event ID of user.
     * @return eventID when it finds.
 */
    public boolean findEventID(Event p) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;

        String PID = p.getEventID();
//        try {

        try{

            String sql = " SELECT * FROM EVENT WHERE EventID = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, PID);
            results = stmt.executeQuery();


            while (results.next()) {
                String PrsnID = results.getString(1);

                if(PID.equals(PrsnID)){
                    System.out.println(PID + " exist!");

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


        return false;
    }

    public boolean findPersonID(Event p) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;

        String PID = p.getEventID();
//        try {

        try{

            String sql = " SELECT * FROM EVENT WHERE PersonID = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, PID);
            results = stmt.executeQuery();


            while (results.next()) {
                String PrsnID = results.getString(1);

                if(PID.equals(PrsnID)){
                    System.out.println(PID + " exist!");

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

        return false;
    }




    public Event getEvent(Event e){
        PreparedStatement stmt = null;
        ResultSet results;

        String pID = e.getEventID();
        try {
            try{
                String sql = " SELECT * FROM EVENT WHERE EventID = ?";
                stmt = c.prepareStatement(sql);

                stmt.setString(1, pID);

                results = stmt.executeQuery();

                while (results.next()) {
                    e = new Event();
                    String PID = results.getString(1);

                    if(pID.equals(PID)){
                        System.out.println(pID + " exist!");
                        e.setEventID(pID);
                        e.setPersonID(results.getString(2));
                        e.setDescendant(results.getString(3));
                        e.setLatitude(results.getDouble(4));
                        e.setLongitude(results.getDouble(5));
                        e.setCountry(results.getString(6));
                        e.setCity(results.getString(7));
                        e.setEventType(results.getString(8));
                        e.setYear(results.getInt(9));
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
        }catch ( SQLException excp ) {
            System.err.println( excp.getClass().getName() + ": " + excp.getMessage() );
            System.out.println( pID + " doesn't exist!");
            return null;

        }
        System.out.println("Records found successfully");
        return e;
    }




    public ArrayList<Event> getEvents(ArrayList<Event> EList, Event e) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;
        String d = e.getDescendant();
        try {

            try{

                String sql = " SELECT * FROM EVENT WHERE Descendant = ?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, d);
                results = stmt.executeQuery();

                while (results.next()) {
                    e = new Event();
                    String descendant = results.getString(3);

                    if(d.equals(descendant)){
                        System.out.println(d + " exist!");
                        e.setEventID(results.getString(1));
                        e.setPersonID(results.getString(2));
                        e.setDescendant(results.getString(3));
                        e.setLatitude(results.getDouble(4));
                        e.setLongitude(results.getDouble(5));
                        e.setCountry(results.getString(6));
                        e.setCity(results.getString(7));
                        e.setEventType(results.getString(8));
                        e.setYear(results.getInt(9));
                        EList.add(e);
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

        }catch ( SQLException ex ) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.out.println( d + " doesn't exist!");
            return null;
        }
        return EList;

    }


    /**
     * find with userName
     * @param e user name of user.
     * @return userName when it finds.
     */
    public Event findU(Event e){

        return null;
    }

}
