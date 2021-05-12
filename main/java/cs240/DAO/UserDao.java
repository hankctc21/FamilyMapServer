package cs240.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cs240.Model.User;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */



public class UserDao {
    DAOManager DM = new DAOManager();
    Connection c = DM.getConnection();

    public UserDao()  {
    }

    /**
     * add userName to the User class.
     * @param u userName of user
     * */
    public boolean addUser(User u) throws SQLException {
        boolean find = true;
        PreparedStatement stmt = null;

        String UserName = u.getUserName();
        String PassWord = u.getPassWord();
        String PersonID = u.getPersonID();
        String AuthToken = u.getAuthToken();
        String Email = u.getEmail();
        String FirstName  = u.getFirstName();
        String LastName = u.getLastName();
        String Gender = u.getGender();

        try {
            try{
                String sql = " INSERT INTO USER (Username, Password, PersonID, AuthToken, Email, FirstName, LastName, Gender)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                stmt = c.prepareStatement(sql);

                stmt.setString(1, UserName);
                stmt.setString(2, PassWord);
                stmt.setString(3, PersonID);
                stmt.setString(4, AuthToken);
                stmt.setString(5, Email);
                stmt.setString(6, FirstName);
                stmt.setString(7, LastName);
                stmt.setString(8, Gender);

                stmt.executeUpdate();
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }

        }catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            find = false;
            return false;
        }
        System.out.println("UO inserted successfully");
        return true;
    }

    /**
     * remove userName from the class.
     * @param u name of user
     */
    public void remove(User u)  {
        PreparedStatement stmt = null;

        String Username = u.getUserName();

        try {
            try{
                String sql = " DELETE FROM USER WHERE Username = ?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, Username);
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

//    /**
//     * check if the class contains userName.
//     * @param username user name of user.
//     * @return true if the table contains userName.
////     */
//    public boolean containsUser(String username, String password) {
//        PreparedStatement stmt = null;
//        ResultSet results = null;
//
//        String UN = username;
//        String PW = password;
//        try {
//            try{
//                String sql = " SELECT * FROM USER WHERE Username = ? AND Password = ?";
//                stmt = c.prepareStatement(sql);
//
//                stmt.setString(1, UN);
//                stmt.setString(2, PW);
//
//                results = stmt.executeQuery();
//
//                while (results.next()) {
//                    String UsrN = results.getString(1);
//                    String PassW = results.getString(2);
//
//                    if(UN.equals(UsrN) && UN.equals(PassW)){
//                        System.out.println(UN + " exists!");
//                        return true;
//                    }
//                }
//                results.close();
//
//                stmt.executeUpdate();
//            }finally {
//                if (stmt != null) {
//                    stmt.close();
//                    stmt = null;
//                }
//            }
//        }catch ( SQLException e ) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.out.println( UN + " doesn't exist!");
//            System.exit(0);
//            return false;
//        }
//        System.out.println("Records found successfully");
//        return true;
//    }

    public boolean findUser(User u) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;

        String UN = u.getUserName();

            try{
                String sql = " SELECT * FROM USER WHERE Username = ?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, UN);
                results = stmt.executeQuery();

                while (results.next()) {
                    String UsrN = results.getString(1);

                    if(UN.equals(UsrN)){
                        System.out.println(UN + " exists!");
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

        System.out.println("Records not found successfully");
        return false;
    }

    public boolean findPassword(User u) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;

        String UN = u.getPassWord();

        try{
            String sql = " SELECT * FROM USER WHERE Password = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, UN);
            results = stmt.executeQuery();

            while (results.next()) {
                String UsrN = results.getString(2);

                if(UN.equals(UsrN)){
                    System.out.println(UN + " exists!");
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

        System.out.println("Records not found successfully");
        return false;
    }

    /**
     * find user name.
     * @param u user name of user.
     * @return userName.
     */
    public User getUser(User u) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;

        String UN = u.getUserName();

            try{
                String sql = " SELECT * FROM USER WHERE Username = ?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, UN);
                results = stmt.executeQuery();

                while (results.next()) {
                    String UsrN = results.getString(1);

                    if(UN.equals(UsrN)){
                        System.out.println(UN + " exists!");
                        u.setPassWord(results.getString(2));
                        u.setPersonID(results.getString(3));
                        u.setAuthToken(results.getString(4));
                        u.setEmail(results.getString(5));
                        u.setFirstName(results.getString(6));
                        u.setLastName(results.getString(7));
                        u.setGender(results.getString(8));
                        return u;
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

        return u;
    }



}
