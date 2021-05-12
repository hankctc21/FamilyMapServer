package cs240.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */


/**
 * A class that manage all DAOs
 *
 * create database here.
 */
public class DAOManager {

    Connection c = getConnection();

    public DAOManager() {
    }


    public static Connection getConnection() {
        String driver = "org.sqlite.JDBC";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        String dbName = "jdbc:sqlite:daoData.db";
        Connection c = null;
        try {
            c = DriverManager.getConnection(dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }


    public void openConnection()  {
        try{
            c.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(boolean commit) {
        try {
            if(commit){
                c.commit();
            }
            else{
                c.rollback();
            }

            c.setAutoCommit(true);
            c.close();
            c = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createPersonTable() throws SQLException {
        boolean success = false;
        String message = "";
        try{
            Statement stmt = null;

            try{
                stmt = c.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS PERSON" +
                        "(PersonID text NOT NULL,       "+
                        "Descendant text," +
                        "FirstName text," +
                        "LastName text," +
                        "Gender text," +
                        "FatherID text," +
                        "MotherID text," +
                        "SpouseID text," +
                        "PRIMARY KEY (PersonID))";
                stmt.executeUpdate(sql);
                success = true;
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }catch (SQLException e){
            System.out.println(message = e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        if(success){
            message = "P Table created successfully";
            System.out.println(message);
            return true;
        }
        return false;
    }

    public boolean createUserTable() throws SQLException {
        try{
            Statement stmt = null;
            try{
                stmt = c.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS USER" +
                        "(Username text NOT NULL PRIMARY KEY," +
                        "Password text," +
                        "PersonID text NOT NULL," +
                        "AuthToken text,"   +
                        "Email text," +
                        "FirstName text," +
                        "LastName text," +
                        "Gender text," +
                        "FOREIGN KEY(PersonID) REFERENCES Person(PersonID))";
                stmt.executeUpdate(sql);
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }catch (SQLException e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        System.out.println("U Table created successfully");
        return true;
    }

    public boolean createEventTable() throws SQLException {
        try{
            Statement stmt = null;

            try{
                stmt = c.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS EVENT" +
                        "(EventID text NOT NULL PRIMARY KEY," +
                        "PersonID text NOT NULL," +
                        "Descendant text," +
                        "Latitude float," +
                        "Longitude float," +
                        "Country text," +
                        "City text," +
                        "EventType text," +
                        "Year int," +
                        "FOREIGN KEY(PersonID) REFERENCES Person(PersonID))";
                stmt.executeUpdate(sql);
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }catch (SQLException e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        System.out.println("E Table created successfully");
        return true;
    }

    public boolean createATokenTable() throws SQLException {
        try{
            Statement stmt = null;

            try{
                stmt = c.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS AUTHTOKEN" +
                        "(AuthToken text NOT NULL PRIMARY KEY," +
                        "Username text NOT NULL," +
                        "PersonID text," +
                        "FOREIGN KEY (Username) REFERENCES User(Username))";
                stmt.executeUpdate(sql);
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }catch (SQLException e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        System.out.println("A Table created successfully");
        return true;
    }

    public boolean dropTables() throws SQLException {
        try{
            Statement stmt = null;

            try{
                stmt = c.createStatement();
                String sql = "DROP TABLE IF EXISTS AUTHTOKEN";
                stmt.executeUpdate(sql);
                String sql2 = "DROP TABLE  IF EXISTS EVENT";
                stmt.executeUpdate(sql2);
                String sql3 = "DROP TABLE  IF EXISTS PERSON";
                stmt.executeUpdate(sql3);
                String sql4 = "DROP TABLE  IF EXISTS USER";
                stmt.executeUpdate(sql4);
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }catch (SQLException e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        System.out.println("All Tables dropped successfully");
        return true;
    }

    public boolean createTables() throws SQLException {
        if(createPersonTable() && createUserTable() && createEventTable() && createATokenTable()){
            return true;
        }
        return false;
    }

}





