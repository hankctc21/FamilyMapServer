package cs240.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cs240.Model.AuthToken;
import cs240.Model.User;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-10-12.
 */

public class AuthTokenDao {
    DAOManager DM = new DAOManager();
    Connection c = DM.getConnection();


    public AuthTokenDao()  {
    }

    /**
     * check if if has the right token.
     * @param t token of user.
     * @return true if the user has a right token.
     */
    public boolean findAuthToken(AuthToken t) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;

        String AT = t.getAuthToken();


            try{
                String sql = " SELECT * FROM AUTHTOKEN WHERE AuthToken = ?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, AT);
                results = stmt.executeQuery();
                while (results.next()) {
                    String AToken = results.getString(1);
                    if(AT.equals(AToken)){
                        System.out.println(AT + " exist!");
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

        System.out.println("Records not found ");

        return false;
    }

    public AuthToken getAuthToken(AuthToken t) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;

        String AT = t.getAuthToken();


            try{
                String sql = " SELECT * FROM AUTHTOKEN WHERE AuthToken = ?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, AT);
                results = stmt.executeQuery();
                while (results.next()) {
                    String AToken = results.getString(1);
                    if(AT.equals(AToken)){
                        System.out.println(AT + " exist!");
                        t.setAuthToken(results.getString(1));
                        t.setUserName(results.getString(2));
                        t.setPersonID(results.getString(3));

                        return t;
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

        System.out.println("Records not found ");

        return null;
    }

    public AuthToken getPersonID(AuthToken t) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet results = null;

        String AT = t.getAuthToken();


        try{
            String sql = " SELECT * FROM AUTHTOKEN WHERE PersonID = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, AT);
            results = stmt.executeQuery();
            while (results.next()) {
                String AToken = results.getString(1);
                if(AT.equals(AToken)){
                    System.out.println(AT + " exist!");
                    t.setAuthToken(results.getString(1));
                    t.setUserName(results.getString(2));
                    t.setPersonID(results.getString(3));

                    return t;
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

        System.out.println("Records not found ");

        return null;
    }



    /**
     * add token with the user name.
     * generates unique token.
     * @param u token of user.
     */
    public boolean addAuthToken(User u) throws SQLException {
        PreparedStatement stmt = null;

        String AuthToken = u.getAuthToken();
        String Username = u.getUserName();
        String PersonID = u.getPersonID();

        try {

            try{
                String sql = " INSERT INTO AUTHTOKEN ( AuthToken, Username, PersonID )" +
                        "VALUES (?, ?, ?)";
                stmt = c.prepareStatement(sql);

                stmt.setString(1, AuthToken);
                stmt.setString(2, Username);
                stmt.setString(3, PersonID);

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
        System.out.println("AO inserted ");
        return true;
    }

    /**
     * remove user if it has the right token.
     * if time expires, find the authToken and remove the token.
     * @param t token of user.
     * @param userName user name of user.
     */
    public void remove(AuthToken t, String userName) throws SQLException {
        PreparedStatement stmt = null;


            try{
                String sql = " DELETE FROM AUTHTOKEN WHERE Username = ?";

                stmt = c.prepareStatement(sql);
                stmt.setString(1, userName);

                stmt.executeUpdate();
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }

        System.out.println("Records deleted successfully");
    }
}
